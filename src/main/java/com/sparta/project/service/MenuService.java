package com.sparta.project.service;

import com.sparta.project.domain.Menu;
import com.sparta.project.domain.QMenu;
import com.sparta.project.domain.Store;
import com.sparta.project.dto.menu.MenuCreateRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.dto.menu.MenuUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.MenuRepository;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    private static final Logger log = LoggerFactory.getLogger(MenuService.class);

    // 권한 확인
    private void checkPermission(Authentication authentication, String... roles) {
        log.info("권한 검사: {}", (Object[]) roles);
        boolean hasPermission = Arrays.stream(roles)
                .anyMatch(role -> authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority(role)));
        if (!hasPermission) {
            log.info("액세스가 거부되었습니다. 현재 유저의 권한: {}", authentication.getAuthorities());
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    // 모든 메뉴 조회
    @Transactional(readOnly = true)
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size);
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
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        return MenuResponse.from(menu);
    }

    // 새로운 메뉴 생성
    @Transactional
    public MenuResponse createMenu(MenuCreateRequest menuCreateRequest, Authentication authentication) {
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        Store store = storeRepository.findById(menuCreateRequest.storeId())
                .orElseThrow(() -> new CodeBloomException(ErrorCode.STORE_NOT_FOUND));

        Menu menu = Menu.create(
                menuCreateRequest.name(),
                store,
                menuCreateRequest.description(),
                menuCreateRequest.price(),
                menuCreateRequest.isClosed()
        );
        menuRepository.save(menu);
        return MenuResponse.from(menu);
    }

    // 메뉴 정보 수정
    @Transactional
    public MenuResponse updateMenu(String menuId, MenuUpdateRequest menuUpdateRequest, Authentication authentication) {
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));

        menu.update(
                menuUpdateRequest.name(),
                menuUpdateRequest.description(),
                menuUpdateRequest.price(),
                menuUpdateRequest.isClosed()
        );
        menuRepository.save(menu);
        return MenuResponse.from(menu);
    }

    // 메뉴 삭제
    @Transactional
    public void deleteMenu(String menuId, Authentication authentication) {
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
        menuRepository.save(menu);
    }
}