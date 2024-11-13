package com.sparta.project.service;

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.dto.store.StoreResponse;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.repository.LocationRepository;
import com.sparta.project.repository.StoreRepository;
import com.sparta.project.repository.storecategory.StoreCategoryRepository;
import com.sparta.project.repository.user.UserRepository;
=======
=======
import com.sparta.project.dto.store.StoreUpdateResponse;
>>>>>>> f337ba7 ([Feat] 음식점 정보 수정 기능 Service)
import com.sparta.project.exception.CodeBloomException;
=======
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
=======
import com.sparta.project.dto.store.StoreUpdateResponse;
import com.sparta.project.exception.CodeBloomException;
>>>>>>> 2ae41d6 ([Feat] 음식점 정보 수정 기능 service)
=======
import com.sparta.project.dto.store.StoreUpdateRequest;
import com.sparta.project.dto.store.StoreUpdateResponse;
>>>>>>> 5bd2e4b ([Feat] 음식점 정보 수정 완료)
import com.sparta.project.repository.LocationRepository;
import com.sparta.project.repository.StoreCategoryRepository;
import com.sparta.project.repository.StoreRepository;
import com.sparta.project.repository.UserRepository;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
import org.junit.jupiter.api.BeforeEach;
=======
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
import org.assertj.core.api.Assertions;
=======
>>>>>>> 2ae41d6 ([Feat] 음식점 정보 수정 기능 service)
import org.junit.jupiter.api.BeforeEach;
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
<<<<<<< HEAD
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
<<<<<<< HEAD
=======
import static org.assertj.core.api.Assertions.assertThatThrownBy;
>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)

=======
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
import static org.assertj.core.api.Assertions.assertThat;

>>>>>>> 2ae41d6 ([Feat] 음식점 정보 수정 기능 service)
@Transactional
@ActiveProfiles("test")
@SpringBootTest
class StoreServiceTest {

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
    @Autowired
    private StoreService storeService;

    @Autowired
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
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
<<<<<<< HEAD
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

        StoreUpdateRequest storeUpdateRequest =
                new StoreUpdateRequest("나쁜양식점", null, null, storeCategory2.getStoreCategoryId());
        // when
        String storeId = storeService.updateStore(store.getStoreId(), storeUpdateRequest);

        // then
        Store updated = storeRepository.findById(storeId).get();
        assertThat(updated).isNotNull();
        assertThat(updated.getName()).isEqualTo(storeNameForUpdate);
        assertThat(updated.getStoreCategory().getName()).isEqualTo("양식");
    }

    @DisplayName("storeId 와 userId를 받아서 ")
    @Test
    void deleteStore() {
        // given
        Store store = Store.create("착한중식점", "가격이 착한 중식당", "서울시 ...", owner, storeCategory, location, 5.0);
        storeRepository.save(store);

        // when
        storeService.deleteStore(store.getStoreId(), owner.getUserId());

        // then
        assertThat(store.getIsDeleted()).isTrue();
        assertThat(store.getDeletedBy()).isEqualTo(String.valueOf(owner.getUserId()));
    }

=======
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
=======
    @DisplayName("테스트")
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
>>>>>>> 3a1c1e0 ([Feat] 음식점 상세 조회 기능)
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

        StoreUpdateRequest storeUpdateRequest =
                new StoreUpdateRequest("나쁜양식점", null, null, storeCategory2.getStoreCategoryId());
        // when
        StoreUpdateResponse storeUpdateResponse = storeService.updateStore(store.getStoreId(), storeUpdateRequest);

        // then
        assertThat(storeUpdateResponse).isNotNull();
        assertThat(storeUpdateResponse.storeName()).isEqualTo(storeNameForUpdate);
        assertThat(storeUpdateResponse.storeCategoryId()).isEqualTo(storeCategory2.getStoreCategoryId());
    }

<<<<<<< HEAD
<<<<<<< HEAD
    @DisplayName("Customer 가 수정하려고 하면 예외 발생")
    @Test
    void updateStoreWithCustomer() {
=======
    @DisplayName("storeId 와 userId를 받아서 ")
    @Test
    void deleteStore() {
>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)
        // given
        Store store = Store.create("착한중식점", "가격이 착한 중식당", "서울시 ...", owner, storeCategory, location, 5.0);
        storeRepository.save(store);

<<<<<<< HEAD
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
<<<<<<< HEAD

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


>>>>>>> b8d01e9 ([Feat] 음식점 상세 조회 기능 Service 및 ServiceTest)
=======
>>>>>>> 377f60d ([Build] ErrorCode 정돈, Location, StoreCategory, Store 의 id 자동 생성 되도록)
=======
>>>>>>> 5bd2e4b ([Feat] 음식점 정보 수정 완료)
=======
        // when
        storeService.deleteStore(store.getStoreId(), owner.getUserId());

        // then
        assertThat(store.getIsDeleted()).isTrue();
        assertThat(store.getDeletedBy()).isEqualTo(String.valueOf(owner.getUserId()));
    }

>>>>>>> c80f876 ([Feat] 음식점 정보 삭제 완료)
}