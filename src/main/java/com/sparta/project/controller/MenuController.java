package com.sparta.project.controller;

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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    private final PermissionValidator permissionValidator;

    // 음식점 메뉴 조회(ALL)
    @GetMapping
    public ApiResponse<PageResponse<MenuResponse>> getAllMenus(
            @RequestParam(value = "storeId") String storeId,
            @RequestParam(value = "storeName") String storeName,
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable) {
        Page<MenuResponse> menus = menuService.getAllMenus(storeId, storeName, pageable);
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
    public ApiResponse<String> createMenu(
            @Valid @RequestBody MenuCreateRequest menuCreateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        String newMenu = menuService.createMenu(menuCreateRequest);
        return ApiResponse.success(newMenu);
    }

    // 메뉴 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{menu_id}")
    public ApiResponse<String> updateMenu(
            @PathVariable String menu_id,
            @NotNull @RequestBody MenuUpdateRequest menuUpdateRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        String updatedMenu = menuService.updateMenu(menu_id, menuUpdateRequest);
        return ApiResponse.success(updatedMenu);
    }

    // 메뉴 삭제(OWNER, MANAGER, MASTER)
    @DeleteMapping("/{menu_id}")
    public ApiResponse<Void> deleteMenu(
            @PathVariable String menu_id,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.OWNER.name(), Role.MANAGER.name(), Role.MASTER.name());
        menuService.deleteMenu(menu_id, authentication);
        return ApiResponse.success();
=======
import com.sparta.project.dto.menu.MenuRequest;
import com.sparta.project.dto.menu.MenuResponse;
import com.sparta.project.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
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
