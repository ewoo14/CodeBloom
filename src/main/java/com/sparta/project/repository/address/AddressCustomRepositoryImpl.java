package com.sparta.project.repository.address;


import static com.sparta.project.domain.QAddress.address;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.Address;
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
public class AddressCustomRepositoryImpl implements AddressCustomRepository {
    private final JPAQueryFactory queryFactory;

    // 일치하는 userId, 삭제 상태로 검색
    @Override
    public Page<Address> findAddressesWith(Pageable pageable,
                                           Long userId,
                                           String city,
                                           Boolean isDeleted) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(userId, city, isDeleted);
        List<Address> results = queryFactory.selectFrom(address)
                .where(booleanBuilder)
                .orderBy(getOrderSpecifiers(pageable)) // Sort 적용
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(address.count())
                .from(address)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(results, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(Long userName, String city, Boolean isDeleted) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eqUserId(userName));
        booleanBuilder.and(likeCity(city));
        booleanBuilder.and(eqIsDeleted(isDeleted));
        return booleanBuilder;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ?  address.user.userId.eq(userId) : null;
    }

    private BooleanExpression likeCity(String city) {
        return address.city.like("%" + ((city!=null) ? city  : "") + "%");
    }

    private BooleanExpression eqIsDeleted(Boolean isDeleted) {
        return isDeleted != null ? address.isDeleted.eq(isDeleted) : null;
    }

    // Pageable 의 Sort 객체를 기반으로 QueryDSL OrderSpecifier 배열을 생성하는 메서드
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
            case "userId" -> address.user.userId;
            case "createdAt" -> address.createdAt;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_SORT_TYPE);
        };
    }
}
