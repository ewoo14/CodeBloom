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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)
    public List<AddressResponse> getUserAddresses(final long userId) {
        User user = userService.getUserOrException(userId);
        return addressRepository.findAllByUserAndIsDeleted(user, null)
                .stream()
                .map(AddressResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
<<<<<<< HEAD
=======
>>>>>>> 94c9f8b ([Feat] 유저 배송지 상세 조회 API)
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)
    public AddressResponse getAddressBy(long userId, String addressId) {
        Address address = getAddressOrException(addressId);
        checkAddressOwner(userId, address.getUser().getUserId());
        if(address.getIsDeleted()!=null) {
            throw new CodeBloomException(ErrorCode.ADDRESS_NOT_FOUND);
        }
        return AddressResponse.from(address);
    }
<<<<<<< HEAD
<<<<<<< HEAD

    @Transactional(readOnly = true)
    public Page<AddressAdminResponse> getAllAddresses(
            Pageable pageable,
            Long targetUserId,
            String city,
            Boolean isDeleted) {
        return addressRepository.findAddressesWith(pageable, targetUserId, city, isDeleted)
                .map(AddressAdminResponse::from);
    }
=======
>>>>>>> 251cce7 ([Feat] 관리자용 배송지 상세 조회 API)

    @Transactional(readOnly = true)
    public Page<AddressAdminResponse> getAllAddresses(Pageable pageable, Long targetUserId, Boolean isDeleted) {
        return addressRepository.findAddressesWith(pageable, targetUserId, isDeleted)
                .map(AddressAdminResponse::from);
    }

    @Transactional(readOnly = true)
    public AddressAdminResponse getAdminAddressBy(String addressId) {
        Address address = getAddressOrException(addressId);
        return AddressAdminResponse.from(address);
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> 251cce7 ([Feat] 관리자용 배송지 상세 조회 API)
    @Transactional
>>>>>>> 94c9f8b ([Feat] 유저 배송지 상세 조회 API)
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)
    public void updateAddress(long userId, String addressId, AddressUpdateRequest request) {
        User user = userService.getUserOrException(userId);
        Address address = getAddressOrException(addressId);
<<<<<<< HEAD
        checkAddressOwner(userId, address.getUser().getUserId());
=======
        checkAddressOwner(user, address.getUser());
>>>>>>> a896d39 ([Refactor] 기존 배송지 변경하는 메서드 분리)
        if(request.isDefault() && alreadyExistDefault(user)) {
            changeDefaultAddress(user);
        }
        address.update(request.city(), request.district(), request.streetName(),
                request.streetNumber(), request.detail(), request.isDefault()
        );
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 05451ad ([Feat] 자신의 배송지 목록 조회 API)
    public void deleteAddress(long userId, String addressId) {
        Address address = getAddressOrException(addressId);
        checkAddressOwner(userId, address.getUser().getUserId());
=======
    @Transactional
    public void deleteAddress(long userId, String addressId) {
        User user = userService.getUserOrException(userId);
        Address address = getAddressOrException(addressId);
        checkAddressOwner(user, address.getUser());
>>>>>>> 18c83f1 ([Feat] 배송지 삭제 API)
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
