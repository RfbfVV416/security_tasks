## Description
This is an implementation of an example access control system. 

The goal of the task is to show how we can provide different access rights for users with different privilege.

## Requirements

For building and running the application you need:

- [OPEN-JDK 11](https://openjdk.java.net/projects/jdk/11/)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com/) - if you prefer launch the database via docker
- [PostgreSQL](https://www.postgresql.org/) - or its image

This project is using PostgreSQL database, so you need it or its docker image (if you use docker) for launch the application.  
You can use docker-compose.yml file for launching the postgres docker file with provided credentials and ports.  
For this type:
```console
docker-compose up
```
Default postgres settings are the same in the docker-compose and the application.yaml files.  
If you want to change some of them
you have to make changes in both files.  
You also have to specify some environment variables:  
`DATABASE_PASSWORD` - for your postgres password,  
`DATABASE_URL` - for your postgres url,  
`DATABASE_USERNAME` - for you postgres username,  
`ADMIN_EMAIL` - for your admin email 

## Demonstration
Screenshots below illustrate postman requests to the application API.

####Successful admin auth with getting a cookie with JSESSIONID. 
![Screenshot 2021-10-19 at 4 20 55 PM](https://user-images.githubusercontent.com/62764458/137908107-fd9d38c7-07b2-483d-9149-e60890a76407.png)

####Admin is able to get all info about users.
![Screenshot 2021-10-19 at 4 26 48 PM](https://user-images.githubusercontent.com/62764458/137908977-2a098885-3f39-496a-85ba-e2e78c2b5fc3.png)

####Admin is able to get any user by id.
![Screenshot 2021-10-19 at 4 29 29 PM](https://user-images.githubusercontent.com/62764458/137909422-8a966c50-9432-4b26-906d-50e3e35e783c.png)

####Successful user auth with getting its own JSESSIONID.
![Screenshot 2021-10-19 at 4 32 30 PM](https://user-images.githubusercontent.com/62764458/137909841-f01121b4-2a23-48f5-8406-d1f0784f0ebd.png)

####User can't get all other users' info.
![Screenshot 2021-10-19 at 4 35 45 PM](https://user-images.githubusercontent.com/62764458/137910387-ff654fb6-2624-4baa-b19e-cd7513fdb69e.png)

####User can't get any other user info by id.
![Screenshot 2021-10-19 at 4 39 47 PM](https://user-images.githubusercontent.com/62764458/137911042-5e37426d-8538-4852-a25f-ca2dc07a15f6.png)

####User is able to get only its own info.
![Screenshot 2021-10-19 at 4 42 05 PM](https://user-images.githubusercontent.com/62764458/137911484-179a068f-5493-4cc1-a462-44f9e174fb3f.png)
