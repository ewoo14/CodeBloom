package com.sparta.project.repository.user;

import static com.sparta.project.domain.QUser.user;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<User> findUserWith(Pageable pageable, String username, Role role, Boolean isDeleted) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(username, role, isDeleted);
        List<User> results = queryFactory.selectFrom(user)
                .where(booleanBuilder)
                .orderBy(getOrderSpecifiers(pageable)) // Sort 적용
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = queryFactory.select(user.count())
                .from(user)
                .where(booleanBuilder);
        return PageableExecutionUtils.getPage(results, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(String username, Role role, Boolean isDeleted) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(likeUsername(username));
        booleanBuilder.and(eqRole(role));
        booleanBuilder.and(eqIsDeleted(isDeleted));
        return booleanBuilder;
    }

    private BooleanExpression likeUsername(String username) {
        return user.username.like("%" + ((username!=null) ? username  : "") + "%");
    }

    private BooleanExpression eqRole(Role role) {
        return role != null ?  user.role.eq(role) : null;
    }

    private BooleanExpression eqIsDeleted(Boolean isDeleted) {
        return isDeleted != null ? user.isDeleted.eq(isDeleted) : null;
    }

    private OrderSpecifier<?>[] getOrderSpecifiers(Pageable pageable) {
        return pageable.getSort().stream()
                .map(order -> {
                    ComparableExpressionBase<?> sortPath = getSortPath(order.getProperty());
                    return new OrderSpecifier<>(
                            order.isAscending()
                                    ? com.querydsl.core.types.Order.ASC
                                    : com.querydsl.core.types.Order.DESC,
                            sortPath);
                })
                .toArray(OrderSpecifier[]::new);
    }

    // 정렬 기준
    private ComparableExpressionBase<?> getSortPath(String property) {
        return switch (property) {
            case "username" -> user.username;
            case "createdAt" -> user.createdAt;
            case "updatedAt" -> user.updatedAt;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_SORT_TYPE);
        };
    }

}
