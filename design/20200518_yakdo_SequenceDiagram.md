# Sequence Diagram v1

- 기능 1. 메인 페이지 = 위치 기반 약국찾기
```mermaid
sequenceDiagram
	participant user
	participant frontend
	participant backend
	user ->> frontend : 1. 브라우저에서 접속
	frontend -->> user : 2. https에서 geolocation 사용, 현재 위치 요청
    user ->> frontend : 3. 허용시/거부시 위치 기준 5km? 표시
    frontend ->> backend : 4. REST request
    backend -->> frontend : 5. 약국 리스트 반환
    loop 제약조건체크    
        frontend ->> frontend : 6. 리스트가 비어있다면 <br> (현재 운영중인 약국이 없다면) 응급실을 안내할까요? alert 창 띄우기
        frontend ->> frontend : 7. 리스트가 비어있지 않다면 지도에 마커 표시
    end  
    frontend -->> user : 8. Vue를 통해 페이지 이동하지 않고 내용 전환(SPA 기능)
```

- 기능 2. 마커 클릭시 
```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	u ->> f : 1. 마커 클릭
	f -->> u : 2. js 함수를 사용해 화면 상단부에 검색창 표시, 하단부에 안내창 표시
	u ->> f : 3-1. 이름 클릭 시 상세 페이지 (새창/새탭으로 열기)
	u ->> f : 3-2. 전화 버튼 클릭시 전화 연결 (html tel 태그 사용)
	u ->> f : 3-3. 길찾기 버튼 클릭시 상세 페이지 (리다이렉트)
	u ->> f : 3-4. 공유 버튼 클릭시 상세 페이지 (리다이렉트)	
```

- 기능 3. 주소 검색

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 마커 클릭
	f -->> u : 2. js 함수를 사용해 화면 상단부에 검색창 표시, 하단부에 안내창 표시
	u ->> f : 3. 검색창에서 주소 검색 (ex) 월드컵북로, 기장동, 서울시???
	f ->> b : 7. request : back에서 sql 처리 (드롭다운 메뉴 제공? 좀 더 생각해보는걸로)
	b -->> f : 8. response : 해당 검색어를 포함하는 리스트 반환
	f -->> u : 9. 마커 찍어주기
```
- 기능 4. 메인에서 가장 가까운 약국 안내 기능 (나중에 가능하면 개발)

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	u ->> f : 1. 지도에서 ?긴급 버튼 클릭	
	f -->> u : 2. 가장 가까운 약국으로 직선 표시 <br> https://apis.map.kakao.com/web/sample/drawingGetData
```

- 기능 5. 비로그인 ip 기반 댓글? 리뷰 기능
- ![20200518-image-135817526](20200518-image-135817526.png)

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 상세 페이지에서 비로그인 리뷰 제출 버튼 클릭 (*placeholder 추가)
	f ->> b : 2. request
	b -->> f : 3. response (axios 등록/비등록 alert)
	f -->> u : 4. 화면 반영
```

기능 6. 나만의 약국 - 약 리스트 /GET

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 햄버거 버튼 
	f -->> u : 2. 나만의 약국 페이지로 이동
	f ->> b : 3-1. 자동 request
	b -->> f : 3-2. response (axios 등록/비등록 alert)
	f -->> u : 4. 화면 반영	
```

기능 7. 나만의 약국 - 약 등록 /POST

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 햄버거 버튼 
	f -->> u : 2. 나만의 약국 페이지로 이동
	u ->> f : 3-1. 약 등록 버튼 클릭
	f -->> u : 3-2. 입력 페이지
	u ->> f : 3-3. 바코드/QR코드 입력 & 유통기간 & <br> 알림 여부 & 알림 시간 & 메모 등 
	f ->> b : 3-4. request
	b -->> f : 3-5. response (axios 등록/비등록 alert)
	f -->> u : 4. 화면 반영(유통기간 임박 약품 강조 안내)
```

기능 8. 나만의 약국 - 약 개별 CRUD

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 약 클릭
	f -->> u : 2. 으로 상세 페이지 이동
	u ->> f : 3-1. + - max 버리기, 나눔, 복용, 추가? 버튼 각 기능
	f ->> b : 3-2. request
	b -->> f : 3-5. response (약 재고 테이블에 주의)
	f -->> u : 4. 화면 반영	
```

기능 9. 복용 기록(로그) (테이블을 그대로)

```example
복용 로그
2020년 ㅁ월 ㅁ일 9시  || 이지엔 || 2알 || 복용 			재고 8 -> 6
9시 3알 복용 되돌리기					재고 5 -> 8
9시 3알 복용							재고 8 -> 5
3시 2알 복용							재고 10 -> 8
```

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 복용 기록 클릭
	f -->> u : 2. 으로 복용 기록 페이지 이동
	u ->> f : 3-1. 날짜, 약 이름, action, 개수(column), 되돌리기 버튼
	f ->> b : 3-2. request
	b -->> f : 3-5. response (약 재고 테이블에 주의)
	f -->> u : 4. 화면 반영	
```

기능 10. 구매, 복용 통계

```mermaid
sequenceDiagram
	participant u as user
	participant f as frontend
	participant b as backend
	u ->> f : 1. 통계 클릭
	f -->> u : 2. 으로 통계 페이지 이동
	f ->> b : 3-1. request 기간에 따른 약 개수 요청 
	b -->> f : 3-2. response (약 재고 테이블에 주의)
	f -->> u : 4. 화면 반영	
```
