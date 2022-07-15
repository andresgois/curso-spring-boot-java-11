
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
- docker run -d --name spring-postgresql -p 5432:5432 -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=springboot-course postgres:12.11
- docker exec -it spring-postgresql bash
- psql -h localhost -U postgres -W
- \l
- \c springboot-course
- \d
## Container PGAdmin
- docker run --name spring-pgadmin -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=postgres@email.com.br -e PGADMIN_DEFAULT_PASSWORD=1234567 -d dpage/pgadmin4
- [Acessar PGAdmin](http://localhost:15432)

## Backup pelo PGAdmin
- Seleciona o banco
- clica com botão direito
- backup
- escolhe a pasta para salva e nome do arquivo
- formato *Plain*
- encoding: *utf-8*
- Dump options
  - Only schema: *YES*
  - Blobs: *NO*
  - Do not save: *(ALL)*
  - verbose messages: *NO*
- Delete tudo antes do **CREATE**

```
-- DROP SCHEMA public;
CREATE SCHEMA public AUTHORIZATION postgres;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.tb_category_id_seq;
CREATE SEQUENCE public.tb_category_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions
ALTER SEQUENCE public.tb_category_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tb_category_id_seq TO postgres;

-- DROP SEQUENCE public.tb_order_id_seq;
CREATE SEQUENCE public.tb_order_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions
ALTER SEQUENCE public.tb_order_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tb_order_id_seq TO postgres;

-- DROP SEQUENCE public.tb_product_id_seq;
CREATE SEQUENCE public.tb_product_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions
ALTER SEQUENCE public.tb_product_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tb_product_id_seq TO postgres;

-- DROP SEQUENCE public.tb_user_id_seq;
CREATE SEQUENCE public.tb_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions
ALTER SEQUENCE public.tb_user_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.tb_user_id_seq TO postgres;

-- public.tb_category definition
-- Drop table
-- DROP TABLE public.tb_category;
CREATE TABLE public.tb_category (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	"name" varchar(255) NULL,
	CONSTRAINT tb_category_pkey PRIMARY KEY (id)
);

-- Permissions
ALTER TABLE public.tb_category OWNER TO postgres;
GRANT ALL ON TABLE public.tb_category TO postgres;

-- public.tb_product definition
-- Drop table
-- DROP TABLE public.tb_product;
CREATE TABLE public.tb_product (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	description varchar(255) NULL,
	img_url varchar(255) NULL,
	"name" varchar(255) NULL,
	price float8 NULL,
	CONSTRAINT tb_product_pkey PRIMARY KEY (id)
);

-- Permissions
ALTER TABLE public.tb_product OWNER TO postgres;
GRANT ALL ON TABLE public.tb_product TO postgres;

-- public.tb_user definition
-- Drop table
-- DROP TABLE public.tb_user;
CREATE TABLE public.tb_user (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	phone varchar(255) NULL,
	CONSTRAINT tb_user_pkey PRIMARY KEY (id)
);

-- Permissions
ALTER TABLE public.tb_user OWNER TO postgres;
GRANT ALL ON TABLE public.tb_user TO postgres;

-- public.tb_order definition
-- Drop table
-- DROP TABLE public.tb_order;
CREATE TABLE public.tb_order (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	moment timestamp NULL,
	order_status int4 NULL,
	client_id int8 NULL,
	CONSTRAINT tb_order_pkey PRIMARY KEY (id),
	CONSTRAINT fki0x0rv7d65vsceuy33km9567n FOREIGN KEY (client_id) REFERENCES public.tb_user(id)
);

-- Permissions
ALTER TABLE public.tb_order OWNER TO postgres;
GRANT ALL ON TABLE public.tb_order TO postgres;

-- public.tb_order_item definition
-- Drop table
-- DROP TABLE public.tb_order_item;
CREATE TABLE public.tb_order_item (
	price float8 NULL,
	quantity int4 NULL,
	product_id int8 NOT NULL,
	order_id int8 NOT NULL,
	CONSTRAINT tb_order_item_pkey PRIMARY KEY (order_id, product_id),
	CONSTRAINT fk4h5xid5qehset7qwe5l9c997x FOREIGN KEY (product_id) REFERENCES public.tb_product(id),
	CONSTRAINT fkgeobgl2xu916he8vhljktwxnx FOREIGN KEY (order_id) REFERENCES public.tb_order(id)
);

-- Permissions
ALTER TABLE public.tb_order_item OWNER TO postgres;
GRANT ALL ON TABLE public.tb_order_item TO postgres;

-- public.tb_payment definition
-- Drop table
-- DROP TABLE public.tb_payment;
CREATE TABLE public.tb_payment (
	moment timestamp NULL,
	order_id int8 NOT NULL,
	CONSTRAINT tb_payment_pkey PRIMARY KEY (order_id),
	CONSTRAINT fkokaf4il2cwit4h780c25dv04r FOREIGN KEY (order_id) REFERENCES public.tb_order(id)
);

-- Permissions
ALTER TABLE public.tb_payment OWNER TO postgres;
GRANT ALL ON TABLE public.tb_payment TO postgres;

-- public.tb_product_category definition
-- Drop table
-- DROP TABLE public.tb_product_category;
CREATE TABLE public.tb_product_category (
	product_id int8 NOT NULL,
	category_id int8 NOT NULL,
	CONSTRAINT tb_product_category_pkey PRIMARY KEY (product_id, category_id),
	CONSTRAINT fk5r4sbavb4nkd9xpl0f095qs2a FOREIGN KEY (category_id) REFERENCES public.tb_category(id),
	CONSTRAINT fkgbof0jclmaf8wn2alsoexxq3u FOREIGN KEY (product_id) REFERENCES public.tb_product(id)
);

-- Permissions
ALTER TABLE public.tb_product_category OWNER TO postgres;
GRANT ALL ON TABLE public.tb_product_category TO postgres;

-- Permissions
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;
```

## Scripts do banco
```
create database springboot-course;

\c springboot-course

create table tb_category (
    id int8 generated by default as identity,
    name varchar(255),
    primary key (id)
);
    
create table tb_order (
    id int8 generated by default as identity,
    moment timestamp,
    order_status int4,
    client_id int8,
    primary key (id)
);
    
create table tb_order_item (
    price float8,
    quantity int4,
    product_id int8 not null,
    order_id int8 not null,
    primary key (order_id, product_id)
);
    
create table tb_payment (
    moment timestamp,
    order_id int8 not null,
    primary key (order_id)
);

create table tb_product (
    id int8 generated by default as identity,
    description varchar(255),
    img_url varchar(255),
    name varchar(255),
    price float8,
    primary key (id)
);
    
create table tb_product_category (
    product_id int8 not null,
    category_id int8 not null,
    primary key (product_id, category_id)
);

create table tb_user (
    id int8 generated by default as identity,
    email varchar(255),
    name varchar(255),
    password varchar(255),
    phone varchar(255),
    primary key (id)
);
    
alter table if exists tb_order 
    add constraint FKi0x0rv7d65vsceuy33km9567n 
    foreign key (client_id) 
    references tb_user;

alter table if exists tb_order_item 
    add constraint FK4h5xid5qehset7qwe5l9c997x 
    foreign key (product_id) 
    references tb_product;

alter table if exists tb_order_item 
    add constraint FKgeobgl2xu916he8vhljktwxnx 
    foreign key (order_id) 
    references tb_order;
    
alter table if exists tb_payment 
    add constraint FKokaf4il2cwit4h780c25dv04r 
    foreign key (order_id) 
    references tb_order;

alter table if exists tb_product_category 
    add constraint FK5r4sbavb4nkd9xpl0f095qs2a 
    foreign key (category_id) 
    references tb_category;

alter table if exists tb_product_category 
    add constraint FKgbof0jclmaf8wn2alsoexxq3u 
    foreign key (product_id) 
    references tb_product;
    

```

## Configurando a aplicação na Heroku
- Faça login na heroku
- vá em Dashboard
- settings
    - Reveal Config Vars
    - DATABASE_URL = postgres://mdarvdzxbrptvy:1cf178730f76bdaf29c2a85ced89d0a6130429f79324516ef638008308914f46@ec2-34-197-84-74.compute-1.amazonaws.com:5432/d49nvgk7sg9e39
```
postgres://mdarvdzxbrptvy:1cf178730f76bdaf29c2a85ced89d0a6130429f79324516ef638008308914f46@ec2-34-197-84-74.compute-1.amazonaws.com:5432/d49nvgk7sg9e39

user:       mdarvdzxbrptvy
password:   1cf178730f76bdaf29c2a85ced89d0a6130429f79324516ef638008308914f46
server:     ec2-34-197-84-74.compute-1.amazonaws.com
port:       5432
database:   d49nvgk7sg9e39
```

## Instalar Heroku CLI
- Na dashboard do seu projeto
- Deploy
- [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli)
```
curl https://cli-assets.heroku.com/install-ubuntu.sh | sh
```
- heroku -v
 
## Associa Heroku com Github
- Assim que der um push irá automáticamente para a heroku
- No dashboard da heroku
- Deploy
- Deploy using Heroku Git
```
heroku login

cd my-project/
git init
heroku git:remote -a java-spring-coursesb

git add .
git commit -am "make it better"
git push heroku master
```

## Depedencias
- [Spring JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Banco H2](https://mvnrepository.com/artifact/com.h2database/h2)

