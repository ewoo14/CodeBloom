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
    // 운영 지역 전체 조회(MANAGER, MASTER)
    @GetMapping("/all")
    public ApiResponse<PageResponse<LocationResponse>> getAllLocations(
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
    @GetMapping("/all")
    public ApiResponse<PageResponse<LocationResponse>> getAllLocations(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        Page<LocationResponse> responses = locationService.getAllLocations(page, size, sortBy);
        return ApiResponse.success(PageResponse.of(responses));
    }

    // 운영 지역 상세 조회(OWNER, MANAGER, MASTER)
    @GetMapping("/{locationId}")
    public ApiResponse<LocationResponse> getLocation(
            @PathVariable String locationId) {
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        LocationResponse response = locationService.getLocation(locationId);
        return ApiResponse.success(response);
    }

<<<<<<< HEAD
    // 운영 지역 생성(MANAGER, MASTER)
    @PostMapping
    public ApiResponse<String> createLocation(
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
    @PostMapping
    public ApiResponse<LocationResponse> createLocation(
            @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER");
        LocationResponse response = locationService.createLocation(locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{locationId}")
    public ApiResponse<LocationResponse> updateLocation(
            @PathVariable String locationId,
            @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "OWNER");
        LocationResponse response = locationService.updateLocation(locationId, locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 삭제(OWNER, MANAGER, MASTER)
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
    @DeleteMapping("/{locationId}")
    public ApiResponse<Void> deleteLocation(
            @PathVariable String locationId,
            Authentication authentication) {
<<<<<<< HEAD
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
=======
        permissionValidator.checkPermission(authentication, "OWNER");
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        locationService.deleteLocation(locationId, authentication);
        return ApiResponse.success();
    }
}