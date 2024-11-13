package com.sparta.project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.QMenu;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
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
 * */
@Slf4j
@Repository
@RequiredArgsConstructor
public class StoreQueryRepository {
    private final JPAQueryFactory queryFactory;

    // todo 동적 정렬 조건 주기
    public Page<Store> searchWithPage(StoreCategory storeCategory,
                                      String name,
                                      String menu,
                                      Pageable pageable) {

        BooleanBuilder booleanBuilder = toBooleanBuilder(storeCategory, name, menu);
        List<Store> contents = queryFactory.selectFrom(store)
                .leftJoin(QMenu.menu).on(store.storeId.eq(QMenu.menu.store.storeId))
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(store.count())
                .from(store)
                .leftJoin(QMenu.menu).on(store.storeId.eq(QMenu.menu.store.storeId))
                .where(booleanBuilder);

        return PageableExecutionUtils.getPage(contents, pageable, count::fetchOne);

    }

    private BooleanBuilder toBooleanBuilder(StoreCategory storeCategory, String name, String menu) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(eqStoreCategory(storeCategory));
        booleanBuilder.and(likeName(name));
        booleanBuilder.and(likeMenu(menu));
        return booleanBuilder;
    }

    private BooleanExpression eqStoreCategory(StoreCategory storeCategory) {
        return storeCategory != null ? store.storeCategory.eq(storeCategory) : null;
    }

    private BooleanExpression likeName(String name) {
        String condition = name == null ? "" :name;
        return store.name.like("%" + condition + "%");
    }

    private BooleanExpression likeMenu(String menu) {
        if (StringUtils.isBlank(menu)) {
            return null; // menu가 null이거나 빈 문자열일 경우 조건 무시
        }
        return QMenu.menu.name.like("%" + menu + "%");
    }
}
