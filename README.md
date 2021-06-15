# TL;DR

A project that focuses on Spring Security.


<br />

# Why did I do this?

To practice how to create backend applications using Spring Framework with Spring Boot again.

# What is this?

A simple project that lets you signup and login inorder to access the routes. It will return a jwt token if you successfully logged in and use this in the HEADER to access the /user and /admin routes. Will return 403 Forbidden http code if you don't have access to the route. 
# How to run the application?

There are 2 ways to run this:

1. Install PostgreSQL in your computer and use the code below in the application.properties in `/main/resources/` folder.

```
spring.datasource.url=postgresql://localhost:5432/<your database name>
spring.datasource.username=<your postgres username>
spring.datasource.password=<your postgres password>
spring.jpa.hibernate.ddl.auto=create-drop
```

2. Install `docker` and `docker-compose` and run the command below. This will create 2 containers, the maven and postgres containers.
```
docker-compose up
```

# How to test the application?

Create an account using the signup route, Login using your username password on /

Routes:

- `/auth/signup` [POST]
- `/auth/login` [POST]
- `/` [GET] (the non-secured route)
- `/user` [GET] (normal users with user role)
- `/admin` [GET] (only users with admin role)
