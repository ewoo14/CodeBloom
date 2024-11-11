package com.sparta.project.service;


import com.sparta.project.domain.Address;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.address.AddressCreateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final UserService userService;
    private final AddressRepository addressRepository;


    @Transactional
    public void createAddress(final long userId, final AddressCreateRequest request) {
        User user = userService.getUserOrException(userId);
        if(user.getRole() != Role.CUSTOMER) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
        if(overMaxAddress(user)) {
            throw new CodeBloomException(ErrorCode.EXCEED_MAXIMUM_ADDRESS);
        }
        if(request.isDefault() && alreadyExistDefault(user)) {
            Address defaultAddress = addressRepository.findByUserAndIsDefault(user, true);
            defaultAddress.updateDefault(false);
        }
        addressRepository.save(Address.create(
                user, request.city(), request.district(), request.streetName(),
                request.streetNumber(), request.detail(), request.isDefault()
        ));
    }

    private boolean overMaxAddress(final User user) {
        return addressRepository.countByUser(user) > 5;
    }

    private boolean alreadyExistDefault(final User user) {
        return addressRepository.existsByUserAndIsDefault(user, true);
    }

}
