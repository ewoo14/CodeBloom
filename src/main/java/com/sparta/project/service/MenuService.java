package com.sparta.project.service;

<<<<<<< HEAD
import com.querydsl.core.types.dsl.BooleanExpression;
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
import com.sparta.project.domain.Menu;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.QMenu;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
import com.sparta.project.domain.Store;
import com.sparta.project.dto.menu.MenuCreateRequest;
<<<<<<< HEAD
import com.sparta.project.dto.menu.MenuUpdateRequest;
=======
=======
import com.sparta.project.domain.QMenu;
>>>>>>> dc05aea ([Fix] 전체 메뉴 조회에 queryDSL 적용)
import com.sparta.project.dto.menu.MenuRequest;
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
import com.sparta.project.dto.menu.MenuCreateRequest;
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
import com.sparta.project.dto.menu.MenuResponse;
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
import com.sparta.project.dto.menu.MenuUpdateRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.MenuRepository;
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
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
=======
import com.sparta.project.repository.StoreRepository;
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
=======
>>>>>>> c1fc115 ([Feat] 주문 요청)
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
import com.querydsl.core.types.dsl.BooleanExpression;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> dc05aea ([Fix] 전체 메뉴 조회에 queryDSL 적용)
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
>>>>>>> c1fc115 ([Feat] 주문 요청)

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
<<<<<<< HEAD
<<<<<<< HEAD
    private final StoreRepository storeRepository;
=======
    private final StoreRepository storeRepository;

<<<<<<< HEAD
    private static final Logger log = LoggerFactory.getLogger(MenuService.class);
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)

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

=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
    // 모든 메뉴 조회
    @Transactional(readOnly = true)
<<<<<<< HEAD
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, Pageable pageable) {
=======
    public Page<MenuResponse> getAllMenus(String storeId, String storeName, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
>>>>>>> 0ebca46 ([Feat] 리뷰 생성 및 조회 구현)
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
<<<<<<< HEAD
        return menuRepository.findAllByStoreStoreIdAndStoreName(storeId, storeName, pageable)
<<<<<<< HEAD
                .map(this::toMenuResponse);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
=======
        QMenu qMenu = QMenu.menu;
        // storeId : 전체일치, storeName : 부분일치
        BooleanExpression predicate = qMenu.store.storeId.eq(storeId)
                .and(qMenu.store.name.containsIgnoreCase(storeName));

        return menuRepository.findAll(predicate, pageable)
>>>>>>> dc05aea ([Fix] 전체 메뉴 조회에 queryDSL 적용)
                .map(MenuResponse::from);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 단일 메뉴 조회
    @Transactional(readOnly = true)
    public MenuResponse getMenuById(String menuId) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> e7b4e74 ([Fix] 파싱오류 변경)
        Menu menu = getMenuOrException(menuId);
        return MenuResponse.from(menu);
=======
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
<<<<<<< HEAD
        return toMenuResponse(menu);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
=======
        Menu menu = findMenuById(menuId);
>>>>>>> 1d1c51a ([Fix] MenuService&LocationService id 공통 검색 메서드 추가)
        return MenuResponse.from(menu);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 새로운 메뉴 생성
    @Transactional
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
    public MenuResponse createMenu(MenuCreateRequest menuCreateRequest, Authentication authentication) {
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
<<<<<<< HEAD
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
        Menu menu = Menu.builder()
                .name(menuCreateRequest.name())
                .description(menuCreateRequest.description())
                .price(menuCreateRequest.price())
                .isClosed(menuCreateRequest.isClosed())
                .build();
=======
=======
    public MenuResponse createMenu(MenuCreateRequest menuCreateRequest) {
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
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
>>>>>>> 9863864 ([Fix] Menu Service 메서드별 테스트 완성)
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
<<<<<<< HEAD
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
=======
    public MenuResponse updateMenu(String menuId, MenuUpdateRequest menuUpdateRequest, Authentication authentication) {
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
=======
    public MenuResponse updateMenu(String menuId, MenuUpdateRequest menuUpdateRequest) {
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
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

=======
        Menu menu = findMenuById(menuId);
>>>>>>> 1d1c51a ([Fix] MenuService&LocationService id 공통 검색 메서드 추가)
=======
        Menu menu = getMenuOrException(menuId);
>>>>>>> e7b4e74 ([Fix] 파싱오류 변경)
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
        return MenuResponse.from(menu);
>>>>>>> 679a9be ([Fix] 정적 팩토리 메서드 방식 적용)
    }

    // 메뉴 삭제
    @Transactional
<<<<<<< HEAD
<<<<<<< HEAD
    public void deleteMenu(String menuId, Authentication authentication) {
<<<<<<< HEAD
<<<<<<< HEAD
        Menu menu = getMenuOrException(menuId);
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
    }

    // menuId 공통 활용
    public Menu getMenuOrException(String menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
=======
    public void deleteMenu(String menuId, String username) {
=======
    public void deleteMenu(String menuId, Authentication authentication) {
<<<<<<< HEAD
        checkPermission(authentication, "OWNER", "MANAGER", "MASTER");
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
=======
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
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
=======
        Menu menu = findMenuById(menuId);
=======
        Menu menu = getMenuOrException(menuId);
>>>>>>> e7b4e74 ([Fix] 파싱오류 변경)
        menu.deleteBase(authentication.getName()); // is_deleted를 true로 변경
        menuRepository.save(menu);
    }

    // menuId 공통 활용
    public Menu getMenuOrException(String menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.MENU_NOT_FOUND));
    }
>>>>>>> 1d1c51a ([Fix] MenuService&LocationService id 공통 검색 메서드 추가)
}