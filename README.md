
# Projeto Spring Java



## Tecnologias utilizadas

<div style="display: inline_block">
  <img align="center" alt="Spring" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="MySql" src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white" />
  <img align="center" alt="Java" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="Heroku" src="https://img.shields.io/badge/Heroku-430098?style=for-the-badge&logo=heroku&logoColor=white" />
</div>


## Heroku
- [Heroku](https://www.heroku.com/#)
- Dashboard
- Create new app
  - app name: java-spring-coursesb
  - região: United States
- Criar Banco postgres
  - Resources
  - Add-ons
  - heroku postgres
    - Free
    - provision
## Banco Postgres
- Banco: springboot-course
- docker run -d --name spring-postgresql -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1234567 -e - POSTGRES_DB=springboot-course -v /custom/mount:/var/lib/postgresql/data postgres
- docker exec -it spring-postgresql bash
- psql -h localhost -U postgres -W
- \l
- \c springboot-course
- \d
 
## Depedencias
- [Spring JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Banco H2](https://mvnrepository.com/artifact/com.h2database/h2)

