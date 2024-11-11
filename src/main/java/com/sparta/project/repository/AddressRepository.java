package com.sparta.project.repository;


import com.sparta.project.domain.Address;
import com.sparta.project.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    Address findByUserAndIsDefault(User user, boolean isDefault);
    boolean existsByUserAndIsDefault(User user, boolean isDefault);
}
