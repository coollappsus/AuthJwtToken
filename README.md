<h1 align="center">Auth Jwt Token</h1>

<div align="center">
    <img src="https://img.shields.io/badge/Java-black?style=for-the-badge&logo=Java" alt="Java"/>
    <img src="https://img.shields.io/badge/Spring-black?style=for-the-badge&logo=Spring" alt="Spring"/>
    <img src="https://img.shields.io/badge/Mysql-black?style=for-the-badge&logo=Mysql" alt="MySQL"/>
</div>
<h3></h3>
<h1>Description</h1>
This project is a service that creates a token when the user is authorized (valid for 1 hour), after which it can:
<ul>
    <li>Write a message that will be saved in the database</li>
    <li>View the latest messages, the amount he enters himself</li>
    <li>View all your messages</li>
</ul>
The project was tested using Postman.
Controllers and services are covered by tests.

<h1>About the project</h1>
<h3>Launch</h3>
This service works on port: 9000
<h3>Authentication</h3>
For register a new user, you need to make a routing request: POST.
POST method /api/auth/login
curl: curl -H "Content-Type: application/json" -X POST http://localhost:9000/api/auth/login -d "{\"login\":\"ivan\",\"password\":\"1234\"}"
Response:
<div><img src="https://github.com/coollappsus/SearchEngine/blob/main/assets/Dashboard2.png?raw=true" alt="Dashboard"></div>
