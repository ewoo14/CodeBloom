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
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;

    // 운영 지역 전체 조회
    public Page<LocationResponse> getAllLocations(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy));
        Page<Location> locations = locationRepository.findAll(pageable);
        return locations.map(LocationResponse::from);
    }

    // 운영 지역 상세 조회
    public LocationResponse getLocation(String locationId) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
        return LocationResponse.from(location);
    }

    // 운영 지역 생성
    public LocationResponse createLocation(LocationRequest locationRequest) {
        boolean exists = locationRepository.existsByName(locationRequest.locationName());
        if (exists) {
            throw new CodeBloomException(ErrorCode.LOCATION_ALREADY_EXIST);
        }
        Location location = Location.create(locationRequest.locationName(), locationRequest.description());
        location = locationRepository.save(location);
        return LocationResponse.from(location);
    }

    // 운영 지역 수정
    public LocationResponse updateLocation(String locationId, LocationRequest locationRequest) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
        boolean exists = locationRepository.existsByName(locationRequest.locationName());
        if (exists) {
            throw new CodeBloomException(ErrorCode.LOCATION_ALREADY_EXIST);
        }
        location.update(locationRequest.locationName(), locationRequest.description());
        location = locationRepository.save(location);
        return LocationResponse.from(location);
    }

    // 운영 지역 삭제
    public void deleteLocation(String locationId, Authentication authentication) {
        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
        location.deleteBase(authentication.getName());
        locationRepository.save(location);
    }
}