package com.sparta.project.controller;

<<<<<<< HEAD
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.location.LocationRequest;
import com.sparta.project.dto.location.LocationResponse;
import com.sparta.project.permission.PermissionValidator;
import com.sparta.project.service.LocationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
=======
import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.location.LocationRequest;
import com.sparta.project.dto.location.LocationResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.service.LocationService;
import com.sparta.project.util.PermissionValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final PermissionValidator permissionValidator;

<<<<<<< HEAD
<<<<<<< HEAD
    // 운영 지역 전체 조회(MANAGER, MASTER)
    @GetMapping("/all")
    public ApiResponse<PageResponse<LocationResponse>> getAllLocations(
<<<<<<< HEAD
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "createdAt", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "updatedAt", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        Page<LocationResponse> responses = locationService.getAllLocations(pageable);
        return ApiResponse.success(PageResponse.of(responses));
    }

    // 운영 지역 상세 조회(MANAGER, MASTER)
    @GetMapping("/{locationId}")
    public ApiResponse<LocationResponse> getLocation(
            @PathVariable String locationId,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
=======
    // 운영 지역 전체 조회(OWNER, MANAGER, MASTER)
=======
    // 운영 지역 전체 조회(MANAGER, MASTER)
>>>>>>> 05ce6cb ([Fix] Valid 어노테이션 컨트롤러 이동, 권한 주석 수정)
    @GetMapping("/all")
    public ApiResponse<PageResponse<LocationResponse>> getAllLocations(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
=======
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
>>>>>>> 94ce7b1 ([Fix] 각 컨트롤러 RequestParam 조건 추가)
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        Page<LocationResponse> responses = locationService.getAllLocations(page, size, sortBy);
        return ApiResponse.success(PageResponse.of(responses));
    }

    // 운영 지역 상세 조회(MANAGER, MASTER)
    @GetMapping("/{locationId}")
    public ApiResponse<LocationResponse> getLocation(
<<<<<<< HEAD
            @PathVariable String locationId) {
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
            @PathVariable String locationId,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
>>>>>>> 426a1a2 ([Fix] Location, Review 권한 조정)
        LocationResponse response = locationService.getLocation(locationId);
        return ApiResponse.success(response);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    // 운영 지역 생성(MANAGER, MASTER)
    @PostMapping
    public ApiResponse<String> createLocation(
<<<<<<< HEAD
            @Valid @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        String response = locationService.createLocation(locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 수정(MANAGER, MASTER)
    @PatchMapping("/{locationId}")
    public ApiResponse<String> updateLocation(
            @PathVariable String locationId,
            @NotNull @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        String response = locationService.updateLocation(locationId, locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 삭제(MANAGER, MASTER)
=======
    // 운영 지역 생성(OWNER, MANAGER, MASTER)
=======
    // 운영 지역 생성(MANAGER, MASTER)
>>>>>>> 05ce6cb ([Fix] Valid 어노테이션 컨트롤러 이동, 권한 주석 수정)
    @PostMapping
    public ApiResponse<LocationResponse> createLocation(
=======
>>>>>>> c7f45b5 ([Fix] Location, Menu 생성 및 수정 메서드 반환 id만으로 통일)
            @Valid @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        String response = locationService.createLocation(locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 수정(MANAGER, MASTER)
    @PatchMapping("/{locationId}")
    public ApiResponse<String> updateLocation(
            @PathVariable String locationId,
            @Valid @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        String response = locationService.updateLocation(locationId, locationRequest);
        return ApiResponse.success(response);
    }

<<<<<<< HEAD
    // 운영 지역 삭제(OWNER, MANAGER, MASTER)
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
    // 운영 지역 삭제(MANAGER, MASTER)
>>>>>>> 05ce6cb ([Fix] Valid 어노테이션 컨트롤러 이동, 권한 주석 수정)
    @DeleteMapping("/{locationId}")
    public ApiResponse<Void> deleteLocation(
            @PathVariable String locationId,
            Authentication authentication) {
<<<<<<< HEAD
<<<<<<< HEAD
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
=======
        permissionValidator.checkPermission(authentication, "OWNER");
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
>>>>>>> a76aa9b ([Fix] UUID DB 자동 할당 방식으로 변경)
        locationService.deleteLocation(locationId, authentication);
        return ApiResponse.success();
    }
}