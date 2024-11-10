package com.sparta.project.service;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.store.StoreResponse;
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
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreCategoryRepository storeCategoryRepository;

    @Autowired
    private LocationRepository locationRepository;

    private User testUser;
    private StoreCategory testStoreCategory;
    private Location testLocation;

    @BeforeEach
    void setUp() {
        testUser = createUser("griotold");
        userRepository.save(testUser);
        testStoreCategory = createStoreCategory("중식", "중식 전문점");
        storeCategoryRepository.save(testStoreCategory);
        testLocation = createLocation("서울", "서울의 상세한 설명");
        locationRepository.save(testLocation);
    }

    @DisplayName("storeId를 입력해서 상세 조회하기")
    @Test
    void getByStoreId() {
        // given
        String storeId = "storeId";
        String name = "착한중식당";
        Double score = 5.0;
        Store store = createStore(storeId, name, testUser, testStoreCategory, testLocation, score);
        storeRepository.save(store);

        // when
        StoreResponse storeResponse = storeService.getStoreById(storeId);

        // then
        assertThat(storeResponse).isNotNull();
        assertThat(storeResponse.storeName()).isEqualTo(name);
        assertThat(storeResponse.score()).isEqualTo(score);
    }

    @DisplayName("storeId가 없으면 예외 발생")
    @Test
    void getByStoreId2() {
        // given
        String storeId = "storeId";
        String name = "착한중식당";
        Double score = 5.0;
        Store store = createStore(storeId, name, testUser, testStoreCategory, testLocation, score);
        storeRepository.save(store);

        String wrongStoreId = "wrongStoreId";
        // when & then
        assertThatThrownBy(() -> storeService.getStoreById(wrongStoreId))
                .isInstanceOf(CodeBloomException.class)
                .hasMessage("일치하는 가게 정보가 존재하지 않습니다.");
    }

    private User createUser(String username) {
        return User.create(username, "1234", "닉네임", Role.CUSTOMER);
    }

    private StoreCategory createStoreCategory(String name, String description) {
        return StoreCategory.create("storeCategoryId", name, description);
    }

    private Location createLocation(String name, String description) {
        return Location.create("locationId", name, description);
    }

    private Store createStore(String storeId, String name, User owner, StoreCategory storeCategory, Location location, Double score) {
        return Store.create(storeId, name, "상세 설명", "주소지", owner, storeCategory, location, score);
    }


}