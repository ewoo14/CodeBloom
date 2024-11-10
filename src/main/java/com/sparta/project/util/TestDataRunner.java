package com.sparta.project.util;

import com.sparta.project.domain.*;
import com.sparta.project.domain.enums.OrderType;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.user.UserSignupRequest;
import com.sparta.project.repository.*;
import com.sparta.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements ApplicationRunner {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @Autowired
    StoreRepository storeRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserSignupRequest userSignupRequest = new UserSignupRequest("testName", "1234", "nickname", Role.CUSTOMER);
        userService.createUser(userSignupRequest);

        User user = userRepository.findByUsername("testName").get();
        Address address = Address.builder()
                .addressId("addressId")
                .user(user)
                .build();
        addressRepository.save(address);
        Location location = Location.builder()
                .locationId("locationId")
                .name("locationName")
                .build();
        locationRepository.save(location);
        StoreCategory storeCategory = StoreCategory.builder()
                .storeCategoryId("storeCategoryId")
                .name("storeCategoryName")
                .build();
        storeCategoryRepository.save(storeCategory);
        Store store = Store.builder()
                .storeId("storeId")
                .name("storeName")
                .address("배송지")
                .owner(user)
                .location(location)
                .storeCategory(storeCategory)
                .build();
        storeRepository.save(store);
        Order order = Order.builder()
                .orderId("orderId")
                .address(address)
                .user(user)
                .store(store)
                .type(OrderType.ONLINE)
                .orderPrice(10000)
                .build();
        orderRepository.save(order);
    }
}
