package com.sparta.project.repository.storecategory;

import static com.sparta.project.domain.QStoreCategory.storeCategory;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.StoreCategory;
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
public class StoreCategoryCustomRepositoryImpl implements StoreCategoryCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<StoreCategory> findAllWith(Pageable pageable, String categoryName, Boolean isDeleted) {
        BooleanBuilder booleanBuilder = toBooleanBuilder(categoryName, isDeleted);
        List<StoreCategory> results = queryFactory.selectFrom(storeCategory)
                .where(booleanBuilder)
                .orderBy(getOrderSpecifiers(pageable)) // Sort 적용
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(storeCategory.count())
                .from(storeCategory)
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(results, pageable, count::fetchOne);
    }

    private BooleanBuilder toBooleanBuilder(String categoryName, Boolean isDeleted) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(likeName(categoryName));
        booleanBuilder.and(eqIsDeleted(isDeleted));
        return booleanBuilder;
    }

    private BooleanExpression likeName(String name) {
        return storeCategory.name.like("%" + ((name!=null) ? name  : "") + "%");
    }

    private BooleanExpression eqIsDeleted(Boolean isDeleted) {
        return isDeleted != null
                ? (isDeleted) ? storeCategory.isDeleted.isTrue() : storeCategory.isDeleted.isNull()
                : null;
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
            case "categoryName" -> storeCategory.name;
            case "createdAt" -> storeCategory.createdAt;
            case "updatedAt" -> storeCategory.updatedAt;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_SORT_TYPE);
        };
    }

}
