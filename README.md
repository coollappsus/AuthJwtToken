<h1 align="center">Auth Jwt Token</h1>

<div align="center">
    <img src="https://img.shields.io/badge/Java-black?style=for-the-badge&logo=Java" alt="Java"/>
    <img src="https://img.shields.io/badge/Spring-black?style=for-the-badge&logo=Spring" alt="Spring"/>
    <img src="https://img.shields.io/badge/Mysql-black?style=for-the-badge&logo=Mysql" alt="MySQL"/>
    <img src="https://img.shields.io/badge/Hibernate-black?style=for-the-badge&logo=Hibernate" alt="Hibernate"/>
    <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JWT" alt="JWT"/>
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
For register a new user, you need to make a routing request: POST<br>
<b>POST</b> method /api/auth/login<br>
curl: curl -H "Content-Type: application/json" -X POST http://localhost:9000/api/auth/login -d "{\"login\":\"ivan\",\"password\":\"1234\"}"<br>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/request_login.png?raw=true" alt="login"></div>
<b>Response:</b>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/response_token.png?raw=true" alt="token"></div>

<h3>Saving messages</h3>
For save the message, you need to make a routing request: POST<br>
<b>POST</b> method /api/message<br>
curl: curl -H "Content-Type: application/json" -H "Authorization: Bearer token" -X POST http://localhost:9000/api/message -d "{\"name\":\"ivan\",\"message\":\"Here your message\"}"<br>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/request_save_message.png?raw=true" alt="message"></div>
<b>Response:</b>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/response_save_message.png?raw=true" alt="save_message"></div>

<h3>Message history</h3>
For show the message history, you need to make a routing request: POST<br>
The number of output messages depends on the entered value<br>
<b>POST</b> method /api/message<br>
curl: curl -H "Content-Type: application/json" -H "Authorization: Bearer token" -X POST http://localhost:9000/api/message -d "{\"name\":\"ivan\",\"message\":\"history 5\"}"<br>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/request_history.png?raw=true" alt="history"></div>
<b>Response:</b>
<div><img src="https://github.com/coollappsus/AuthJwtToken/blob/main/assets/response_history.png?raw=true" alt="show_history"></div>

<h1>Technologies</h1>
<ul>
    <li>Java - version 16</li>
    <li>MySQL - version 8.0.26</li>
    <li>Spring boot - version 2.7.0</li>
    <li>Spring security - version 5.7.1</li>
    <li>JWT token - version 0.11.5</li>
    <li>JUnit - version 4.13.2</li>
    <li>Hibernate - version 6.1.1</li>
</ul>

<h1>Status</h1>
Project is: finished 😉

<h1>Inspiration</h1>
This project was created as a test task for the company "Inside" LLC.

<h1>Contact</h1>
Created by <a href="https://t.me/coollappsus"> Ryzhikov Ivan</a> - feel free to contact me!
<h3></h3>

<div><b>+7 (952) 353-19-01</b> | <a href="mailto:ntdr.94@yandex.ru"> Send me mail</a>  |
    <a href="https://github.com/coollappsus"> github</a></div>

