package com.sparta.project.service;

import com.sparta.project.domain.Menu;
<<<<<<< HEAD
import com.sparta.project.domain.QMenu;
import com.sparta.project.domain.Store;
import com.sparta.project.dto.menu.MenuCreateRequest;
import com.sparta.project.dto.menu.MenuUpdateRequest;
=======
import com.sparta.project.dto.menu.MenuRequest;
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.MenuRepository;
<<<<<<< HEAD
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
=======
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
<<<<<<< HEAD
    private final StoreRepository storeRepository;

    // 모든 메뉴 조회
    @Transactional(readOnly = true)
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, Pageable pageable) {
        QMenu qMenu = QMenu.menu;
        // storeId : 전체일치, storeName : 부분일치
        BooleanExpression predicate = qMenu.store.storeId.eq(storeId)
                .and(qMenu.store.name.containsIgnoreCase(storeName));

        return menuRepository.findAll(predicate, pageable)
                .map(MenuResponse::from);
=======

    // 모든 메뉴 조회
    @Transactional(readOnly = true)
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return menuRepository.findAllByStoreStoreIdAndStoreName(storeId, storeName, pageable)
<<<<<<< HEAD
                .map(this::toMenuResponse);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
                .map(MenuResponse::from);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 단일 메뉴 조회
    @Transactional(readOnly = true)
    public MenuResponse getMenuById(String menuId) {
<<<<<<< HEAD
        Menu menu = getMenuOrException(menuId);
        return MenuResponse.from(menu);
=======
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
<<<<<<< HEAD
        return toMenuResponse(menu);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
        return MenuResponse.from(menu);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 새로운 메뉴 생성
    @Transactional
<<<<<<< HEAD
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
=======
    public MenuResponse createMenu(MenuRequest menuRequest) {
        Menu menu = Menu.builder()
                .name(menuRequest.name())
                .description(menuRequest.description())
                .price(menuRequest.price())
                .isClosed(menuRequest.isClosed())
                .build();
        menuRepository.save(menu);
<<<<<<< HEAD
        return toMenuResponse(menu);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
        return MenuResponse.from(menu);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 메뉴 정보 수정
    @Transactional
<<<<<<< HEAD
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
=======
    public MenuResponse updateMenu(String menuId, MenuRequest menuRequest) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
<<<<<<< HEAD
        menu.setName(menuRequest.name());
        menu.setDescription(menuRequest.description());
        menu.setPrice(menuRequest.price());
        menu.setIsClosed(menuRequest.isClosed());
        return toMenuResponse(menu);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======

        menu.update(
                menuRequest.name(),
                menuRequest.description(),
                menuRequest.price(),
                menuRequest.isClosed()
        );
        menuRepository.save(menu);
        return MenuResponse.from(menu);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 메뉴 삭제
    @Transactional
<<<<<<< HEAD
    public void deleteMenu(String menuId, Authentication authentication) {
        Menu menu = getMenuOrException(menuId);
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
    }

    // menuId 공통 활용
    public Menu getMenuOrException(String menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
=======
    public void deleteMenu(String menuId, String username) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        menu.deleteBase(username); // is_deleted를 true로 변경
        menuRepository.save(menu);
    }
<<<<<<< HEAD

    // MenuResponse DTO 변환
    private MenuResponse toMenuResponse(Menu menu) {
        return new MenuResponse(
                menu.getMenuId(),
                menu.getStore().getStoreId(),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getIsClosed()
        );
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
    }
=======
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
}