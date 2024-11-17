<<<<<<< HEAD
<<<<<<< HEAD
package com.sparta.project.controller;

<<<<<<< HEAD
<<<<<<< HEAD

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
=======
import com.sparta.project.dto.address.AddressRequest;
import com.sparta.project.dto.address.AddressResponse;
=======
import com.sparta.project.dto.AddressRequest;
import com.sparta.project.dto.AddressResponse;
>>>>>>> d0a8147 ([Fix] controller 내 dto 경로 수정)
import com.sparta.project.service.AddressService;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
=======
import lombok.RequiredArgsConstructor;
>>>>>>> 92aac64 ([Fix] : Autowired -> ReqArgsCons 변경)
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< HEAD
<<<<<<< HEAD
=======
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
>>>>>>> 79fb384 ([Feat] 배송지 생성 API)
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {

<<<<<<< HEAD
    private final PermissionValidator permissionValidator;
    private final AddressService addressService;


    @PostMapping
    public ApiResponse<Void> createAddress(Authentication authentication,
                                           @Valid @RequestBody AddressCreateRequest request) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
=======
    private final AddressService addressService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<Void> createAddress(Authentication authentication,
                                              @Valid @RequestBody AddressCreateRequest request) {
>>>>>>> 79fb384 ([Feat] 배송지 생성 API)
        addressService.createAddress(Long.parseLong(authentication.getName()), request);
        return ApiResponse.success();
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)
    @GetMapping("/my")
    public ApiResponse<ListResponse<AddressResponse>> getUserAddresses(Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        List<AddressResponse> responses = addressService.getUserAddresses(Long.parseLong(authentication.getName()));
        return ApiResponse.success(ListResponse.of(responses));
    }
<<<<<<< HEAD
=======
    @PatchMapping("/{address_id}")
    public ApiResponse<Void> updateAddress(Authentication authentication,
                                           @PathVariable String address_id,
                                           @RequestBody AddressUpdateRequest request) {
        addressService.updateAddress(Long.parseLong(authentication.getName()), address_id, request);
        return ApiResponse.success();
    }

>>>>>>> c2e8bda ([Feat] 배송지 수정 API)
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)

    // 로그인 된 유저의 자신의 배송지 조회 (CUSTOMER)
    @GetMapping("/my/{address_id}")
    public ApiResponse<AddressResponse> getAddress(Authentication authentication,
                                                   @PathVariable String address_id) {
=======
    @GetMapping("/my/{address_id}")
    public ApiResponse<AddressResponse> getAddress(Authentication authentication,
                                                       @PathVariable String address_id) {
>>>>>>> 94c9f8b ([Feat] 유저 배송지 상세 조회 API)
        permissionValidator.checkPermission(authentication, Role.CUSTOMER.name());
        AddressResponse response = addressService.getAddressBy(Long.parseLong(authentication.getName()), address_id);
        return ApiResponse.success(response);
    }
<<<<<<< HEAD
<<<<<<< HEAD

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
=======
>>>>>>> 251cce7 ([Feat] 관리자용 배송지 상세 조회 API)

    // 전체 유저의 배송지 목록 조회 (MANAGER, MASTER)
    @GetMapping("")
    public ApiResponse<PageResponse<AddressAdminResponse>> getAllAddresses(
            Authentication authentication,
            @PageableDefault(size = 5)
            @SortDefault(sort = "createdAt", direction = Direction.DESC)
            Pageable pageable,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "isDeleted", required = false) Boolean isDeleted) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<AddressAdminResponse> result = addressService.getAllAddresses(pageable, userId, isDeleted);
        return ApiResponse.success(PageResponse.of(result));
    }

    @GetMapping("/{address_id}")
    public ApiResponse<AddressAdminResponse> getAdminAddress(Authentication authentication,
                                                             @PathVariable String address_id) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        AddressAdminResponse response = addressService.getAdminAddressBy(address_id);
        return ApiResponse.success(response);
    }

<<<<<<< HEAD
=======
>>>>>>> 94c9f8b ([Feat] 유저 배송지 상세 조회 API)
=======
>>>>>>> 251cce7 ([Feat] 관리자용 배송지 상세 조회 API)
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

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
@RequiredArgsConstructor
>>>>>>> 92aac64 ([Fix] : Autowired -> ReqArgsCons 변경)
@RequestMapping("/users/address")
public class AddressController {

    private AddressService addressService;

    // 전체 배송지 정보 불러오기
    @GetMapping
    public List<AddressResponse> getAllAddresses() {
        return addressService.getAllAddresses();
    }
    // 배송지 상세 조회
    @GetMapping("/{address_id}")
    public AddressResponse getAddressById(@PathVariable String address_id) {
        return addressService.getAddressById(address_id);
    }

    // 배송지 등록
    @PostMapping
    public AddressResponse addAddress(@RequestBody AddressRequest addressRequest) {
        return addressService.addAddress(addressRequest);
    }

    // 배송지 수정
    @PatchMapping("/{address_id}")
    public AddressResponse updateAddress(@PathVariable String address_id, @RequestBody AddressRequest addressRequest) {
        return addressService.updateAddress(address_id, addressRequest);
    }

    // 배송지 삭제
    @DeleteMapping("/{address_id}")
    public void deleteAddress(@PathVariable String address_id) {
        addressService.deleteAddress(address_id);
    }
>>>>>>> c220516 ([Feat] : 컨트롤러 초안 작성)
}
=======
//package com.sparta.project.controller;
//
//import com.sparta.project.dto.address.AddressRequest;
//import com.sparta.project.dto.address.AddressResponse;
//import com.sparta.project.dto.common.ApiResponse;
//import com.sparta.project.dto.common.PageResponse;
//import com.sparta.project.service.AddressService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/addresses")
//public class AddressController {
=======

>>>>>>> 79fb384 ([Feat] 배송지 생성 API)
//
//
//    // 배송지 목록 조회(CUSTOMER, MANAGER, MASTER)
=======

// 배송지 목록 조회(CUSTOMER, MANAGER, MASTER)
>>>>>>> 94c9f8b ([Feat] 유저 배송지 상세 조회 API)
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
<<<<<<< HEAD
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
<<<<<<< HEAD
//}
>>>>>>> 5f194e3 ([Fix] AI명세서대로 컨트롤러 초안 수정)
=======
}
>>>>>>> 79fb384 ([Feat] 배송지 생성 API)
=======
}
>>>>>>> 18c83f1 ([Feat] 배송지 삭제 API)
=======
}
>>>>>>> f68b3e1 ([Feat] 전체 유저의 배송지 목록 조회 API)
