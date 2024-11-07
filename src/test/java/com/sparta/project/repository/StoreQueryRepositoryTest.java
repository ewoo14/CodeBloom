package com.sparta.project.repository;

import com.sparta.project.domain.Location;
import com.sparta.project.domain.Store;
import com.sparta.project.domain.StoreCategory;
import com.sparta.project.domain.User;
import com.sparta.project.domain.enums.Role;
import com.sparta.project.repository.storecategory.StoreCategoryRepository;
import com.sparta.project.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StoreQueryRepositoryTest {

    @Autowired
    StoreQueryRepository storeQueryRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    StoreCategoryRepository storeCategoryRepository;

    @BeforeEach
    void setup() {
        User owner = User.create("griotold", "1234", "nicknick", Role.OWNER);
        userRepository.save(owner);

        Location location = Location.create("서울", "서울 상세한 설명");
        locationRepository.save(location);

        StoreCategory storeCategory = StoreCategory.create("중식", "중식 전문점");
        StoreCategory storeCategory2 = StoreCategory.create("양식", "양식 전문점");
        storeCategoryRepository.saveAll(List.of(storeCategory, storeCategory2));

        Store store = Store.create("착한 중식점", "가격이 착한...", "서울 행당...", owner, storeCategory, location);

        storeRepository.save(store);

//        Menu.create("짜장면", )
    }

//    @DisplayName("카테고리, 이름, 메뉴로 검색 테스트")
//    @Test
//    void searchAllPage() {
//        // given
//
//        // when
//
//        // then
//    }

}