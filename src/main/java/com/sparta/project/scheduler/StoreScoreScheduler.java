package com.sparta.project.scheduler;

import com.sparta.project.repository.ReviewRepository;
import com.sparta.project.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StoreScoreScheduler {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

<<<<<<< HEAD
<<<<<<< HEAD
    // @Scheduled(fixedRate = 60 * 1000) // 1분마다 실행
=======
    //@Scheduled(fixedRate = 60 * 1000) // 1분마다 실행
>>>>>>> 4ed121a ([Feat] 평점 업데이트 - 1시간마다 스케쥴러 동작)
=======
    // @Scheduled(fixedRate = 60 * 1000) // 1분마다 실행
>>>>>>> abb6662 ([Fix] 평점을 계산할 때 소수점 두 번째 자리에서 반올림하여 "4.33333..." -> "4.3" 형태로 표현되도록 수정)
    @Scheduled(fixedRate = 60 * 60 * 1000) // 1시간마다 실행
    @Transactional
    public void updateStoreScores() {
        log.info("가게 평점 업데이트...");
        // 1. 모든 가게의 ID를 조회
        List<String> storeIds = storeRepository.findAllStoreIds();

        // 2. 각 가게별로 평균 평점 계산 및 업데이트
        for (String storeId : storeIds) {
            Double averageScore = reviewRepository.calculateAverageScoreByStoreId(storeId);
            storeRepository.updateScore(storeId, averageScore != null ? averageScore : 0.0);
        }
        log.info("가게 평점 업데이트 완료");
    }
}
