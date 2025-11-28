#  Spring MVC Item Service & Login System

스프링 MVC를 활용한 **상품 관리 및 세션 기반 로그인 서비스** 프로젝트입니다.
기본적인 CRUD 기능부터 Bean Validation을 이용한 검증, 인터셉터와 아규먼트 리졸버를 활용한 인증 처리, 그리고 단위 테스트까지 구현하였습니다.

##  Tech Stack

  - **Java**
  - **Spring Boot**
  - **Thymeleaf** (View Template)
  - **Lombok**
  - **Spring Validation**
  - **JUnit 5 & AssertJ** (Testing)
  - **Bootstrap** (Styling)

-----

## 주요 기능 (Features)

1.  **상품 관리 (Item Management)**

      - 상품 목록 조회, 상세 조회
      - 상품 등록 및 수정, **삭제(Delete)**
      - **검증 로직**: 가격, 수량 제한 및 글로벌 오류(가격×수량 합계 검증) 처리

2.  **회원 관리 (Member Management)**

      - 회원 가입 (Memory Repository 기반)
      - 아이디 중복 방지 로직 (Optional 활용)

3.  **로그인/로그아웃 (Authentication)**

      - **Session**: 서블릿 기반의 `HttpSession`을 활용한 로그인 상태 유지
      - **Interceptor**: 비로그인 사용자의 접근 제한 (접속 시도한 경로를 기억해 로그인 후 리다이렉트)
      - **Argument Resolver**: 커스텀 애노테이션 `@Login`을 통해 컨트롤러에서 세션 회원 객체를 간편하게 주입

-----

##  API 명세서 (API Specification)

### 1\. 인증 & 홈 (Auth & Home)

| Method | URI | Description | 비고 |
| :--- | :--- | :--- | :--- |
| `GET` | `/` | 홈 화면 | 로그인 여부에 따라 화면 분기 |
| `GET` | `/login` | 로그인 폼 이동 | |
| `POST` | `/login` | 로그인 처리 | 세션 생성 (`JSESSIONID` 발급) |
| `POST` | `/login/logout` | 로그아웃 | 세션 만료 (`session.invalidate()`) |

### 2\. 회원 (Member)

| Method | URI | Description | 검증 (Validation) |
| :--- | :--- | :--- | :--- |
| `GET` | `/members/addMemberForm` | 회원가입 폼 이동 | |
| `POST` | `/members/addMemberForm` | 회원가입 처리 | 이름, ID, PW 필수 입력 |

### 3\. 상품 (Items)

| Method | URI | Description | 검증 (Validation) |
| :--- | :--- | :--- | :--- |
| `GET` | `/items` | 상품 목록 조회 | |
| `GET` | `/items/{itemId}` | 상품 상세 조회 | |
| `GET` | `/items/addForm` | 상품 등록 폼 이동 | |
| `POST` | `/items/addForm` | 상품 등록 | 가격(Range), 수량(Max), 글로벌 오류 |
| `GET` | `/items/{itemId}/edit` | 상품 수정 폼 이동 | |
| `POST` | `/items/{itemId}/edit` | 상품 수정 | 수정 시 가격 제한 없음 |
| `POST` | `/items/{itemId}/delete`| 상품 삭제 | **HTML Form 한계로 POST 사용** |

-----

##  테스트 (Tests)

**JUnit 5**와 **AssertJ**를 사용하여 핵심 비즈니스 로직인 Repository 계층을 검증했습니다.

  - **`MemoryItemRepositoryTest`**:
      - 상품 저장, 조회, 수정 로직 검증.
      - `delete()`: 상품 삭제 후 조회 시 `null` 반환 확인.
  - **`MemoryMemberRepositoryTest`**:
      - 회원 가입 및 조회 성공 테스트 (`isEqualTo`).
      - 존재하지 않는 회원 조회 시 `isNull` 검증.

-----

##  Validation Rules

`errors.properties`를 통해 관리되는 검증 메시지입니다.

```properties
# Global Error
totalPrice=[ERROR] 금액 * 수량은 {0} 원 이상이어야 합니다. 현재 금액 : {1}

# Field Error
NotEmpty.name=[ERROR] 상품명을 입력해주세요
NotNull.price=[ERROR] 가격을 입력해주세요
Range=[ERROR] {2} 원이상 {1} 원 이하 까지만 입력 가능합니다.
```

-----

##  Future Plans (추후 계획)

추후 데이터베이스와 다른 기능도 배우면 여러가지 추가해 볼 예정입니다.

1.  **정밀한 로그인 검증**: 아이디/비밀번호 불일치 시 구체적인 피드백 제공 및 보안 강화.
2.  **데이터베이스(DB) 도입**: 현재 `ConcurrentHashMap` 기반의 메모리 저장소를 H2/MySQL 및 MyBatis/JPA로 전환하여 데이터 영속성 확보.
3.  **Service 계층 도입**: 컨트롤러에 집중된 비즈니스 로직을 Service 계층으로 분리하여 책임 분산 및 유지보수성 향상.

-----

##  Today I Learned (TIL)

프로젝트를 진행하며 겪은 시행착오와 기술적 깨달음을 정리했습니다.

### 1\. Thymeleaf 변수 표현식 실수

타임리프에서 변수를 출력할 때 `${}` 구문을 빠뜨리면 문자열 그대로 출력되거나 파싱 에러가 발생한다.

  - **Bad**: `th:text="err"` (문자열 "err" 출력)
  - **Good**: `th:text="${err}"` (변수 `err`의 값 출력)

### 2\. `th:errorclass`의 올바른 위치

`th:errorclass`는 에러가 발생했을 때 CSS 클래스를 추가해주는 기능이므로, 입력 필드(input)에 작성해야 한다.

  - 에러 메시지를 출력하는 `<p>` 태그나 `<div>` 태그에 `th:errorclass`와 `th:errors`를 같이 쓰면 충돌하거나 예외가 발생할 수 있다.

### 3\. DTO와 기본 생성자 (NoArgsConstructor)

스프링 MVC가 요청 파라미터를 DTO로 바인딩할 때 내부적으로 Reflection을 사용한다. 이때 **기본 생성자**가 없으면 객체를 생성할 수 없어 에러가 발생한다.

  - DTO에는 비즈니스 로직이 담긴 생성자보다는 `@Data`나 `@NoArgsConstructor`를 사용하여 기본 생성자를 열어두는 것이 안전하다.

### 4\. `ArgumentResolver`의 타입 체크 (`isAssignableFrom`)

  - `parameter.getParameterType()`: 파라미터의 정확한 클래스 타입을 반환한다.
  - **`Member.class.isAssignableFrom(type)`**: 단순히 같은 타입인지 비교하는 것이 아니라, **해당 클래스이거나 상속받은 자식 클래스인지**까지 확인해준다. 유연한 타입 체크를 위해 이 메서드를 사용하는 것이 좋다.

### 5\. 세션(Session)과 쿠키(Cookie)의 동작 원리

  - **쿠키**: 클라이언트(브라우저)는 서버가 발급한 랜덤한 UUID 값인 `JSESSIONID`만을 쿠키로 저장한다. (보안상 실제 정보 X)
  - **세션**: 서버의 메모리(세션 저장소)에 `JSESSIONID`를 Key로, 실제 회원 객체(`Member`)를 Value로 보관한다.
  - 요청이 오면 서버는 쿠키의 ID를 보고 세션 저장소(금고)를 조회하여, 일치하는 세션이 있을 경우에만 회원 정보를 꺼내준다.

### 6\. HTML Form의 한계와 Delete 처리

  - HTML의 `<form>` 태그는 기본적으로 **GET**과 **POST** 메서드만 지원한다.
  - RESTful API 설계를 위해 `@DeleteMapping`을 사용하고 싶더라도, 순수 HTML Form에서는 호출할 수 없다.
  - 해결책:
    1.  컨트롤러를 `@PostMapping`으로 매핑하여 처리한다. (본 프로젝트 적용 방식)
    2.  Hidden Method Filter(`_method=delete`)를 사용한다. (설정이 복잡해질 수 있음)
