# 목차

- 프로젝트 개요
- 프로젝트 구성
- 프로젝트 수행과정 및 결과
- 프로젝트 후기

## 1. 프로젝트 개요

### 프로젝트 필요성  
현대 웹 서비스에서 메뉴 관리 및 공지사항과 같은 콘텐츠 관리는 필수적입니다.  
본 프로젝트는 Spring Framework 기반 웹 애플리케이션 개발 경험을 쌓기 위해 메뉴 CRUD 기능과 조회수 관리, CSRF 보안 기능을 통합한 학습 및 참조용 프로젝트로 시작되었습니다.

### 서비스 대상  
- 소규모 웹 애플리케이션 운영자 및 개발자  
- Spring MVC 및 MyBatis 기반 백엔드 연동 기술 학습자  
- Java 웹 프로젝트에서 프론트엔드와 백엔드 통신 구현을 연습하는 개발자

### 담당 업무  
- Spring MVC 컨트롤러 및 서비스 개발  
- MyBatis 매퍼 및 SQL 쿼리 작성  
- JSP와 JavaScript(Fetch API)를 활용한 동적 UI 구현  
- Spring Security CSRF 적용 및 보안 설정  
- 프로젝트 빌드, 배포, 디버깅 작업

---

## 2. 프로젝트 구성

### 기술 스택  
- Java 11 이상  
- Spring Framework (Spring MVC, Spring Security)  
- MyBatis ORM  
- MariaDB / MySQL  
- Apache Tomcat 9  
- JSP, HTML, CSS, JavaScript (Fetch API)  
- IntelliJ IDEA Ultimate

### 시스템 아키텍처

#### 시퀀스 다이어그램  
- 클라이언트 → 서버: 메뉴 조회, 추가, 수정, 삭제 요청 (REST API)  
- 서버 → DB: MyBatis 매퍼를 통한 CRUD 실행  
- 서버 → 클라이언트: JSON 응답 및 JSP 렌더링 결과 전송

#### 데이터베이스 구조  
- **menu** 테이블 구성  
  - 주요 컬럼: `idx`(PK), `title`, `content`, `writer`, `indate`, `count`(조회수)

#### API 설계 및 명세  

| HTTP 메서드 | 경로               | 역할                   |
|-------------|--------------------|------------------------|
| GET         | /menu/all          | 전체 메뉴 목록 조회      |
| GET         | /menu/{idx}        | 특정 메뉴 조회          |
| POST        | /menu/add          | 메뉴 추가               |
| PUT         | /menu/update/{idx} | 메뉴 수정               |
| PUT         | /menu/count/{idx}  | 메뉴 조회수 증가        |
| DELETE      | /menu/delete/{idx} | 메뉴 삭제               |

---

## 3. 프로젝트 수행과정 및 결과

- Spring MVC 기반 프로젝트 환경 구성 및 컨트롤러 작성  
- MyBatis 매퍼 XML 작성 및 DB 연동 테스트  
- JSP와 JavaScript Fetch API 활용한 동적 콘텐츠 구현  
- CSRF 토큰 메타태그 방식 적용 및 보안 강화  
- 메뉴 CRUD 기능 완성 및 RESTful API 연동 검증  
- 메뉴 조회수 증가 기능 구현 및 클릭 이벤트 핸들링  
- Spring Security 설정과 인증/인가 체계 확립  
- 톰캣 서버 배포 후 실환경 동작 확인

---

## 4. 프로젝트 후기

- Spring MVC와 MyBatis 연동 경험을 통해 SQL 매핑과 트랜잭션 관리 능력 향상  
- 클라이언트와 서버 간 REST API 통신 구조를 명확히 이해  
- CSRF 보안 적용 과정을 직접 경험하며 보안 설정 중요성 체감  
- 405, 403 오류 해결 과정에서 HTTP 메서드 및 보안 정책에 관한 실무 지식 증진  
- 향후 React, Vue.js 등 프론트엔드 프레임워크 연동 프로젝트 도전 계획

