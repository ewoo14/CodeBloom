package com.sparta.project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.QMenu;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.project.domain.QStore.store;

/**
 * QueryDSL 사용 리포지토리
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {
    private final JPAQueryFactory queryFactory;

    // todo 동적 정렬 조건 주기
    public Page<Store> searchWithPage(StoreCategory storeCategory,
                                      String storeName,
                                      String menuName,
                                      Pageable pageable) {

        BooleanBuilder booleanBuilder = toBooleanBuilder(storeCategory, storeName, menuName);
        List<Store> contents = queryFactory.selectDistinct(store)
                .from(store)
                .leftJoin(QMenu.menu).on(store.storeId.eq(QMenu.menu.store.storeId))
                .where(booleanBuilder)
                .orderBy(getOrderSpecifiers(pageable)) // Sort 적용
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(store.countDistinct())
                .from(store)
                .leftJoin(QMenu.menu).on(store.storeId.eq(QMenu.menu.store.storeId))
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);

    }

    private BooleanBuilder toBooleanBuilder(StoreCategory storeCategory, String storeName, String menuName) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eqStoreCategory(storeCategory));
        booleanBuilder.and(likeStoreName(storeName));
        booleanBuilder.and(likeMenuName(menuName));
        booleanBuilder.and(isNotDeleted());
        if (menuName != null) {
            booleanBuilder.and(isNotClosedMenu());
        }
        return booleanBuilder;
    }

    private BooleanExpression eqStoreCategory(StoreCategory storeCategory) {
        return storeCategory != null ? store.storeCategory.eq(storeCategory) : null;
    }

    private BooleanExpression likeStoreName(String storeName) {
        String condition = storeName == null ? "" : storeName;
        return store.name.like("%" + condition + "%");
    }

    private BooleanExpression likeMenuName(String menuName) {
        if (StringUtils.isBlank(menuName)) {
            return null; // menu가 null이거나 빈 문자열일 경우 조건 무시
        }
        return QMenu.menu.name.like("%" + menuName + "%");
    }

    private BooleanExpression isNotDeleted() {
        return store.isDeleted.isFalse();
    }

    private BooleanExpression isNotClosedMenu() {
        return QMenu.menu.isClosed.isFalse();
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
            case "createdAt" -> store.createdAt;
            case "updatedAt" -> store.updatedAt;
            case "name" -> store.name;
            default -> throw new CodeBloomException(ErrorCode.UNSUPPORTED_SORT_TYPE);
        };
    }
}
