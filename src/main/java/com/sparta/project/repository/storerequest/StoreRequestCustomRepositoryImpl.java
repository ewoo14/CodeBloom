package com.sparta.project.repository.storerequest;

import static com.sparta.project.domain.QStoreRequest.storeRequest;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.StoreRequest;
import com.sparta.project.domain.enums.StoreRequestStatus;
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
public class StoreRequestCustomRepositoryImpl implements StoreRequestCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StoreRequest> findStoreRequestsWith(Pageable pageable,
                                                    String categoryId,
                                                    StoreRequestStatus status,
                                                    String storeName) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(categoryId, status, storeName, null);
        return getStoreRequests(pageable, booleanBuilder);
    }

    @Override
    public Page<StoreRequest> findUserStoreRequestsWith(Pageable pageable,
                                                        Long userId,
                                                        StoreRequestStatus status,
                                                        String storeName) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(null, status, storeName, userId);
        return getStoreRequests(pageable, booleanBuilder);
    }

    private Page<StoreRequest> getStoreRequests(Pageable pageable, BooleanBuilder booleanBuilder) {
        List<StoreRequest> results = queryFactory.selectFrom(storeRequest)
                .where(booleanBuilder)
                .orderBy(getOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        JPAQuery<Long> count = queryFactory.select(storeRequest.count())
                .from(storeRequest)
                .where(booleanBuilder);
        return PageableExecutionUtils.getPage(results, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(String categoryId,
                                            StoreRequestStatus status,
                                            String storeName,
                                            Long userId) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eqUserId(userId));
        booleanBuilder.and(eqCategoryId(categoryId));
        booleanBuilder.and(eqStatus(status));
        booleanBuilder.and(likeStoreName(storeName));
        return booleanBuilder;
    }

    private BooleanExpression eqUserId(Long userId) {
        return userId != null ?  storeRequest.owner.userId.eq(userId) : null;
    }

    private BooleanExpression eqCategoryId(String categoryId) {
        return categoryId != null ?  storeRequest.storeCategory.storeCategoryId.eq(categoryId) : null;
    }

    private BooleanExpression eqStatus(StoreRequestStatus status) {
        return status != null ? storeRequest.status.eq(status) : null;
    }

    private BooleanExpression likeStoreName(String storeName) {
        return storeRequest.name.like("%" + ((storeName!=null) ? storeName : "") + "%");
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
            case "userId" -> storeRequest.owner.userId;
            case "createdAt" -> storeRequest.createdAt;
            case "updatedAt" -> storeRequest.updatedAt;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_SORT_TYPE);
        };
    }
}
