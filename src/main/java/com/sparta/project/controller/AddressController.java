package com.sparta.project.controller;


import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.address.AddressCreateRequest;
import com.sparta.project.dto.address.AddressResponse;
import com.sparta.project.dto.address.AddressUpdateRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.service.AddressService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/my/{address_id}")
    public ApiResponse<AddressResponse> getAddress(Authentication authentication,
                                                       @PathVariable String address_id) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        AddressResponse response = addressService.getAddressBy(Long.parseLong(authentication.getName()), address_id);
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


// 배송지 목록 조회(CUSTOMER, MANAGER, MASTER)
//    @GetMapping
//    public ApiResponse<PageResponse<AddressResponse>> getAllAddresses(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<AddressResponse> addresses = addressService.getAllAddresses(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(addresses));
//    }
//
//
}