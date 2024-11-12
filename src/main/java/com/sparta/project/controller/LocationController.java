package com.sparta.project.controller;

import com.sparta.project.dto.common.PageResponse;
import com.sparta.project.dto.location.LocationRequest;
import com.sparta.project.dto.location.LocationResponse;
import com.sparta.project.dto.common.ApiResponse;
import com.sparta.project.service.LocationService;
import com.sparta.project.util.PermissionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final PermissionValidator permissionValidator;

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
        LocationResponse response = locationService.getLocation(locationId);
        return ApiResponse.success(response);
    }

    // 운영 지역 생성(OWNER, MANAGER, MASTER)
    @PostMapping
    public ApiResponse<LocationResponse> createLocation(
            @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        LocationResponse response = locationService.createLocation(locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 수정(OWNER, MANAGER, MASTER)
    @PatchMapping("/{locationId}")
    public ApiResponse<LocationResponse> updateLocation(
            @PathVariable String locationId,
            @RequestBody LocationRequest locationRequest,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        LocationResponse response = locationService.updateLocation(locationId, locationRequest);
        return ApiResponse.success(response);
    }

    // 운영 지역 삭제(OWNER, MANAGER, MASTER)
    @DeleteMapping("/{locationId}")
    public ApiResponse<Void> deleteLocation(
            @PathVariable String locationId,
            Authentication authentication) {
        permissionValidator.checkPermission(authentication, "MANAGER", "MASTER");
        locationService.deleteLocation(locationId, authentication);
        return ApiResponse.success();
    }
}