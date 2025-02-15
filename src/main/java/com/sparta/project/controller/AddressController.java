package com.sparta.project.controller;


import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.address.AddressAdminResponse;
import com.sparta.project.dto.address.AddressCreateRequest;
import com.sparta.project.dto.address.AddressResponse;
import com.sparta.project.dto.address.AddressUpdateRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.ListResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

    private final PermissionValidator permissionValidator;
    private final AddressService addressService;


    @PostMapping
    public ApiResponse<Void> createAddress(Authentication authentication,
                                           @Valid @RequestBody AddressCreateRequest request) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        addressService.createAddress(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

    @GetMapping("/my")
    public ApiResponse<ListResponse<AddressResponse>> getUserAddresses(Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        List<AddressResponse> responses = addressService.getUserAddresses(Long.parseLong(authentication.getName()));
        return ApiResponse.success(ListResponse.of(responses));
    }

    // 로그인 된 유저의 자신의 배송지 조회 (CUSTOMER)
    @GetMapping("/my/{address_id}")
    public ApiResponse<AddressResponse> getAddress(Authentication authentication,
                                                   @PathVariable String address_id) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        AddressResponse response = addressService.getAddressBy(Long.parseLong(authentication.getName()), address_id);
        return ApiResponse.success(response);
    }

    // 전체 유저의 배송지 목록 조회 (MANAGER, MASTER)
    @GetMapping("")
    public ApiResponse<PageResponse<AddressAdminResponse>> getAllAddresses(
            Authentication authentication,
            @PageableDefault(size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Direction.DESC)
            })
            Pageable pageable,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "isDeleted", required = false) Boolean isDeleted) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<AddressAdminResponse> result = addressService.getAllAddresses(pageable, userId, city, isDeleted);
        return ApiResponse.success(PageResponse.of(result));
    }

    @GetMapping("/{address_id}")
    public ApiResponse<AddressAdminResponse> getAdminAddress(Authentication authentication,
                                                             @PathVariable String address_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        AddressAdminResponse response = addressService.getAdminAddressBy(address_id);
        return ApiResponse.success(response);
    }

    @PatchMapping("/{address_id}")
    public ApiResponse<Void> updateAddress(Authentication authentication,
                                           @PathVariable String address_id,
                                           @RequestBody AddressUpdateRequest request) {
        addressService.updateAddress(Long.parseLong(authentication.getName()), address_id, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/{address_id}")
    public ApiResponse<Void> deleteAddress(Authentication authentication,
                                           @PathVariable String address_id) {
        addressService.deleteAddress(Long.parseLong(authentication.getName()), address_id);
        return ApiResponse.success();
    }

}