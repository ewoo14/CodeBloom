package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.store.StoreResponse;
import com.sparta.project.dto.store.StoreUpdateResponse;
import com.sparta.project.exception.CodeBloomException;
import com.sparta.project.repository.LocationRepository;
import com.sparta.project.repository.StoreCategoryRepository;
import com.sparta.project.repository.StoreRepository;
import com.sparta.project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @Autowired
    StoreRepository storeRepository;

    private User owner;
    private Location location;
    private StoreCategory storeCategory;

    @BeforeEach
    void setup() {
        owner = User.create("griotold", "1234", "nicknick", Role.OWNER);
        userRepository.save(owner);

        location = Location.create("서울", "서울 상세한 설명");
        locationRepository.save(location);

        storeCategory = StoreCategory.create("중식", "중식 전문점");
        storeCategoryRepository.save(storeCategory);
    }

    @DisplayName("storeId를 입력해서 가게 정보를 받는다.")
    @Test
    void getStoreById() {
        // given
        Store store = Store.create("착한중식점", "가격이 착한 중식당", "서울시 ...", owner, storeCategory, location, 5.0);
        storeRepository.save(store);

        // when
        StoreResponse storeResponse = storeService.getStoreById(store.getStoreId());

        // then
        assertThat(storeResponse).isNotNull();
    }

    @DisplayName("storeName 과 categoryId를 수정한다.")
    @Test
    void updateStore() {
        // given
        Store store = Store.create("착한중식점", "가격이 착한 중식당", "서울시 ...", owner, storeCategory, location, 5.0);
        storeRepository.save(store);

        String storeNameForUpdate = "나쁜양식점";
        StoreCategory storeCategory2 = StoreCategory.create("양식", "양식 전문점");
        storeCategoryRepository.save(storeCategory2);

        // when
        StoreUpdateResponse storeUpdateResponse = storeService.updateStore(
                        store.getStoreId(),
                storeNameForUpdate, null, null,
                storeCategory2.getStoreCategoryId(), owner.getUserId());
        // then
        assertThat(storeUpdateResponse).isNotNull();
        assertThat(storeUpdateResponse.storeName()).isEqualTo(storeNameForUpdate);
        assertThat(storeUpdateResponse.storeCategoryId()).isEqualTo(storeCategory2.getStoreCategoryId());
    }

    @DisplayName("Customer 가 수정하려고 하면 예외 발생")
    @Test
    void updateStoreWithCustomer() {
        // given
        Store store = Store.create("착한중식점", "가격이 착한 중식당", "서울시 ...", owner, storeCategory, location, 5.0);
        storeRepository.save(store);

        String storeNameForUpdate = "나쁜양식점";
        StoreCategory storeCategory2 = StoreCategory.create("양식", "양식 전문점");
        storeCategoryRepository.save(storeCategory2);

        User customer = User.create("customer1", "1234", "nicknick", Role.CUSTOMER);
        userRepository.save(customer);
        // when
        assertThatThrownBy(() -> storeService.updateStore(
                store.getStoreId(),
                storeNameForUpdate, null, null,
                storeCategory2.getStoreCategoryId(), customer.getUserId()))
                .isInstanceOf(CodeBloomException.class);

    }
}