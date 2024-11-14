package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.dto.location.LocationRequest;
import com.sparta.project.dto.location.LocationResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
<<<<<<< HEAD
<<<<<<< HEAD
=======
@Transactional
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
>>>>>>> 93f8c68 ([Fix] 조회 메서드에 readOnly = true 추가)
public class LocationService {

    private final LocationRepository locationRepository;

    // 운영 지역 전체 조회
<<<<<<< HEAD
<<<<<<< HEAD
    @Transactional(readOnly = true)
    public Page<LocationResponse> getAllLocations(Pageable pageable) {
=======
=======
    @Transactional(readOnly = true)
>>>>>>> 93f8c68 ([Fix] 조회 메서드에 readOnly = true 추가)
    public Page<LocationResponse> getAllLocations(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
        Page<Location> locations = locationRepository.findAll(pageable);
        return locations.map(LocationResponse::from);
    }

    // 운영 지역 상세 조회
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 93f8c68 ([Fix] 조회 메서드에 readOnly = true 추가)
    @Transactional(readOnly = true)
    public LocationResponse getLocation(String locationId) {
<<<<<<< HEAD
<<<<<<< HEAD
        Location location = getLocationOrException(locationId);
=======
    public LocationResponse getLocation(String locationId) {
<<<<<<< HEAD
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
        Location location = findLocationById(locationId);
>>>>>>> 1d1c51a ([Fix] MenuService&LocationService id 공통 검색 메서드 추가)
=======
        Location location = storeLocationService.getStoreLocationOrException(locationId);
>>>>>>> 25bb93c ([Fix] StoreLocationService 공통 메서드 사용으로 수정)
=======
        Location location = getLocationOrException(locationId);
>>>>>>> 38b9494 ([Fix] StoreLocationService 삭제 및 LocationService로의 통합)
        return LocationResponse.from(location);
    }

    // 운영 지역 생성
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 93f8c68 ([Fix] 조회 메서드에 readOnly = true 추가)
    @Transactional
    public String createLocation(LocationRequest locationRequest) {
=======
    public LocationResponse createLocation(LocationRequest locationRequest) {
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
    public String createLocation(LocationRequest locationRequest) {
>>>>>>> c7f45b5 ([Fix] Location, Menu 생성 및 수정 메서드 반환 id만으로 통일)
        boolean exists = locationRepository.existsByName(locationRequest.locationName());
        if (exists) {
            throw new CodeBloomException(ErrorCode.LOCATION_ALREADY_EXIST);
        }
        Location location = Location.create(locationRequest.locationName(), locationRequest.description());
        location = locationRepository.save(location);
<<<<<<< HEAD
<<<<<<< HEAD
        return location.getLocationId();
    }

    // 운영 지역 수정
    @Transactional
    public String updateLocation(String locationId, LocationRequest locationRequest) {
        Location location = getLocationOrException(locationId);
=======
        return LocationResponse.from(location);
    }

    // 운영 지역 수정
    public LocationResponse updateLocation(String locationId, LocationRequest locationRequest) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
        Location location = findLocationById(locationId);
>>>>>>> 1d1c51a ([Fix] MenuService&LocationService id 공통 검색 메서드 추가)
=======
        Location location = storeLocationService.getStoreLocationOrException(locationId);
>>>>>>> 25bb93c ([Fix] StoreLocationService 공통 메서드 사용으로 수정)
=======
=======
        return location.getLocationId();
    }

    // 운영 지역 수정
    @Transactional
    public String updateLocation(String locationId, LocationRequest locationRequest) {
>>>>>>> c7f45b5 ([Fix] Location, Menu 생성 및 수정 메서드 반환 id만으로 통일)
        Location location = getLocationOrException(locationId);
>>>>>>> 38b9494 ([Fix] StoreLocationService 삭제 및 LocationService로의 통합)
        boolean exists = locationRepository.existsByName(locationRequest.locationName());
        if (exists) {
            throw new CodeBloomException(ErrorCode.LOCATION_ALREADY_EXIST);
        }
        location.update(locationRequest.locationName(), locationRequest.description());
        location = locationRepository.save(location);
<<<<<<< HEAD
<<<<<<< HEAD
        return location.getLocationId();
    }

    // 운영 지역 삭제
    @Transactional
<<<<<<< HEAD
    public void deleteLocation(String locationId, Authentication authentication) {
        Location location = getLocationOrException(locationId);
        location.deleteBase(authentication.getName());
    }

    // location_id 공통 사용
    public Location getLocationOrException(String locationId) {
        return locationRepository.findById(locationId).orElseThrow(()->
                new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
=======
        return LocationResponse.from(location);
=======
        return location.getLocationId();
>>>>>>> c7f45b5 ([Fix] Location, Menu 생성 및 수정 메서드 반환 id만으로 통일)
    }

    // 운영 지역 삭제
=======
>>>>>>> 93f8c68 ([Fix] 조회 메서드에 readOnly = true 추가)
    public void deleteLocation(String locationId, Authentication authentication) {
        Location location = getLocationOrException(locationId);
        location.deleteBase(authentication.getName());
<<<<<<< HEAD
        locationRepository.save(location);
>>>>>>> 9425453 ([Feat] location 서비스 및 컨트롤러 작성, Menu 중복 방지)
=======
>>>>>>> 469ffdf ([Refactor] deleteBase 메서드 밑 save문 삭제)
    }

    // location_id 공통 사용
    public Location getLocationOrException(String locationId) {
        return locationRepository.findById(locationId).orElseThrow(()->
                new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
    }
}