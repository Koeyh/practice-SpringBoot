## Spring Boot를 이용한 간단한 게시판 만들기
- 이전에 JPA를 사용한 팀 프로젝트를 해 봤으니 Mybatis를 사용해서 CRUD 구현까지 빠르게 경험해보자
- 이전 팀 프로젝트와 달라진 점
  - JPA -> mybatis
  - JSP -> Thymeleaf
  - Oracle -> MySQL
- CSS Style 요소는 최소한으로
- mybatis와 thymeleaf, javascript 사용과 숙달에 집중 할 것

- 패키지별 개념 잡기
|패키지명|용도|
|:---:|:---|
|Controller|View와 Service를 이어주는 역할|
|Service|실질적인 로직을 실행하여 Model단으로 전달|
|Repository|Service에서 전달받은 내용으로 xml 파일에 전달|
|/resources/mapper|실제 쿼리를 작성하는 곳|
|dto|DB의 컬럼과 Java 클래스의 필드를 매핑시키기 위함|

#### 1일차
- insert 문을 이용한 데이터 삽입
- save()

#### 2일차
- select 문을 이용한 데이터 조회(게시물 목록)
- findAll()
- 해당 테이블의 모든 컬럼을 조회하므로 selectList() 사용

#### 3일차
- select 문을 이용한 데이터 조회(게시물 상세)
- findById()
- SqlSessionTemplete의 selectOne() 사용
  - 하나의 컬럼만 조회하므로 selectOne()으로 조회
- detail.html에서 목록, 수정, 삭제 버튼의 onclick에 대한 반응으로 Javascript Arrow 함수 사용
```
<button onclick="listReq()">목록</button>
...
const listReq = () => {
  location.href = "/list";
}
```
- a태그나, action태그의 링크 설정은 이전에 경험 했으니, JS로 설정

#### 4일차
