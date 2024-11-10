<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.menu.MenuCreateRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.dto.menu.MenuUpdateRequest;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.MenuService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
=======
package com.sparta.project.controller;

import com.sparta.project.dto.menu.MenuCreateRequest;
import com.sparta.project.dto.menu.MenuUpdateRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
<<<<<<< HEAD
import org.springframework.security.core.context.SecurityContextHolder;
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
<<<<<<< HEAD
    private final PermissionValidator permissionValidator;
=======
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)

    // 음식점 메뉴 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<MenuResponse>> getAllMenus(
<<<<<<< HEAD
            @RequestParam(value = "storeId") String storeId,
            @RequestParam(value = "storeName") String storeName,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        Page<MenuResponse> menus = menuService.getAllMenus(storeId, storeName, pageable);
=======
            @RequestParam("storeId") String storeId,
            @RequestParam("storeName") String storeName,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        Page<MenuResponse> menus = menuService.getAllMenus(storeId, storeName, page, size, sortBy);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
        return ApiResponse.success(PageResponse.of(menus));
    }

    // 메뉴 단건 조회(ALL)
    @GetMapping("/{menu_id}")
    public ApiResponse<MenuResponse> getMenuById(@PathVariable String menu_id) {
        MenuResponse menu = menuService.getMenuById(menu_id);
        return ApiResponse.success(menu);
    }

    // 메뉴 추가(OWNER, MANAGER, MASTER)
    @PostMapping
<<<<<<< HEAD
<<<<<<< HEAD
    public ApiResponse<String> createMenu(
            @Valid @RequestBody MenuCreateRequest menuCreateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        String newMenu = menuService.createMenu(menuCreateRequest);
=======
    public ApiResponse<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest) {
        MenuResponse newMenu = menuService.createMenu(menuRequest);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
    public ApiResponse<MenuResponse> createMenu(
            @RequestBody MenuCreateRequest menuCreateRequest,
            Authentication authentication) {
        MenuResponse newMenu = menuService.createMenu(menuCreateRequest, authentication);
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
        return ApiResponse.success(newMenu);
    }

    // 메뉴 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{menu_id}")
<<<<<<< HEAD
    public ApiResponse<String> updateMenu(
            @PathVariable String menu_id,
            @NotNull @RequestBody MenuUpdateRequest menuUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        String updatedMenu = menuService.updateMenu(menu_id, menuUpdateRequest);
=======
    public ApiResponse<MenuResponse> updateMenu(
            @PathVariable String menu_id,
<<<<<<< HEAD
            @RequestBody MenuRequest menuRequest) {
        MenuResponse updatedMenu = menuService.updateMenu(menu_id, menuRequest);
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
=======
            @RequestBody MenuUpdateRequest menuUpdateRequest,
            Authentication authentication) {
        MenuResponse updatedMenu = menuService.updateMenu(menu_id, menuUpdateRequest, authentication);
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
        return ApiResponse.success(updatedMenu);
    }

    // 메뉴 삭제(OWNER, MANAGER, MASTER)
    @DeleteMapping("/{menu_id}")
<<<<<<< HEAD
<<<<<<< HEAD
    public ApiResponse<Void> deleteMenu(
            @PathVariable String menu_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        menuService.deleteMenu(menu_id, authentication);
        return ApiResponse.success();
=======
import com.sparta.project.dto.menu.MenuRequest;
import com.sparta.project.dto.menu.MenuResponse;
=======
import com.sparta.project.dto.MenuRequest;
import com.sparta.project.dto.MenuResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private MenuService menuService;

    // 음식점 메뉴 조회
    @GetMapping("/stores/{store_id}")
    public List<MenuResponse> getMenusByStore(@PathVariable String store_id) {
        return menuService.getMenusByStore(store_id);
    }

    // 메뉴 단건 조회
    @GetMapping("/{menu_id}")
    public MenuResponse getMenuById(@PathVariable String menu_id) {
        return menuService.getMenuById(menu_id);
    }

    // 메뉴 추가
    @PostMapping
    public MenuResponse addMenu(@RequestBody MenuRequest menuRequest) {
        return menuService.addMenu(menuRequest);
    }

    // 메뉴 수정
    @PatchMapping("/{menu_id}")
    public MenuResponse updateMenu(@PathVariable String menu_id, @RequestBody MenuRequest menuRequest) {
        return menuService.updateMenu(menu_id, menuRequest);
    }

    // 메뉴 삭제
    @DeleteMapping("/{menu_id}")
    public void deleteMenu(@PathVariable String menu_id) {
        menuService.deleteMenu(menu_id);
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
    }
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.menu.MenuRequest;
//import com.sparta.project.dto.menu.MenuResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.MenuService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/menu")
//public class MenuController {
//
//    private final MenuService menuService;
//
//    // 음식점 메뉴 조회(ALL)
//    @GetMapping
//    public ApiResponse<PageResponse<MenuResponse>> getAllMenus(
//            @RequestParam("storeId") String storeId,
//            @RequestParam("storeName") String storeName,
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<MenuResponse> menus = menuService.getAllMenus(storeId, storeName, page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(menus));
//    }
//
//    // 메뉴 단건 조회(ALL)
//    @GetMapping("/{menu_id}")
//    public ApiResponse<MenuResponse> getMenuById(@PathVariable String menu_id) {
//        MenuResponse menu = menuService.getMenuById(menu_id);
//        return ApiResponse.success(menu);
//    }
//
//    // 메뉴 추가(OWNER, MANAGER, MASTER)
//    @PostMapping
//    public ApiResponse<MenuResponse> createMenu(@RequestBody MenuRequest menuRequest) {
//        MenuResponse newMenu = menuService.createMenu(menuRequest);
//        return ApiResponse.success(newMenu);
//    }
//
//    // 메뉴 수정(OWNER, MANAGER, MASTER)
//    @PatchMapping("/{menu_id}")
//    public ApiResponse<MenuResponse> updateMenu(
//            @PathVariable Long menu_id,
//            @RequestBody MenuRequest menuRequest) {
//        MenuResponse updatedMenu = menuService.updateMenu(menu_id, menuRequest);
//        return ApiResponse.success(updatedMenu);
//    }
//
//    // 메뉴 삭제(OWNER, MANAGER, MASTER)
//    @DeleteMapping("/{menu_id}")
//    public ApiResponse<Void> deleteMenu(@PathVariable String menu_id) {
//        menuService.deleteMenu(menu_id);
//        return ApiResponse.success();
//    }
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
    public ApiResponse<Void> deleteMenu(@PathVariable String menu_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        menuService.deleteMenu(menu_id, username);
=======
    public ApiResponse<Void> deleteMenu(
            @PathVariable String menu_id,
            Authentication authentication) {
        menuService.deleteMenu(menu_id, authentication);
>>>>>>> 5c260d6 ([Fix] MenuRequest 객체 분리 & 권한 로직 추가 & UUID 수도 부여)
        return ApiResponse.success();
    }
}
>>>>>>> 5fcfbf6 ([Feat] menu dto와 service 코드 작성)
