package com.sparta.project.service;


import com.sparta.project.domain.Location;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreLocationService {
    private final LocationRepository locationRepository;

    public Location getStoreLocationOrException(String locationId) {
        return locationRepository.findById(locationId).orElseThrow(()->
                new CodeBloomException(ErrorCode.LOCATION_NOT_FOUND));
    }

}
