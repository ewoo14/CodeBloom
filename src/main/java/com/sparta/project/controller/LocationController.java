package com.sparta.project.controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final PermissionValidator permissionValidator;

    // 운영 지역 전체 조회(MANAGER, MASTER)
    @GetMapping("/all")
    public ApiResponse<PageResponse<LocationResponse>> getAllLocations(
            @PageableDefault(size = 5)
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
        LocationResponse response = locationService.getLocation(locationId);
        return ApiResponse.success(response);
    }

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
    @DeleteMapping("/{locationId}")
    public ApiResponse<Void> deleteLocation(
            @PathVariable String locationId,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, Role.MANAGER.name(), Role.MASTER.name());
        locationService.deleteLocation(locationId, authentication);
        return ApiResponse.success();
    }
}