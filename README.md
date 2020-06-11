## 무신사 과제

### 프로젝트 : URL_Shortening

주요 기능 : URL을 입력 받아 짧게 줄여주고, Shortening된 URL을 입력하면 원래 URL로 리다이렉트 시킨다.

요구 사항

1. URL 입력폼 제공 및 결과 출력
2. URL Shortening Key는 8 Character 이내로 생성되어야 한다.
3. 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답해야 한다.
4. 동일한 URL에 대한 요청 수 정보를 가져야 한다
5. Shortening된 URL을 요청받으면 원래 URL로 리다이렉트 합니다.

### 개발 환경

Environment: Java(1.8), JPA, Thymeleaf, jQuery

Framework : Spring Boot, Junit5

Database : H2 Database(In_Memory)

Tools : IntelliJ IDEA

### 설치 및 빌드 방법

1. 먼저 프로젝트를 다운 받은 다음, gradlew가 있는 디렉토리에서 명령어를 입력한다.

```
./gradlew build
```

Gradle build가 실행되고, build/lib 디렉토리에 Jar파일이 생성된다.



2. build/lib 디렉토리에서 jar파일을 실행한다.

```
java -jar '생성된 디렉토리의 jar'
```



3. http://localhost:8080에 접속하여 테스트를 진행한다.

