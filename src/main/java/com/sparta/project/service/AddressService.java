package com.sparta.project.service;


import com.sparta.project.domain.Address;
import com.sparta.project.domain.User;
import com.sparta.project.dto.address.AddressAdminResponse;
import com.sparta.project.dto.address.AddressCreateRequest;
import com.sparta.project.dto.address.AddressResponse;
import com.sparta.project.dto.address.AddressUpdateRequest;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.exception.ErrorCode;
import com.sparta.project.repository.address.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final UserService userService;
    private final AddressRepository addressRepository;


    public void createAddress(final long userId, final AddressCreateRequest request) {
        User user = userService.getUserOrException(userId);
        if(overMaxAddress(user)) {
            throw new CodeBloomException(ErrorCode.EXCEED_MAXIMUM_ADDRESS);
        }
        if(request.isDefault() && alreadyExistDefault(user)) {
            changeDefaultAddress(user);
        }
        addressRepository.save(Address.create(
                user, request.city(), request.district(), request.streetName(),
                request.streetNumber(), request.detail(), request.isDefault()
        ));
    }

    @Transactional(readOnly = true)
    public List<AddressResponse> getUserAddresses(final long userId) {
        User user = userService.getUserOrException(userId);
        return addressRepository.findAllByUserAndIsDeleted(user, null)
                .stream()
                .map(AddressResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AddressResponse getAddressBy(long userId, String addressId) {
        Address address = getAddressOrException(addressId);
        checkAddressOwner(userId, address.getUser().getUserId());
        if(address.getIsDeleted()!=null) {
            throw new CodeBloomException(ErrorCode.ADDRESS_NOT_FOUND);
        }
        return AddressResponse.from(address);
    }

    @Transactional(readOnly = true)
    public AddressAdminResponse getAdminAddressBy(String addressId) {
        Address address = getAddressOrException(addressId);
        return AddressAdminResponse.from(address);
    }

    public void updateAddress(long userId, String addressId, AddressUpdateRequest request) {
        User user = userService.getUserOrException(userId);
        Address address = getAddressOrException(addressId);
        checkAddressOwner(userId, address.getUser().getUserId());
        if(request.isDefault() && alreadyExistDefault(user)) {
            changeDefaultAddress(user);
        }
        address.update(request.city(), request.district(), request.streetName(),
                request.streetNumber(), request.detail(), request.isDefault()
        );
    }

    public void deleteAddress(long userId, String addressId) {
        Address address = getAddressOrException(addressId);
        checkAddressOwner(userId, address.getUser().getUserId());
        address.deleteBase(String.valueOf(userId));
    }

    private boolean overMaxAddress(final User user) {
        return addressRepository.countByUser(user) > 5;
    }

    private boolean alreadyExistDefault(final User user) {
        return addressRepository.existsByUserAndIsDefault(user, true);
    }

    private void changeDefaultAddress(User user) {
        Address defaultAddress = addressRepository.findByUserAndIsDefault(user, true);
        defaultAddress.updateDefault(false);
    }

    private void checkAddressOwner(long requestUserId, long ownerUserId) {
        if(requestUserId != ownerUserId) {
            throw new CodeBloomException(ErrorCode.FORBIDDEN_ACCESS);
        }
    }

    public Address getAddressOrException(final String addressId) {
        return addressRepository.findById(addressId).orElseThrow(()->
                new CodeBloomException(ErrorCode.ADDRESS_NOT_FOUND)
        );
    }

}
