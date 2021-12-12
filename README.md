# BOJ 그룹스터디 회원들의 공간

***새롭게 단장하기 위한 `리액트` 공부로 인해 현재 일시중지하였습니다.***

Spring과 DB를 공부하기 위한, 그리고 그룹스터디 회원들에게 알고리즘 가이드를 해주기 위해 만드는 개인프로젝트 (2021.08 ~ )

관리자모드 회원명단          |  회원이 작성한 게시글
:-------------------------:|:-------------------------:
<img src="https://user-images.githubusercontent.com/57135043/138881965-f6fb0924-e231-42a8-a4c8-f1a99cb11cbe.png"  width="400" height="300"/>  |  <img src="https://user-images.githubusercontent.com/57135043/138882809-35e7ff7e-2bf1-4d68-88b7-cd9fd642db97.png"  width="400" height="300"/>

## Functions
- [x] 회원가입/로그인을 통해 그룹 회원들이 사이트를 이용할 수 있다.
- [x] 관리자/정식회원/손님 계정에 따른 권한 부여 및 인증기능을 가진다.
- [ ] RestTemplate을 이용한 회원 티어 자동 표시로 검증할 수 있다.
- [x] 게시판을 통해 그룹 회원들끼리 정보를 공유할 수 있다.
- [ ] 웹소켓을 이용한 댓글알림 기능이 존재한다.
- [ ] AWS 서버를 이용하여 그룹스터디원들이 실제로 접속할 수 있다. (해외결제 가능 카드 생성 이후 작업하기)
- [ ] 회원들끼리 채팅방을 통해 실시간으로 대화할 수 있다.

- https://kth990303.tistory.com/125 의 Java 부분 참고

## Settings
BackEnd
- Language: Java 11
- ORM: JPA
- DB: MySQL
- Library/Framework: Spring Boot (MapStruct, Spring Security)

FrontEnd
- Template Engine: Thymeleaf
