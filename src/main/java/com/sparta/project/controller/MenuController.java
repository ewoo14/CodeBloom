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
