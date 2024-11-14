package com.sparta.project.service;

import com.sparta.project.domain.Menu;
import com.sparta.project.domain.QMenu;
import com.sparta.project.domain.Store;
import com.sparta.project.dto.menu.MenuCreateRequest;
import com.sparta.project.dto.menu.MenuUpdateRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.MenuRepository;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    // 모든 메뉴 조회
    @Transactional(readOnly = true)
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        QMenu qMenu = QMenu.menu;
        // storeId : 전체일치, storeName : 부분일치
        BooleanExpression predicate = qMenu.store.storeId.eq(storeId)
                .and(qMenu.store.name.containsIgnoreCase(storeName));

        return menuRepository.findAll(predicate, pageable)
                .map(MenuResponse::from);
    }

    // 단일 메뉴 조회
    @Transactional(readOnly = true)
    public MenuResponse getMenuById(String menuId) {
        Menu menu = getMenuOrException(menuId);
        return MenuResponse.from(menu);
    }

    // 새로운 메뉴 생성
    @Transactional
    public String createMenu(MenuCreateRequest menuCreateRequest) {
        Store store = storeRepository.findById(menuCreateRequest.storeId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        boolean exists = menuRepository.existsByName(menuCreateRequest.name());
        if (exists) {
            throw new CodeBloomException(ErrorCode.MENU_ALREADY_EXIST);
        }

        Menu menu = Menu.create(
                menuCreateRequest.name(),
                store,
                menuCreateRequest.description(),
                menuCreateRequest.price(),
                menuCreateRequest.isClosed()
        );
        menuRepository.save(menu);
        return menu.getMenuId();
    }

    // 메뉴 정보 수정
    @Transactional
    public String updateMenu(String menuId, MenuUpdateRequest menuUpdateRequest) {
        Menu menu = getMenuOrException(menuId);
        boolean exists = menuRepository.existsByName(menuUpdateRequest.name());
        if (exists) {
            throw new CodeBloomException(ErrorCode.MENU_ALREADY_EXIST);
        }

        menu.update(
                menuUpdateRequest.name(),
                menuUpdateRequest.description(),
                menuUpdateRequest.price(),
                menuUpdateRequest.isClosed()
        );
        menuRepository.save(menu);
        return menu.getMenuId();
    }

    // 메뉴 삭제
    @Transactional
    public void deleteMenu(String menuId, Authentication authentication) {
        Menu menu = getMenuOrException(menuId);
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
        menuRepository.save(menu);
    }

    // menuId 공통 활용
    public Menu getMenuOrException(String menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
    }
}