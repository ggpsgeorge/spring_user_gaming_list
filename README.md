# Gaming List REST API
Spring REST API back-end project for a website or webapp, that must save a list of games that users likes it. A user can have many games, and games can have many users, so that it can be implemented in the future a kind of games recommendation to users with similar game lists.

Javadoc was used to document the project, or most of it. The password field must be saved in the database, but not returned. So a DTO pattern is implemented.

Also Mockito is used to test the Repositories, Services and Controllers of the Users and Games models. The use of h2 database is used in testing.

## What do you need
1. Java 17
2. Docker
3. Maven
4. Postman, or any API tool

## How to execute
A database must be running. So docker-compose is used to run a postgresql image. To run the container:
```sh
docker compose up
```
To acess the database go to http://localhost:8080/
The credentials are on the docker-compose.yml file

Now run the the project with a IDE with Maven integration. This project used vscode.

To see if the project is running correctly, you can access an endpoint such as http://localhost:8181/api/v1/games/add. A whitelabel error page will appear.

A API tool must be used to send a JSON.
Use this JSON as a test. Send a POST to localhost:8181/api/v1/games/add

```code
{
    "name":"Super Mario 64",
    "genres":["Platform", "Kids"]
}
``` 
It will return 
```code
{
    "id": 1,
    "name": "Super Mario 64",
    "genres": [
        "Platform",
        "Kids"
    ]
}
```
That's it

To shutdown the docker container:
```sh
docker compose down
```