# Shop Rest API Server
### Codepresso Simple Shopping Mall BackEnd Project 

### 프로젝트 소개

쇼핑몰을 위한 REST API server 입니다.

**기능**

1. 로그인
2. 회원가입
3. 메인페이지(상품리스트 페이지)
4. 상품상세페이지
5. 장바구니(회원전용)


### 제작정보
codepresso 의 dev-ops 과정을 수강하면 작성한 프로젝트입니다.  
##### http://codepresso.kr/  
### 사용기술
* springboot 
* MySQL
* Mybatis
* RDS(MySQL)

### 필수조건 안내
* java 8 이상이 필요합니다.  
* STS 설치권장  
* Mybatis
* MySQL 5.7  
### 설치 안내 
클론을 통해서 받아서 사용하면 됩니다.  
주의할것은  src/main/resources/application.properies 파일을 보안상의 문제로 Database Connection 부분은 git 에는 업로드 하지 않았습니다.  
그래서 제가 사용한 기본값들을 올리니 사용자에 맞게 작성해 사용하면 됩니다.  
    
    ## datasouce 정보 설정
	spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver(사용자에 맞게 작성)
	spring.datasource.url=db-url 
	spring.datasource.username=사용자
	spring.datasource.password=비밀번호
	


### 실행방법

   - 참고 블로그 : https://www.leafcats.com/178
* 
    

### 저작권
codepresso  
![codepresso logo](./images/codepresso-logo.png)
