-- 첫 CUSTOMER 계정의 user_id는 23이며, username은 'firstman'입니다. / 32, 33은 삭제된 계정입니다.
INSERT INTO p_user(user_id, username, password, nickname, role, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
(1, 'master', '1234', '마스터계정', 'MASTER', false, '2024-09-15 16:38:19', 'master', '2024-09-18 13:45:31', 'master', null, null),
(2, 'manager001', '1234', '윤수현 매니저', 'MANAGER', false, '2024-09-15 17:05:20', 'manager001', '2024-09-15 20:03:15', 'manager001', null, null),
(3, 'manager002', '1234', '김동현 매니저', 'MANAGER', false, '2024-09-15 12:00:07', 'manager002', '2024-09-15 19:31:48', 'manager002', null, null),
(4, 'manager003', '1234', '김연수 매니저', 'MANAGER', false, '2024-09-15 14:10:50', 'manager003', '2024-09-15 20:05:18', 'manager003', null, null),
(5, 'manager004', '1234', '이하연 매니저', 'MANAGER', false, '2024-09-15 14:12:40', 'manager004', '2024-09-15 19:42:59', 'manager004', null, null),
(6, 'manager005', '1234', '김지수 매니저', 'MANAGER', false, '2024-09-16 14:29:42', 'manager005', '2024-09-16 15:28:06', 'manager005', null, null),
(7, 'chickeng1', '1234', '치킨킹 강남점', 'OWNER', false, '2024-09-20 07:40:26', 'chickeng1', '2024-09-20 10:57:01', 'chickeng1', null, null),
(8, 'bgk051', '1234', '버거킹 해운대우동점', 'OWNER', false, '2024-09-20 07:51:08', 'bgk051', '2024-09-20 08:13:48', 'bgk051', null, null),
(9, 'gyogyo3', '1234', '교촌 마린시티점', 'OWNER', false, '2024-09-20 08:01:31', 'gyogyo3', '2024-09-27 18:31:47', 'gyogyo3', null, null),
(10, 'street77', '1234', '7번가피자 우동점', 'OWNER', false, '2024-09-20 08:02:07', 'street77', '2024-09-20 08:06:57', 'street77', null, null),
(11, 'zzambbong2', '1234', '여빈 본점', 'OWNER', false, '2024-09-20 08:13:52', 'zzambbong2', '2024-09-21 11:21:20', 'zzambbong2', null, null),
(12, 'bestkatsu5', '1234', '톤쇼우 해운대점', 'OWNER', false, '2024-09-20 08:15:28', 'bestkatsu5', '2024-09-20 08:30:35', 'bestkatsu5', null, null),
(13, 'siuuuuu2', '1234', '호식이 해운대우동', 'OWNER', false, '2024-09-20 08:29:13', 'siuuuuu2', '2024-09-20 10:30:36', 'siuuuuu2', null, null),
(14, 'momstouch3', '1234', '맘스터치 해운대마린', 'OWNER', false, '2024-09-20 08:42:28', 'momstouch3', '2024-10-01 15:30:27', 'momstouch3', null, null),
(15, 'outdark1', '1234', 'outdark', 'OWNER', false, '2024-09-20 08:52:10', 'outdark1', '2024-09-20 08:52:10', 'outdark1', null, null),
(16, 'kyochobo4', '1234', '교촌 연산4호점', 'OWNER', false, '2024-09-20 08:54:43', 'kyochobo4', '2024-09-21 13:10:36', 'kyochobo4', null, null),
(17, 'thunderbol', '1234', '썬더치킨 연산5호점', 'OWNER', false, '2024-09-20 09:07:53', 'thunderbol', '2024-09-20 09:11:56', 'thunderbol', null, null),
(18, 'domino24', '1234', 'domino24', 'OWNER', false, '2024-09-20 09:30:41', 'domino24', '2024-09-20 09:30:41', 'domino24', null, null),
(19, 'manshon10', '1234', '카츠맨숀10', 'OWNER', false, '2024-09-20 10:12:41', 'manshon10', '2024-09-20 10:16:37', 'manshon10', null, null),
(20, 'maramara3', '1234', '탕화쿵푸마라탕벡스코', 'OWNER', false, '2024-09-20 11:18:55', 'maramara3', '2024-09-20 15:03:10', 'maramara3', null, null),
(21, 'bbqstar5', '1234', 'BBQ 서면스타', 'OWNER', false, '2024-09-20 12:30:37', 'bbqstar5', '2024-09-29 19:07:02', 'bbqstar5', null, null),
(22, 'bbqmarina', '1234', 'BBQ 부산마리나', 'OWNER', false, '2024-09-20 13:11:33', 'bbqmarina', '2024-09-20 13:41:17', 'bbqmarina', null, null),
(23, 'firstman', '1234', '퍼스트클래스', 'CUSTOMER', false, '2024-09-20 21:00:07', 'firstman', '2024-10-17 01:31:30', 'firstman', null, null),
(24, 'bhcudong', '1234', 'BHC 해운대우동점', 'OWNER', false, '2024-09-21 09:02:26', 'bhcudong', '2024-09-21 11:44:27', 'bhcudong', null, null),
(25, 'afterp1zza', '1234', '피자스쿨 마린시티', 'OWNER', false, '2024-09-23 12:43:52', 'afterp1zza', '2024-09-24 00:15:57', 'afterp1zza', null, null),
(26, 'yourenerg2', '1234', '암유어에너지', 'CUSTOMER', false, '2024-09-23 13:45:12', 'yourenerg2', '2024-09-24 01:45:22', 'yourenerg2', null, null),
(27, 'tri3angle3', '1234', '트라이앵글', 'CUSTOMER', false, '2024-09-23 14:23:45', 'tri3angle3', '2024-09-27 12:10:15', 'tri3angle3', null, null),
(28, 'king9queen', '1234', '맛있으면짖는개', 'CUSTOMER', false, '2024-09-24 15:55:33', 'king9queen', '2024-10-13 02:45:50', 'king9queen', null, null),
(29, 'darimi2', '1234', 'darimi2', 'CUSTOMER', false, '2024-09-24 16:30:18', 'darimi2', '2024-09-24 16:30:18', 'darimi2', null, null),
(30, 'r0borob0', '1234', '헤이1헤이2', 'CUSTOMER', false, '2024-09-26 09:43:37', 'r0borob0', '2024-10-05 00:13:16', 'r0borob0', null, null),
(31, 'pizzking', '1234', '피자킹 강남점', 'OWNER', false, '2024-09-30 18:06:55', 'pizzking', '2024-10-11 12:18:47', 'pizzking', null, null),
(32, 'deleteSoon', '1234', '삭제된 커스터머1', 'CUSTOMER', true, '2024-10-09 15:46:17', 'deleteSoon', '2024-11-01 03:17:18', 'deleteSoon', '2024-11-01 03:17:18', 'deleteSoon'),
(33, 'deleteS00n', '1234', '삭제된 오너1', 'OWNER', true, '2024-10-10 09:08:54', 'deleteS00n', '2024-11-01 13:13:58', 'deleteS00n', '2024-11-01 13:13:58', 'deleteS00n'),
(34, 'sup3rp0wer', '1234', '힘을내요슈퍼파월', 'CUSTOMER', false, '2024-10-11 17:05:22', 'sup3rp0wer', '2024-10-27 03:55:10', 'sup3rp0wer', null, null)
;

-- 주소도 'firstman' 계정의 user_id인 23만 있습니다.
INSERT INTO p_address (address_id, user_id, city, district, street_name, street_number, detail, is_default, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('3d6eb26a-5cb2-47a7-8322-b599cea55d29', 23, '부산', '해운대구', '해운대로483번길', '10', '해운대롯데아파트 102동 1405호', true, false, '2024-09-23 15:30:00', 'firstman', '2024-09-23 15:30:00', 'firstman', null, null),
('29626d8f-fc31-4071-8f7b-8e95ad1bde1f', 23, '부산', '연제구', '고분로', '280', '부산센텀푸르지오아파트 101동 502호', false, false, '2024-09-29 17:52:03', 'firstman', '2024-09-29 18:02:55', 'firstman', null, null),
('871c9048-90f3-40e6-9acc-0e593d444844', 23, '부산', '해운대구', 'APEC로', '55', '벡스코 제1전시장 1홀', false, false, '2024-10-10 11:10:34', 'firstman', '2024-10-10 11:10:34', 'firstman', null, null),
('c18db69e-cfa3-486f-9057-958b2215f36a', 23, '부산', '해운대구', '마린시티1로', '51', '파크하얏트부산호텔 906호', false, true, '2024-10-12 19:27:04', 'firstman', '2024-10-12 19:27:04', 'firstman', '2024-10-14 21:35:12', 'firstman'),
('701575a0-e51d-4d1d-ad3b-795db2381fc5', 23, '부산', '부산진구', '가야대로', '772', '롯데호텔 부산 1613호', false, true, '2024-10-18 19:39:51', 'firstman', '2024-10-18 19:39:51', 'firstman', '2024-10-23 00:28:08', 'firstman'),
('d18af435-ef43-454b-9168-1f04bcbe7b9f', 23, '부산', '해운대구', '중동1로', '35-1', '부산은행 해운대금융센터 1층', false, false, '2024-10-21 11:39:58', 'firstman', '2024-10-21 11:45:02', 'firstman', null, null)
;

INSERT INTO p_store_category (store_category_id, name, description, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('18352dc9-6260-47c0-bf37-5e2b1a3835ab', '치킨', '국민들의 사랑을 받는 치느님, 치킨', false, '2024-09-16 09:40:26', 'master', '2024-09-16 09:40:26', 'master', null, null),
('e44e9b61-2276-4672-ae5f-5b4c39bea887', '중식', '오옹? 이거 빠스잖아? 중식', false, '2024-09-16 09:44:52', 'master', '2024-09-16 09:44:52', 'master', null, null),
('b790f04e-1dc7-4715-a3dd-963ce18a0322', '일식·돈까스', '남자들의 영원한 소울푸드, 돈까스와 일식', false, '2024-09-16 09:49:28', 'master', '2024-09-16 09:49:28', 'master', null, null),
('88950c47-1d62-4138-967e-84fabefadc7b', '피자', '쭉 늘어나는 치즈! 피자', false, '2024-09-16 09:55:08', 'master', '2024-09-16 09:55:08', 'master', null, null),
('02a0a155-8404-404a-9f4c-d5b7eb17a7f7', '패스트푸드', '바쁠 땐 역시 햄버거, 패스트푸드', false, '2024-09-16 09:59:08', 'master', '2024-09-16 09:59:08', 'master', null, null),
('c7f7bc74-168e-497a-8ca9-a44d29d81efc', '찜·탕', '든든하게 해장하고 가시죠, 찜 또는 탕', false, '2024-09-16 10:02:07', 'master', '2024-09-16 10:02:07', 'master', null, null),
('5331a1d1-f78c-44f5-8532-8f65b01e4311', '족발·보쌈', '유튜버 말왕도 좋아하는 왕족발 보쌈~', false, '2024-09-16 10:09:13', 'master', '2024-09-16 10:09:13', 'master', null, null),
('16ceea8f-687e-45f5-ac56-734af49e4d5f', '분식', '라면 먹고 갈래? 아니 난 떡볶이! 분식', false, '2024-09-16 10:22:46', 'master', '2024-09-16 10:22:46', 'master', null, null),
('371e6824-21a5-4357-af1a-4fdfb5cbd3d0', '카페·디저트', '디저트 배는 따로 있는 거 아시죠? 카페와 디저트', false, '2024-09-16 10:30:02', 'master', '2024-09-16 10:30:02', 'master', null, null)
;

-- 여기 location 기준을 잘 몰라서 일단 시-구 단위로 넣었습니다.
INSERT INTO p_location (location_id, name, description, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('e36b87a7-26ee-472e-a062-e5bc13ec8be0', '서울 강남구', '서울 강남구', false, '2024-09-16 10:52:29', 'master', '2024-09-16 10:52:29', 'master', null, null),
('a1a1776f-fcb7-4f51-8139-ff770f6fd514', '서울 서대문구', '서울 서대문구', false, '2024-09-16 10:52:36', 'master', '2024-09-16 10:52:36', 'master', null, null),
('a11fd4dc-fbf2-4d72-950a-e96915961a29', '부산 해운대구', '부산 해운대구', false, '2024-09-16 10:52:47', 'master', '2024-09-16 10:52:47', 'master', null, null),
('6fc829e7-251e-46e6-9ea9-84c8c9a9f6cb', '부산 연제구', '부산 연제구', false, '2024-09-16 10:53:01', 'master', '2024-09-16 10:53:01', 'master', null, null),
('b0ed68d3-d786-47b9-b70e-6d7df37c772c', '부산 부산진구', '부산 부산진구', false, '2024-09-16 10:53:13', 'master', '2024-09-16 10:53:13', 'master', null, null),
('c04dd92f-476d-4172-b841-9aa9dcc0b220', '부산 남구', '부산 남구', false, '2024-09-16 10:53:28', 'master', '2024-09-16 10:53:28', 'master', null, null),
('3d43a55e-079e-4efe-a508-6f260dd42302', '부산 수영구', '부산 수영구', false, '2024-09-16 10:53:43', 'master', '2024-09-16 10:53:43', 'master', null, null)
;

INSERT INTO p_store_request (store_request_id, is_approved, name, description, address, owner_id, store_category_id, location_id, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('ce6ae142-ab8c-4c2c-9c25-06c3497b890f', true, '치킨킹 강남점', '강남에서 가장 인기 있는 치킨집!\n신선한 재료와 독특한 양념을 체험해보세요', '서울 강남구 테헤란로1길 28-5', 7, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'e36b87a7-26ee-472e-a062-e5bc13ec8be0', true, '2024-09-20 08:05:52', 'chickeng1', '2024-09-20 10:05:26', 'manager001', '2024-09-20 10:05:26', 'manager001'),
('d2f3e46c-8311-4a11-aab0-fdd8238ef963', true, '버거킹 해운대우동점', '버거킹! 해운대에서 맛 볼 수 있습니다!', '부산 해운대구 해운대로452번길 16', 8, '02a0a155-8404-404a-9f4c-d5b7eb17a7f7', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:16:26', 'bgk051', '2024-09-20 10:07:08', 'manager002', '2024-09-20 10:07:08', 'manager002'),
('6d9f1f98-5d8f-4870-8f8c-eb5610f64c9d', true, '7번가피자 우동점', '해운대 신한 휴플러스 오피스텔 맞은편에 횡단보도 앞에 위치해있습니다.', '부산 해운대구 해운대로 547-1', 10, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:17:31', 'street77', '2024-09-20 10:06:07', 'manager004', '2024-09-20 10:06:07', 'manager004'),
('d2290c6e-66c9-4dc3-9662-cfe31b760cd7', true, '톤쇼우 해운대점', '부산 돈카츠 찐 맛집! 줄 서서 먹는 그 맛집!\n톤쇼우가 해운대에서도 오픈하게 되었습니다!', '부산 해운대구 구남로25번길 19', 12, 'b790f04e-1dc7-4715-a3dd-963ce18a0322', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:23:13', 'bestkatsu5', '2024-09-20 10:20:28', 'manager002', '2024-09-20 10:20:28', 'manager002'),
('a8c3d544-9d53-4c40-bcdd-2399bb02bc58', true, '교촌치킨 마린시티점', '국민 치킨 교촌치킨을 바다를 보면서 즐길 수 있는 마린시티점입니다.', '부산 해운대구 마린시티1로 155 해운대현대 하이페리온 C동 101호', 9, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:24:04', 'gyogyo3', '2024-09-20 10:09:31', 'manager003', '2024-09-20 10:09:31', 'manager003'),
('da193b04-9d68-4a31-8eb4-4e2c18d496a6', true, '짬뽕전문점 여빈 본점', '신선한 재료로 만든 짬뽕 국물이 일품인 짬뽕 전문점입니다.', '부산 해운대구 해운대해변로 163 상가동 108호', 11, 'e44e9b61-2276-4672-ae5f-5b4c39bea887', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:24:23', 'zzambbong2', '2024-09-20 10:18:52', 'manager001', '2024-09-20 10:18:52', 'manager001'),
('f6735dea-99d8-48fe-997c-bb15f0e17b9b', true, '호식이두마리치킨 해운대우동점', '1인1닭? 커플이선 2인2닭! 호식이 두마리 치킨으로 오세요!\n동백역 4번 출구의 마블피트니스건물 1층에 있습니다.', '부산 해운대구 해운대로469번길 8 1층', 13, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 08:50:18', 'siuuuuu2', '2024-09-20 10:34:13', 'manager003', '2024-09-20 10:34:13', 'manager003'),
('95c0a8ea-e049-4688-b352-05aaf067813d', true, '아웃닭 서면점', '치킨은 "아웃닭"\n방문해주셔서 감사합니다!', '부산 부산진구 동천로85번길 21 1층 아웃닭', 15, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'b0ed68d3-d786-47b9-b70e-6d7df37c772c', true, '2024-09-20 09:02:23', 'outdark1', '2024-09-20 11:02:10', 'manager001', '2024-09-20 11:02:10', 'manager001'),
('09fa98b1-a435-4542-ade6-81dbd2a0ba88', true, '썬더치킨 연산5호점', '치킨&호프 썬더치킨 연산5호점입니다.', '부산 연제구 과정로 308', 17, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', '6fc829e7-251e-46e6-9ea9-84c8c9a9f6cb', true, '2024-09-20 09:35:21', 'thunderbol', '2024-09-20 11:00:53', 'manager003', '2024-09-20 11:00:53', 'manager003'),
('089e50bf-15ce-4f28-837f-dee1f0754139', true, '도미노피자 센텀점', '* 신제품5종 배달앱 주문 불가 *\n- 매장으로 전화주세요', '부산 해운대구 해운대로 535 1층 도미노피자', 18, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 09:35:37', 'domino24', '2024-09-20 10:45:41', 'manager004', '2024-09-20 10:45:41', 'manager004'),
('005064c2-25b3-4545-b620-237f9862f1f8', true, '맘스터치 해운대마린시티점', '맘스터치 해운대마린시티점입니다^^\n문의사항은 편하게 연락주세요 ^^', '부산 해운대구 마린시티2로 2 마린파크 109호', 14, '02a0a155-8404-404a-9f4c-d5b7eb17a7f7', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 09:52:28', 'momstouch3', '2024-09-20 11:30:50', 'manager004', '2024-09-20 11:30:50', 'manager004'),
('fb902fb5-de42-40d9-8df7-0c44b24de3b3', true, '카츠맨숀10 센텀시티점', '진심을 담아내는 카츠\n카츠맨숀10 센텀시티점입니다.', '부산 해운대구 센텀3로 20 1층 102-3호', 19, 'b790f04e-1dc7-4715-a3dd-963ce18a0322', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 10:19:19', 'manshon10', '2024-09-20 12:07:41', 'manager001', '2024-09-20 12:07:41', 'manager001'),
('059d44bf-0cc2-4cc8-8eb5-9d1b62ca16c7', true, '탕화쿵푸마라탕 벡스코점', '마라의 깊은 맛을 느끼실 수 있는!\n[탕화쿵푸마라탕 벡스코점] 인사드립니다 ^^', '부산 해운대구 해운대로394번길 17 해운대센텀 미진이지비아 아파트 105동 102호', 20, 'e44e9b61-2276-4672-ae5f-5b4c39bea887', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 11:27:01', 'maramara3', '2024-09-20 12:09:55', 'manager002', '2024-09-20 12:09:55', 'manager002'),
('0c820908-83ed-4f41-90bc-6faba49277c4', true, '교촌치킨 연산4호점', '*새로 오픈한 교촌치킨 연산4호점*', '부산 연제구 과정로 280-1 1층', 16, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', '6fc829e7-251e-46e6-9ea9-84c8c9a9f6cb', true, '2024-09-20 11:50:12', 'kyochobo4', '2024-09-20 12:30:43', 'manager002', '2024-09-20 12:30:43', 'manager002'),
('c11229bf-8fd8-45b3-be09-81a7007768b5', true, 'BBQ치킨 부산서면스타점', '올리브와 치킨이 만나 기분이 좋아~\n\n안녕하세요, BBQ치킨 부산서면스타점입니다!', '부산 부산진구 서전로10번길 16 1층', 21, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'b0ed68d3-d786-47b9-b70e-6d7df37c772c', true, '2024-09-20 12:37:40', 'bbqstar5', '2024-09-20 14:33:37', 'manager003', '2024-09-20 14:33:37', 'manager003'),
('b0b73c41-3e62-494a-b00e-47b22ce52cf0', true, 'BBQ부산마리나점', 'BBQ부산마리나점은 해운대구청에서 실시하는 푸른신호등 시범참여업소로 항상 깨끗한 튀김유 관리로 최상의 올리브유를 사용하여 건강하고 맛있는 치킨으로 고객님들께 보답하고 있습니다.', '부산 해운대구 해운대해변로 141', 22, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-20 14:46:10', 'bbqmarina', '2024-09-20 15:05:50', 'manager004', '2024-09-20 15:05:50', 'manager004'),
('987026a1-65f3-43f5-a903-347a7152084f', true, 'BHC치킨 해운대우동점', 'BHC 해운대우동점을 찾아주셔서 진심으로 감사드립니다.', '부산 해운대구 해운대로483번가길 5', 24, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-21 09:25:09', 'bhcudong', '2024-09-21 10:05:50', 'manager001', '2024-09-21 10:05:50', 'manager001'),
('4ef91780-ae21-44e4-97de-beee6018a158', true, '피자스쿨 마린시티점', '안녕하세요 피자스쿨 마린시티점입니다.', '부산 해운대구 마린시티3로 1 116호', 25, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', true, '2024-09-23 13:17:36', 'afterp1zza', '2024-09-23 13:35:50', 'manager004', '2024-09-23 13:35:50', 'manager004'),
('da4f1a66-588a-499f-83b4-a7a480e68264', false, '피자킹 강남점', '피자킹 강남점 오픈 예정입니다.', '서울 강남구 논현로102길 12 2층 215호', 31, '88950c47-1d62-4138-967e-84fabefadc7b', 'e36b87a7-26ee-472e-a062-e5bc13ec8be0', false, '2024-10-03 12:35:50', 'pizzking', '2024-09-20 14:35:50', 'manager001', null, null)
;

INSERT INTO p_store (store_id, name, description, address, owner_id, store_category_id, location_id, score, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('fa813a3c-1f9f-441c-9fff-5f1d40ea5f1e', '치킨킹 강남점', '강남에서 가장 인기 있는 치킨집!\n신선한 재료와 독특한 양념을 체험해보세요', '서울 강남구 테헤란로1길 28-5', 7, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'e36b87a7-26ee-472e-a062-e5bc13ec8be0', 0.0, false, '2024-09-20 10:05:26', 'manager001', '2024-09-20 10:05:26', 'manager001', null, null),
('fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '버거킹 해운대우동점', '버거킹! 해운대에서 맛 볼 수 있습니다!\n현재 리뷰이벤트 활발하게 진행중!\n리뷰 이벤트 참여는 요청사항에 아래의 항목에서 선택하여 번호를 적어주세요.', '부산 해운대구 해운대로452번길 16', 8, '02a0a155-8404-404a-9f4c-d5b7eb17a7f7', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:07:08', 'manager002', '2024-09-27 09:50:36', 'bgk051', null, null),
('87e7b574-a9ba-4034-9358-9898f9954b03', '7번가피자 우동점', '해운대 신한 휴플러스 오피스텔 맞은편에 횡단보도 앞에 위치해있습니다.', '부산 해운대구 해운대로 547-1', 10, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:06:07', 'manager004', '2024-09-20 10:06:07', 'manager004', null, null),
('d140dbd1-525b-4a49-ab05-f1702fa4d666', '톤쇼우 해운대점', '부산 돈카츠 찐 맛집! 줄 서서 먹는 그 맛집!\n톤쇼우가 해운대에서도 오픈하게 되었습니다!', '부산 해운대구 구남로25번길 19', 12, 'b790f04e-1dc7-4715-a3dd-963ce18a0322', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:20:28', 'manager002', '2024-09-20 10:20:28', 'manager002', null, null),
('4d8e7f37-b235-409c-8ab9-c3531627c11f', '교촌치킨 마린시티점', '국민 치킨 교촌치킨을 바다를 보면서 즐길 수 있는 마린시티점입니다.', '부산 해운대구 마린시티1로 155 해운대현대 하이페리온 C동 101호', 9, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:09:31', 'manager003', '2024-09-20 10:09:31', 'manager003', null, null),
('ce9aa567-32cb-405b-bc71-ca1892b5db55', '짬뽕전문점 여빈 본점', '신선한 재료로 만든 짬뽕 국물이 일품인 짬뽕 전문점입니다.', '부산 해운대구 해운대해변로 163 상가동 108호', 11, 'e44e9b61-2276-4672-ae5f-5b4c39bea887', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:18:52', 'manager001', '2024-09-20 10:18:52', 'manager001', null, null),
('3f18d8ac-50f1-466b-860a-78d9a073b0a9', '호식이두마리치킨 해운대우동점', '1인1닭? 커플이선 2인2닭! 호식이 두마리 치킨으로 오세요!\n\n주문 요청사항에 리뷰이벤트 요청하시면 콜라 1캔을 드리고 있습니다.', '부산 해운대구 해운대로469번길 8 1층', 13, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:34:13', 'manager003', '2024-10-07 02:57:08', 'siuuuuu2', null, null),
('451e6375-f574-441c-b4b8-a970d6d562da', '아웃닭 서면점', '치킨은 "아웃닭"\n방문해주셔서 감사합니다!', '부산 부산진구 동천로85번길 21 1층 아웃닭', 15, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'b0ed68d3-d786-47b9-b70e-6d7df37c772c', 0.0, false, '2024-09-20 11:02:10', 'manager001', '2024-09-20 11:02:10', 'manager001', null, null),
('c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '썬더치킨 연산5호점', '치킨\&호프 썬더치킨 연산5호점입니다.', '부산 연제구 과정로 308', 17, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', '6fc829e7-251e-46e6-9ea9-84c8c9a9f6cb', 0.0, false, '2024-09-20 11:00:53', 'manager003', '2024-09-20 11:00:53', 'manager003', null, null),
('e673b8fd-64bf-4f52-8660-b715a35fca58', '도미노피자 센텀점', '* 신제품5종 배달앱 주문 불가 *\n- 매장으로 전화주세요\n\n필독!\n* 피클, 핫소스, 갈릭디핑소스는 선택하신 분들만 제공됩니다.\n\n* 제품에 문제가 있거나, 누락될 경우 매장으로 전화주시면 빠르게 해결해드립니다.', '부산 해운대구 해운대로 535 1층 도미노피자', 18, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 10:45:41', 'manager004', '2024-09-29 23:30:31', 'domino24', null, null),
('71a472a6-df53-4c0d-b9f0-5c6d0a16e7ab', '맘스터치 해운대마린시티점', '맘스터치 해운대마린시티점입니다^^\n문의사항은 편하게 연락주세요 ^^', '부산 해운대구 마린시티2로 2 마린파크 109호', 14, '02a0a155-8404-404a-9f4c-d5b7eb17a7f7', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 11:30:50', 'manager004', '2024-09-20 11:30:50', 'manager004', null, null),
('7c7ddc1b-f520-4aee-b6f8-f8adbf624190', '카츠맨숀10 센텀시티점', '리뷰를 다시면 재구매시 아메리카노 서비스\n진심을 담아내는 카츠\n카츠맨숀10 센텀시티점입니다.', '부산 해운대구 센텀3로 20 1층 102-3호', 19, 'b790f04e-1dc7-4715-a3dd-963ce18a0322', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 12:07:41', 'manager001', '2024-09-23 08:36:23', 'manshon10', null, null),
('ce0336e5-0b06-4ead-b5cf-81bf7650bcfc', '탕화쿵푸마라탕 벡스코점', '마라의 깊은 맛을 느끼실 수 있는!\n\[탕화쿵푸마라탕 벡스코점\] 인사드립니다 ^^', '부산 해운대구 해운대로394번길 17 해운대센텀 미진이지비아 아파트 105동 102호', 20, 'e44e9b61-2276-4672-ae5f-5b4c39bea887', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 12:09:55', 'manager002', '2024-09-20 12:09:55', 'manager002', null, null),
('007cf483-3afc-4276-8352-a419ccb75c84', '교촌치킨 연산4호점', '*새로 오픈한 교촌치킨 연산4호점*\n\n교촌치킨 신규 가맹점 사업자 교육 부문\n"최우수가맹점"으로 선정된 연산4호점입니다.', '부산 연제구 과정로 280-1 1층', 16, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', '6fc829e7-251e-46e6-9ea9-84c8c9a9f6cb', 0.0, false, '2024-09-20 12:30:43', 'manager002', '2024-09-20 22:25:15', 'kyochobo4', null, null),
('ea156e21-133b-496d-855a-ec3863216a4f', 'BBQ치킨 부산서면스타점', '올리브와 치킨이 만나 기분이 좋아~\n\n안녕하세요, BBQ치킨 부산서면스타점입니다!', '부산 부산진구 서전로10번길 16 1층', 21, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'b0ed68d3-d786-47b9-b70e-6d7df37c772c', 0.0, false, '2024-09-20 14:33:37', 'manager003', '2024-09-20 14:33:37', 'manager003', null, null),
('14281a5a-f044-4758-8fae-e349ac055bd1', 'BBQ부산마리나점', 'BBQ부산마리나점은 해운대구청에서 실시하는 푸른신호등 시범참여업소로 항상 깨끗한 튀김유 관리로 최상의 올리브유를 사용하여 건강하고 맛있는 치킨으로 고객님들께 보답하고 있습니다.', '부산 해운대구 해운대해변로 141', 22, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-20 15:05:50', 'manager004', '2024-09-20 15:05:50', 'manager004', null, null),
('67c0e78e-4629-4b85-bfc3-009926127ca5', 'BHC치킨 해운대우동점', 'BHC 해운대우동점을 찾아주셔서 진심으로 감사드립니다.', '부산 해운대구 해운대로483번가길 5', 24, '18352dc9-6260-47c0-bf37-5e2b1a3835ab', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-21 10:05:50', 'manager001', '2024-09-21 10:05:50', 'manager001', null, null),
('619bd66a-7994-4938-a19a-eec74f066133', '피자스쿨 마린시티점', '안녕하세요 피자스쿨 마린시티점입니다.', '부산 해운대구 마린시티3로 1 116호', 25, '88950c47-1d62-4138-967e-84fabefadc7b', 'a11fd4dc-fbf2-4d72-950a-e96915961a29', 0.0, false, '2024-09-23 13:35:50', 'manager004', '2024-09-23 13:35:50', 'manager004', null, null)
;

INSERT INTO p_menu (menu_id, store_id, name, description, price, is_closed, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
-- 버거킹 해운대우동점
('2e01f8b8-56e3-4059-9f3a-eee5207b8576', 'fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '와퍼세트', '불에 직접 구운 순 쇠고기 패티에 싱싱한 야채가 한가득~ 버거킹의 대표 메뉴!', 9100, false, false, '2024-09-20 10:27:17', 'bgk051', '2024-09-20 10:27:17', 'bgk051', null, null),
('1be6241e-bcea-4dfe-b602-1c5f714664fb', 'fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '콰트로치즈와퍼세트', '진짜 불맛을 즐겨라, 4가지 고품격 치즈와 불에 직접 구운 와퍼 패티의 만남!', 9900, false, false, '2024-09-20 10:31:13', 'bgk051', '2024-09-20 10:31:13', 'bgk051', null, null),
('e2192a23-643e-41d6-8aa7-722ec0b78119', 'fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '치즈와퍼세트', '불에 직접 구운 순 쇠고기 패티가 들어간 와퍼에 고소한 치즈까지!', 9700, false, false, '2024-09-20 10:36:08', 'bgk051', '2024-09-20 10:36:08', 'bgk051', null, null),
('065cc41f-21df-4626-ac55-478c8b226480', 'fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '와퍼주니어세트', '불에 직접 구운 순 쇠고기 패티가 들어간 와퍼의 주니어 버전~ 작지만 꽉 찼다!', 6700, false, false, '2024-09-20 10:45:19', 'bgk051', '2024-09-20 10:45:19', 'bgk051', null, null),
('61887208-f03e-42c2-96f4-a88995bcef7c', 'fbee5461-1eb7-4de2-b2a4-6de2c9140b7c', '불고기와퍼주니어', '불에 직접 구운 순 쇠고기 패티가 들어간 와퍼주니어에 달콤한 불고기 소스까지!', 6700, false, false, '2024-09-20 10:59:25', 'bgk051', '2024-09-20 10:59:25', 'bgk051', null, null),
-- 7번가피자 우동점
('b1149eb0-47af-4180-96ea-14111f5c5063', '87e7b574-a9ba-4034-9358-9898f9954b03', '불고기(R)', '한국인의 입맛을 사로잡은 불고기피자', 20900, false, false, '2024-09-20 11:35:09', 'street77', '2024-09-20 11:35:09', 'street77', null, null),
('be506e30-ebb2-406b-a560-b81d213939c7', '87e7b574-a9ba-4034-9358-9898f9954b03', '페스티벌(R)', '불갈비, 포테이토, 쉬림프, 핫치킨 네가지 토핑의 축제', 25900, false, false, '2024-09-20 11:39:52', 'street77', '2024-09-20 11:39:52', 'street77', null, null),
('53c825dd-11f8-4494-8a51-630bdc2d2a6e', '87e7b574-a9ba-4034-9358-9898f9954b03', '파티박스 R(R)', '프리미엄피자 석쇠R 중 택1, 로제스파게티, 치킨바샤삭8개, 골드멘보샤2개, 코카콜라500ml', 37900, false, false, '2024-09-20 11:46:25', 'street77', '2024-09-20 11:46:25', 'street77', null, null),
('aa49aa6b-66b2-46cd-884c-7c21c07fa4c8', '87e7b574-a9ba-4034-9358-9898f9954b03', '[신메뉴]오븐 치즈떡볶이', '매콤한 떡볶이 위에 오븐에서 갓구워낸 자연산 모짜렐라 치즈를 얹은 환상의 맛', 6500, false, false, '2024-09-20 11:50:27', 'street77', '2024-09-20 11:50:27', 'street77', null, null),
('4400fb09-03da-4d3d-92be-e216605a7f99', '87e7b574-a9ba-4034-9358-9898f9954b03', '페페로니(R)', '향긋한 페페로니와 짭조름한 치즈의 절묘한 조화', 20900, false, false, '2024-09-20 11:56:19', 'street77', '2024-09-20 11:56:19', 'street77', null, null),
-- 톤쇼우 해운대점
('de13cf4b-00ea-40a5-96fe-05c1ef054b04', 'd140dbd1-525b-4a49-ab05-f1702fa4d666', '버크셔K로스카츠', '버크셔K 등심', 15500, false, false, '2024-09-20 12:02:25', 'bestkatsu5', '2024-09-20 12:02:25', 'bestkatsu5', null, null),
('9e6c41c2-d774-4b9f-a57e-23521b71a0b0', 'd140dbd1-525b-4a49-ab05-f1702fa4d666', '히레카츠', '안심', 12500, false, false, '2024-09-20 12:07:34', 'bestkatsu5', '2024-09-20 12:07:34', 'bestkatsu5', null, null),
('05d6941f-9563-4240-b108-94c886b7df2b', 'd140dbd1-525b-4a49-ab05-f1702fa4d666', '로스카츠', '등심', 12000, false, false, '2024-09-20 12:17:12', 'bestkatsu5', '2024-09-20 12:17:12', 'bestkatsu5', null, null),
('d59b3657-901c-45c2-9ff2-79929d5ec54d', 'd140dbd1-525b-4a49-ab05-f1702fa4d666', '칸쥬쿠카츠', '얇게 썰어 완전히 익힌 등심', 11500, false, false, '2024-09-20 12:25:53', 'bestkatsu5', '2024-09-20 12:25:53', 'bestkatsu5', null, null),
('02cd183c-1891-44a3-838c-0f49cbb89c85', 'd140dbd1-525b-4a49-ab05-f1702fa4d666', '에비카츠', '새우', 12000, false, false, '2024-09-20 12:31:37', 'bestkatsu5', '2024-09-20 12:31:37', 'bestkatsu5', null, null),
-- 교촌치킨 마린시티점
('7c5a00b0-e335-40c2-a9ea-84a6ff9d2404', '4d8e7f37-b235-409c-8ab9-c3531627c11f', '리얼후라이드', '오트밀, 퀴노아, 아마란스 등 슈퍼푸드로 바삭함을 살린 후라이드', 20000, false, false, '2024-09-20 12:09:33', 'gyogyo3', '2024-09-20 12:09:33', 'gyogyo3', null, null),
('42589a54-407d-4c26-aa4a-3c17806c3ded', '4d8e7f37-b235-409c-8ab9-c3531627c11f', '레드콤보', '치킨의 날개와 봉, 다리에 밴 레드소스의 매콤한 맛이 일품인 부위별 치킨', 23000, false, false, '2024-09-20 12:15:11', 'gyogyo3', '2024-09-20 12:15:11', 'gyogyo3', null, null),
('d4d947d9-d66b-4c65-a420-d553168e070a', '4d8e7f37-b235-409c-8ab9-c3531627c11f', '허니콤보', '달콤한 허니소스와 바삭한 날개, 봉, 다리가 만나 촉촉함과 달콤함을 더욱 느낄 수 있는 부위별 치킨', 23000, false, false, '2024-09-20 12:19:17', 'gyogyo3', '2024-09-20 12:19:17', 'gyogyo3', null, null),
('2496188a-0254-4578-a0c2-9c7ec25cd0fa', '4d8e7f37-b235-409c-8ab9-c3531627c11f', '시그니처점보윙', '교촌점보윙, 레드점보윙, 허니갈릭점보윙을 한 번에 즐길 수 있는 메뉴(24조각)', 28000, false, false, '2024-09-20 12:25:10', 'gyogyo3', '2024-09-20 12:25:10', 'gyogyo3', null, null),
('01466b06-6fd3-4fc9-b98d-44b298064d3e', '4d8e7f37-b235-409c-8ab9-c3531627c11f', '교촌옥수수오리지날', '리얼옥수수의 진한 풍미와 달콤함이 특징인 한마리 치킨', 20000, false, false, '2024-09-20 12:31:07', 'gyogyo3', '2024-09-20 12:31:07', 'gyogyo3', null, null),
-- 호식이두마리치킨 해운대우동점
('bef95480-be2f-4af4-94eb-f4ec05ab294f', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', '두마리', '합리적 소비! 한방에 두닭하세요!', 25000, false, false, '2024-09-20 12:32:43', 'siuuuuu2', '2024-09-20 12:32:43', 'siuuuuu2', null, null),
('fa4e1bb1-6798-4bad-ada9-c2b192da5263', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', '후라이드치킨', '호식이두마리치킨의 정통 후라이드 치킨!', 16000, false, false, '2024-09-20 12:36:05', 'siuuuuu2', '2024-09-20 12:36:05', 'siuuuuu2', null, null),
('4c54a728-3798-4722-9d65-092092d761d1', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', '양념치킨', '클래식의 양대산맥, 헤어나올 수 없는 달콤한 매력!', 18000, false, false, '2024-09-20 12:40:05', 'siuuuuu2', '2024-09-20 12:40:05', 'siuuuuu2', null, null),
('86e62a48-ce8d-436f-9b89-152fc9beb623', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', '크리스피 골드(국내산 순살) 단품', '바삭함으로 감싸고 촉촉함으로 가득 채운 국내산 순살 크리스피 골드 치킨', 17000, false, false, '2024-09-20 12:50:59', 'siuuuuu2', '2024-09-20 12:50:59', 'siuuuuu2', null, null),
('46766167-d2b0-499c-aff1-51e48c13590e', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', 'New)요거치즈닝치킨', '지중해풍 딜요거트와 바삭한 마법 치즈시즈닝의 만남!', 18000, false, false, '2024-09-20 12:55:34', 'siuuuuu2', '2024-09-20 12:55:34', 'siuuuuu2', null, null),
-- 짬뽕전문점 여빈 본점
('554ca05d-e9be-4f56-836c-66c6fccb5124', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', '여빈짬뽕', '여빈짬뽕의 시그니처 기본 짬뽕', 12000, false, false, '2024-09-20 13:00:35', 'zzambbong2', '2024-09-20 13:00:35', 'zzambbong2', null, null),
('a76066f9-4b54-4cca-902d-d0386a50420f', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', '여빈짜장면', '짜장면', 8000, false, false, '2024-09-20 13:04:49', 'zzambbong2', '2024-09-20 13:04:49', 'zzambbong2', null, null),
('10165f5d-82e6-44c1-a9da-cec3eba15443', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', '여빈덮밥', '덮밥', 15000, false, false, '2024-09-20 13:09:26', 'zzambbong2', '2024-09-20 13:09:26', 'zzambbong2', null, null),
('8d671fa6-3818-4321-a47e-d706f2a4d698', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', '등심탕수육', '등심으로 만든 탕수육', 27000, false, false, '2024-09-20 13:13:57', 'zzambbong2', '2024-09-20 13:13:57', 'zzambbong2', null, null),
('f889c062-772b-497b-85e0-d42842131940', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', '깐풍육', '깐풍육', 30000, false, false, '2024-09-20 13:19:28', 'zzambbong2', '2024-09-20 13:19:28', 'zzambbong2', null, null),
-- 썬더치킨 연산5호점
('a6d54322-dc44-42ec-bc4f-2fce1d76ed96', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '크리스피치킨(한마리 (뼈))', '소리까지 바삭한 썬더치킨 대표메뉴 (뼈)', 16900, false, false, '2024-09-20 13:20:44', 'thunderbol', '2024-09-20 13:20:44', 'thunderbol', null, null),
('084c98f4-088e-481d-bfa7-2f187f0f9d00', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '크리스피치킨(한마리 (순살))', '소리까지 바삭한 썬더치킨 대표메뉴 (순살)', 18900, false, false, '2024-09-20 13:25:41', 'thunderbol', '2024-09-20 13:25:41', 'thunderbol', null, null),
('dc04a71b-178b-4189-884c-d8638bb7869d', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '반반치킨(한마리 (뼈))', '크리스피치킨+양념치킨(선택) (뼈)', 18900, false, false, '2024-09-20 13:30:34', 'thunderbol', '2024-09-20 13:30:34', 'thunderbol', null, null),
('9fa81e4f-c85c-4996-bd9a-44cd87dabab9', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '반반치킨(한마리(순살))', '크리스피치킨+양념치킨(선택) (순살)', 20900, false, false, '2024-09-20 13:34:58', 'thunderbol', '2024-09-20 13:34:58', 'thunderbol', null, null),
('fec3456b-7b02-42aa-943d-d537990eff2f', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', '간장치킨(한마리 (뼈))', '짭조름한 간장소스와 바삭하고 풍미 넘치는 크리스피치킨의 만남 (뼈)', 18900, false, false, '2024-09-20 13:40:06', 'thunderbol', '2024-09-20 13:40:06', 'thunderbol', null, null),
-- BBQ치킨 부산서면스타점
('4bfbffa1-f1d8-4ba4-82b7-0ba340f1e644', 'ea156e21-133b-496d-855a-ec3863216a4f', '황금올리브치킨', '황금빛 파우더의 바삭함과 육즙 가득 퍼지는 부드러운 속살이 환상적!', 23000, false, false, '2024-09-20 14:43:57', 'bbqstar5', '2024-09-20 14:43:57', 'bbqstar5', null, null),
('853c57cf-5949-4255-a886-551132fce07c', 'ea156e21-133b-496d-855a-ec3863216a4f', '황금올리브치킨 양념', '옛날치킨 컨셉의 얇은 튀김옷에 사과퓨레, 당근 등의 천연재료를 가미하여 부드러운 단맛이 매력적인 BBQ의 새로운 양념치킨', 24500, false, false, '2024-09-20 14:50:26', 'bbqstar5', '2024-09-20 14:50:26', 'bbqstar5', null, null),
('1a6b0ca5-ed42-4cf1-abdf-b735db4e5b3a', 'ea156e21-133b-496d-855a-ec3863216a4f', '바사칸 윙', '후추와 마늘의 알싸한 매운맛과 구운 소금의 담백함이 더해진 바삭한 치킨', 23000, false, false, '2024-09-20 14:56:09', 'bbqstar5', '2024-09-20 14:56:09', 'bbqstar5', null, null),
('66abce8d-b720-4717-aedf-d6ffbcef4cbf', 'ea156e21-133b-496d-855a-ec3863216a4f', '땡초숯불양념치킨', '땡초의 매운맛과 진한 감칠맛, 숯불향이 조화를 이룬 BBQ만의 특제 양념소스를 더한 치킨', 25000, false, false, '2024-09-20 15:02:02', 'bbqstar5', '2024-09-20 15:02:02', 'bbqstar5', null, null),
('b156833d-6191-41f3-9248-9cc44121f2a7', 'ea156e21-133b-496d-855a-ec3863216a4f', '자메이카 소떡만나치킨', 'BBQ 자메이카 저크소스와 신선육, 소떡소떡을 조합하여 풍미를 올린 전국민이 최애하는 치킨', 24000, false, false, '2024-09-20 15:10:17', 'bbqstar5', '2024-09-20 15:10:17', 'bbqstar5', null, null)
;

INSERT INTO p_order (order_id, user_id, address_id, store_id, type, status, order_price, demand, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
-- 교촌치킨 마린시티점 레드콤보 1개
('8214df5a-846c-4ece-9392-79d3d20aeb2e', 23, '3d6eb26a-5cb2-47a7-8322-b599cea55d29', '4d8e7f37-b235-409c-8ab9-c3531627c11f', 'ONLINE', 'APPROVED', 23000, '일회용 수저 X', false, '2024-09-27 19:27:46', 'firstman', '2024-09-27 19:28:17', 'gyogyo3', null, null),
-- 썬더치킨 연산5호점 크리스피치킨(한마리 (뼈)) 1개, 간장치킨(한마리 (뼈)) 1개
('e93874ec-b37c-4b65-ae3c-8305a46ce690', 23, '29626d8f-fc31-4071-8f7b-8e95ad1bde1f', 'c3716f27-e2ec-482d-b028-1dd3f9a2e20b', 'ONLINE', 'APPROVED', 35800, '초인종 누르고 문 앞에 놓아주세요', false, '2024-09-29 18:30:25', 'firstman', '2024-09-27 18:31:00', 'thunderbol', null, null),
-- 호식이두마리치킨 해운대우동점 두마리메뉴 취소
('c89adbe9-bb64-4585-b995-693c641dfca8', 23, '3d6eb26a-5cb2-47a7-8322-b599cea55d29', '3f18d8ac-50f1-466b-860a-78d9a073b0a9', 'ONLINE', 'CANCELED', 25000, '치킨무 빼고 주세요', false, '2024-10-07 23:47:12', 'firstman', '2024-10-07 23:49:05', 'siuuuuu2', null, null),
-- 7번가피자 우동점 페스티벌(R) 1개
('5b05825b-597f-41c4-82f6-c163a076d496', 23, '3d6eb26a-5cb2-47a7-8322-b599cea55d29', '87e7b574-a9ba-4034-9358-9898f9954b03', 'ONLINE', 'APPROVED', 25900, '피클 빼고 주세요', false, '2024-10-07 23:58:10', 'firstman', '2024-10-07 23:58:36', 'street77', null, null),
-- 여빈 짬뽕 3개, 짜장 1개
('5c3b2d40-140e-441b-b321-9e9e42d1514e', 23, '871c9048-90f3-40e6-9acc-0e593d444844', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', 'ONLINE', 'APPROVED', 44000, '일회용 수저 O', false, '2024-10-10 11:30:13', 'firstman', '2024-10-10 11:30:40', 'zzambbong2', null, null),
-- 교촌치킨 마린시티점 허니콤보 1개
('2019fd61-05f0-4ce3-b476-854588c17a0a', 23, 'c18db69e-cfa3-486f-9057-958b2215f36a', '4d8e7f37-b235-409c-8ab9-c3531627c11f', 'ONLINE', 'APPROVED', 23000, '리뷰이벤트 1번', false, '2024-10-12 19:45:15', 'firstman', '2024-10-12 19:45:35', 'gyogyo3', null, null),
-- BBQ치킨 부산서면스타점 황금올리브치킨 양념 1개
('ec93f54b-d74d-4470-866c-170ab182c3a8', 23, '701575a0-e51d-4d1d-ad3b-795db2381fc5', 'ea156e21-133b-496d-855a-ec3863216a4f', 'ONLINE', 'APPROVED', 24500, '일회용 수저 O', false, '2024-10-18 20:00:35', 'firstman', '2024-10-18 20:01:00', 'bbqstar5', null, null),
-- 여빈 짬뽕 3개, 짜장 2개, 등심탕수육 1개
('4dae22d4-1c84-4a15-a2fe-74d01db103fe', 23, 'd18af435-ef43-454b-9168-1f04bcbe7b9f', 'ce9aa567-32cb-405b-bc71-ca1892b5db55', 'ONLINE', 'APPROVED', 79000, '일회용 수저 O, 리뷰이벤트 사이다', false, '2024-10-21 11:52:00', 'firstman', '2024-10-21 11:52:27', 'zzambbong2', null, null)
;

INSERT INTO p_order_menu (order_menu_id, menu_id, order_id, count, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
-- 교촌치킨 마린시티점 레드콤보 1개
('9321577b-f6fe-49d6-a8ab-92386d364b9c', '42589a54-407d-4c26-aa4a-3c17806c3ded', '8214df5a-846c-4ece-9392-79d3d20aeb2e', 1, false, '2024-09-27 19:27:46', 'firstman', '2024-09-27 19:27:46', 'firstman', null, null),
-- 썬더치킨 연산5호점 크리스피치킨(한마리 (뼈)) 1개, 간장치킨(한마리 (뼈)) 1개
('468cbfd6-6323-4a50-afc2-2bd228db3685', 'a6d54322-dc44-42ec-bc4f-2fce1d76ed96', 'e93874ec-b37c-4b65-ae3c-8305a46ce690', 1, false, '2024-09-29 18:30:25', 'firstman', '2024-09-29 18:30:25', 'firstman', null, null),
('bc54f8db-985b-4776-be6a-b586e81c8f48', 'fec3456b-7b02-42aa-943d-d537990eff2f', 'e93874ec-b37c-4b65-ae3c-8305a46ce690', 1, false, '2024-09-29 18:30:25', 'firstman', '2024-09-29 18:30:25', 'firstman', null, null),
-- 호식이두마리치킨 해운대우동점 두마리메뉴 취소
('6ada3b7b-d53c-46d4-bc23-2d595370d878', 'bef95480-be2f-4af4-94eb-f4ec05ab294f', 'c89adbe9-bb64-4585-b995-693c641dfca8', 1, false, '2024-10-07 23:47:12', 'firstman', '2024-10-07 23:47:12', 'firstman', null, null),
-- 7번가피자 우동점 페스티벌(R) 1개
('3a470d39-c4c6-4638-a140-5c39345e248d', 'be506e30-ebb2-406b-a560-b81d213939c7', '5b05825b-597f-41c4-82f6-c163a076d496', 1, false, '2024-10-07 23:58:10', 'firstman', '2024-10-07 23:58:10', 'firstman', null, null),
-- 여빈 짬뽕 3개, 짜장 1개
('196a29ec-eb8a-419b-ada2-7ed9a4c0954b', '554ca05d-e9be-4f56-836c-66c6fccb5124', '5c3b2d40-140e-441b-b321-9e9e42d1514e', 3, false, '2024-10-10 11:30:13', 'firstman', '2024-10-10 11:30:13', 'firstman', null, null),
('0f56ba46-e697-4b80-9018-2673903d6301', 'a76066f9-4b54-4cca-902d-d0386a50420f', '5c3b2d40-140e-441b-b321-9e9e42d1514e', 1, false, '2024-10-10 11:30:13', 'firstman', '2024-10-10 11:30:13', 'firstman', null, null),
-- 교촌치킨 마린시티점 허니콤보 1개
('a362e566-141c-491d-b7c8-114c13fef4f8', 'd4d947d9-d66b-4c65-a420-d553168e070a', '2019fd61-05f0-4ce3-b476-854588c17a0a', 1, false, '2024-10-12 19:45:15', 'firstman', '2024-10-12 19:45:15', 'firstman', null, null),
-- BBQ치킨 부산서면스타점 황금올리브치킨 양념 1개
('19da8557-2e9b-4c16-9ce1-52009db7f731', '853c57cf-5949-4255-a886-551132fce07c', 'ec93f54b-d74d-4470-866c-170ab182c3a8', 1, false, '2024-10-18 20:00:35', 'firstman', '2024-10-18 20:00:35', 'firstman', null, null),
-- 여빈 짬뽕 3개, 짜장 2개, 등심탕수육 1개
('9d219aaa-f1b5-4204-861b-b8233bd049f7', '554ca05d-e9be-4f56-836c-66c6fccb5124', '4dae22d4-1c84-4a15-a2fe-74d01db103fe', 3, false, '2024-10-21 11:52:00', 'firstman', '2024-10-21 11:52:00', 'firstman', null, null),
('6a17a45d-bd3e-4bc1-a4fa-9d9459c7e0e5', 'a76066f9-4b54-4cca-902d-d0386a50420f', '4dae22d4-1c84-4a15-a2fe-74d01db103fe', 2, false, '2024-10-21 11:52:00', 'firstman', '2024-10-21 11:52:00', 'firstman', null, null),
('b51e6727-146f-4232-8daa-997beeb9be5f', '8d671fa6-3818-4321-a47e-d706f2a4d698', '4dae22d4-1c84-4a15-a2fe-74d01db103fe', 1, false, '2024-10-21 11:52:00', 'firstman', '2024-10-21 11:52:00', 'firstman', null, null)
;

INSERT INTO p_payment (payment_id, order_id, user_id, type, payment_price, pg_name, pg_key, is_deleted, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by) VALUES
('1382f7a8-a59b-4822-8feb-1a57add1151f', '8214df5a-846c-4ece-9392-79d3d20aeb2e', 23, 'CARD', 23000, 'TOSS', '5EnN2RJGvaBX7zk2SK203JS6X6vaAOH2P2B79roHEHoSrSN5', false, '2024-09-27 19:27:56', 'firstman', '2024-09-27 19:27:56', 'firstman', null, null),
('ba40a034-ecc4-49ba-b711-1120f9d8e297', 'e93874ec-b37c-4b65-ae3c-8305a46ce690', 23, 'CARD', 35800, 'TOSS', 'ymvLI0Vn79wtoYrpLkTJpxPh5jAivou119EhRx4jRmYxg41B', false, '2024-09-29 18:30:35', 'firstman', '2024-09-29 18:30:35', 'firstman', null, null),
('b31f4da6-0324-4a43-9582-c5c94d60285c', 'c89adbe9-bb64-4585-b995-693c641dfca8', 23, 'CARD', 25000, 'TOSS', '9e6jQMwTQQPXrQjgryXFGxCFFgEKMJugMyBOQhIJEv563krK', false, '2024-10-07 23:47:22', 'firstman', '2024-10-07 23:47:22', 'firstman', null, null),
('163c10ea-668f-45aa-ba06-85c4ffe80a02', '5b05825b-597f-41c4-82f6-c163a076d496', 23, 'CARD', 25900, 'TOSS', 'ARa4Mz2n6aibuIingUS1lI0zcnliRIBayHvvOwWaGox8ejuN', false, '2024-10-07 23:58:20', 'firstman', '2024-10-07 23:58:20', 'firstman', null, null),
('b53377d5-f79b-4a59-8989-931a0ad866d9', '5c3b2d40-140e-441b-b321-9e9e42d1514e', 23, 'CARD', 44000, 'TOSS', 'Ebgbq6WRLOdPCu7liv7fH9TMdNv8ENrKahkJcVmLQ8LA2mQW', false, '2024-10-10 11:30:23', 'firstman', '2024-10-10 11:30:23', 'firstman', null, null),
('00e1ddf6-2f11-4cea-b5d2-3e9815e23d22', '2019fd61-05f0-4ce3-b476-854588c17a0a', 23, 'CARD', 23000, 'TOSS', 'FkHPlaeCNsUr4xI8biX6GMx64434RymoKa5mYurXzOVHBqvL', false, '2024-10-12 19:45:25', 'firstman', '2024-10-12 19:45:25', 'firstman', null, null),
('36099fe7-edb9-4090-be83-73b0b1d2f2b7', 'ec93f54b-d74d-4470-866c-170ab182c3a8', 23, 'CARD', 24500, 'TOSS', 'Apzjd2l3s0rKxZfXPki4wiVSZvK9mI1kkNdJQDDAf1JBUubB', false, '2024-10-18 20:00:45', 'firstman', '2024-10-18 20:00:45', 'firstman', null, null),
('1f5cee46-4ec6-440e-ac31-ac5e098693f3', '4dae22d4-1c84-4a15-a2fe-74d01db103fe', 23, 'CARD', 79000, 'TOSS', 'Z5KQy3LOHQBri2usWQT7CD5uhNQVp94glWxmKwDv02EXsR2w', false, '2024-10-21 11:52:10', 'firstman', '2024-10-21 11:52:10', 'firstman', null, null)
;
