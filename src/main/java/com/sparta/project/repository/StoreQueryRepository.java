package com.sparta.project.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.project.domain.QStore;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QueryDSL 사용 리포지토리
 * */
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
        List<Store> contents = queryFactory.selectFrom(QStore.store)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> count = queryFactory.select(QStore.store.count())
                .from(QStore.store)
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
        return storeCategory != null ? QStore.store.storeCategory.eq(storeCategory) : null;
    }

    private BooleanExpression likeName(String name) {
        String condition = name == null ? "" :name;
        return QStore.store.name.like("%" + condition + "%");
    }

    private BooleanExpression likeMenu(String menu) {
        String condition = menu == null ? "" :menu;
        return QStore.store.name.like("%" + condition + "%");
    }
}
