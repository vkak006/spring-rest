# Overview
Spring boot 기반 RESTful API 파일럿 프로젝트 개발을 위한 스터디용 프로젝트.

# Version
> 💬 프로젝트 설계방향에 따라 일부 버전 변경 될 수 있음.
* Spring boot : 2.4.8
* Maven : 4.0.0
* Swagger : 3.0.0

# Features
###user
___
User Authentication, ~~Oauth2~~, Spring Security 적용

###sample
___
webFlux, HATEOAS 등에 관한 샘플 코드

# CI/CD
> 💬 NAS 서버 구축 시 gitlab으로 코드 이관 후, gitlab + jenkins + docker 사용예정
* github action

# Tech Stach
* HATEOAS (netty FW 상에서 사용 가능한지 확인 필요)
* WebFlux
* Mongodb-reactive
* Reactive-core
* netty FW
* Azure App Service 배포