# PLANM

⚙ 환경

* Back-end
  * Spring framework 5(Spring boot 2)
  * Mybatis
  * MySQL 8
* Front-end
  * Thymeleaf
  * jquery
  * Bootstrap 4
  * Tabulator Grid

---

개발내용(~2022.05.09)



#### 1.관리자

![admin1](https://user-images.githubusercontent.com/47884586/170852035-ca43ea45-24d1-4ef3-af22-901017472776.png)

* 사용권한별 메뉴 조회
* 관리자 기능
  * 회사정보 입력 기능
  * 사번 생성 규칙 기능(사원 추가시 사번 자동 부여)
  * 부서/파트 등록 및 수정 기능(부서/파트장 관리 기능)
  * 직급유형 선택 기능(직급코드 고정)
  * 전자결재 관리 기능
    * 문서 유형별 결재권한 부여 기능
    * 결재라인 고정 기능

#### 2.인사카드

![user1](https://user-images.githubusercontent.com/47884586/170852101-344fdf51-9cd9-4895-b261-db0a751a0bdd.png)

* 사원 추가 기능

#### 3.전자결재

![document1](https://user-images.githubusercontent.com/47884586/170852090-fe45f6a4-f6c5-496d-8f5b-4572b3f1c469.png)

* 문서 조회 기능(내문서, 결재문서)
* 문서 작성 기능
  * 휴가신청 - 사원 생성시 자동 휴가일수 부여/휴가 기간 일수 계산
  * 문서 임시 저장 기능
  * 문서 결재 신청 기능(결재자 테이블에 신청문서 데이터 생성)
  * 문서 상태는 코드로 고정관리(0001:결재대기, 0002:결재완료, 0003:반려, 0004:임시저장)