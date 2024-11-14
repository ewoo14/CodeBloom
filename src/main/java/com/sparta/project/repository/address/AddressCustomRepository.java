package com.sparta.project.repository.address;


import com.sparta.project.domain.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressCustomRepository {
    Page<Address> findAddressesWith(Pageable pageable, Long userId, Boolean isDeleted);
}
