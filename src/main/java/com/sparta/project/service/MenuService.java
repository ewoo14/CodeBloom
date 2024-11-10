package com.sparta.project.service;

import com.sparta.project.domain.Menu;
import com.sparta.project.dto.menu.MenuRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    // 모든 메뉴 조회
    @Transactional(readOnly = true)
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return menuRepository.findAllByStoreStoreIdAndStoreName(storeId, storeName, pageable)
                .map(MenuResponse::from);
    }

    // 단일 메뉴 조회
    @Transactional(readOnly = true)
    public MenuResponse getMenuById(String menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        return MenuResponse.from(menu);
    }

    // 새로운 메뉴 생성
    @Transactional
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
                .name(menuRequest.name())
                .description(menuRequest.description())
                .price(menuRequest.price())
                .isClosed(menuRequest.isClosed())
                .build();
        menuRepository.save(menu);
        return MenuResponse.from(menu);
    }

    // 메뉴 정보 수정
    @Transactional
    public MenuResponse updateMenu(String menuId, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));

        menu.update(
                menuRequest.name(),
                menuRequest.description(),
                menuRequest.price(),
                menuRequest.isClosed()
        );
        menuRepository.save(menu);
        return MenuResponse.from(menu);
    }

    // 메뉴 삭제
    @Transactional
    public void deleteMenu(String menuId, String username) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        menu.deleteBase(username); // is_deleted를 true로 변경
        menuRepository.save(menu);
    }
}