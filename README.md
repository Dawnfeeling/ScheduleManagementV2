## 개요
Spring으로 CRUD및 인증/인가를 구현한 ScheduleManagementV2

**API 명세서, ERD**

https://abalone-flat-811.notion.site/Schedule-management-Project-V2-13f1531f17908040bacbfc0b528d93a3?pvs=4

## 주요 기능
config
- PasswordEncoder : 비밀번호 암호화하거나 암호화된 비밀번호를 비교
- WebConfig : 로그인 필터를 등록

controller
- SchedileController : ScheduleRequest를 받아 ScheduleService로 데이터를 넘겨준다.
- UserController : UserRequest를 받아 UserService로 데이터를 넘겨준다.

dto
- 전체적인 요청 및 응답 데이터를 담는 객체

entity
- BaseEntity : 작성일과 수정일을 담는 인터페이스(작성일과 수정일이 필요한 entity에 구현)
- Schedule : Schedule의 정보를 담는 클래스
- User : User의 정보를 담는 클래스

filter
- LoginFilter : 인증된 사용자가 아닌 클라이언트가 WHITE_LIST가 아닌 페이지에 접근하는 것을 막는다.

repository
- ScheduleRepository : schedule DB 상호작용 인터페이스
- UserRepository : user DB 상호작용 인터페이스

service
- ScheduleService : ScheduleController로부터 데이터를 받아 비즈니스 로직을 실행
- UserService : UserController로부터 데이터를 받아 비즈니스 로직을 실행
