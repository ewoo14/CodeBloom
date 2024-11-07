package com.sparta.project.repository.address;

import com.sparta.project.domain.Address;
import com.sparta.project.domain.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String>, AddressCustomRepository {
    List<Address> findAllByUserAndIsDeleted(User user, Boolean isDeleted);
    Address findByUserAndIsDefault(User user, boolean isDefault);
    boolean existsByUserAndIsDefault(User user, boolean isDefault);
    int countByUser(User user);
}
