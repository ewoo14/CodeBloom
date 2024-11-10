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

import java.util.List;

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
    private StoreCategory testStoreCategory2;
    private Location testLocation;
    private Location testLocation2;

    @BeforeEach
    void setUp() {
        testUser = createUser("griotold");
        userRepository.save(testUser);

        testStoreCategory = createStoreCategory("storeCategoryId", "중식", "중식 전문점");
        testStoreCategory2 = createStoreCategory("storeCategoryId2", "양식", "양식 전문점");
        storeCategoryRepository.saveAll(List.of(testStoreCategory, testStoreCategory2));

        testLocation = createLocation("locationId", "서울", "서울의 상세한 설명");
        testLocation2 = createLocation("locationId2", "인천", "인천의 상세한 설명");
        locationRepository.saveAll(List.of(testLocation, testLocation2));
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

    @DisplayName("수정 - storeName, description, locationId, storeCategoryId")
    @Test
    void updateStore() {
        // given
        String storeId = "storeId";
        String name = "착한중식당";
        Double score = 5.0;
        Store store = createStore(storeId, name, testUser, testStoreCategory, testLocation, score);
        storeRepository.save(store);

        String storeNameForUpdate = "나쁜레스토랑";
        String descriptionForUpdate = "수정한 상세 설명";
        String locationIdForUpdate = "locationId2";
        String storeCategoryIdForUpdate = "storeCategoryId2";

        // when
        StoreUpdateResponse storeUpdateResponse
                = storeService.updateStore(storeId, storeNameForUpdate, descriptionForUpdate, locationIdForUpdate, storeCategoryIdForUpdate);

        // then
        assertThat(storeUpdateResponse.storeName()).isEqualTo(storeNameForUpdate);
        assertThat(storeUpdateResponse.description()).isEqualTo(descriptionForUpdate);
        assertThat(storeUpdateResponse.locationId()).isEqualTo(locationIdForUpdate);
        assertThat(storeUpdateResponse.storeCategoryId()).isEqualTo(storeCategoryIdForUpdate);
    }

    @DisplayName("삭제하면 isDeleted 는 true가 된다.")
    @Test
    void test() {
        // given
        String storeId = "storeId";
        String name = "착한중식당";
        Double score = 5.0;
        Store store = createStore(storeId, name, testUser, testStoreCategory, testLocation, score);
        storeRepository.save(store);

        // when
        storeService.deleteStore(storeId, testUser.getUsername());

        // then
        Store deletedStore = storeRepository.findById(storeId).get();
        assertThat(deletedStore.getIsDeleted()).isTrue();
        assertThat(deletedStore.getDeletedBy()).isEqualTo(testUser.getUsername());
    }

    private User createUser(String username) {
        return User.create(username, "1234", "닉네임", Role.CUSTOMER);
    }

    private StoreCategory createStoreCategory(String storeCategoryId, String name, String description) {
        return StoreCategory.create(storeCategoryId, name, description);
    }

    private Location createLocation(String locationId, String name, String description) {
        return Location.create(locationId, name, description);
    }

    private Store createStore(String storeId, String name, User owner, StoreCategory storeCategory, Location location, Double score) {
        return Store.create(storeId, name, "상세 설명", "주소지", owner, storeCategory, location, score);
    }


}