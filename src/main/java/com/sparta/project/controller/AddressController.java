package com.sparta.project.controller;


import com.sparta.project.domain.Address;
import com.sparta.project.dto.address.AddressCreateRequest;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.service.AddressService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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


//
//    private final AddressService addressService;
//
//    // 배송지 목록 조회(CUSTOMER, MANAGER, MASTER)
//    @GetMapping
//    public ApiResponse<PageResponse<AddressResponse>> getAllAddresses(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy) {
//        Page<AddressResponse> addresses = addressService.getAllAddresses(page, size, sortBy);
//        return ApiResponse.success(PageResponse.of(addresses));
//    }
//
//    // 배송지 상세 조회(CUSTOMER)
//    @GetMapping("/{address_id}")
//    public ApiResponse<AddressResponse> getAddressById(@PathVariable String address_id) {
//        AddressResponse address = addressService.getAddressById(address_id);
//        return ApiResponse.success(address);
//    }
//
//    // 배송지 등록(CUSTOMER)
//    @PostMapping
//    public ApiResponse<AddressResponse> createAddress(@RequestBody AddressRequest addressRequest) {
//        AddressResponse madeAddress = addressService.createAddress(addressRequest);
//        return ApiResponse.success(madeAddress);
//    }
//
//    // 배송지 수정(CUSTOMER)
//    @PatchMapping("/{address_id}")
//    public ApiResponse<AddressResponse> updateAddress(
//            @PathVariable String address_id,
//            @RequestBody AddressRequest addressRequest) {
//        AddressResponse updatedAddress = addressService.updateAddress(address_id, addressRequest);
//        return ApiResponse.success(updatedAddress);
//    }
//
//    // 배송지 삭제(CUSTOMER)
//    @DeleteMapping("/{address_id}")
//    public ApiResponse<Void> deleteAddress(@PathVariable String address_id) {
//        addressService.deleteAddress(address_id);
//        return ApiResponse.success();
//    }
}