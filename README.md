# social-media-api
This is a basic social media api built with spring boot and spring security.

It uses mysql but anyother db can be used. simply edit the configuration file at 
  
    src/main/resources/application.properties
    
The following at are the endpoints

Register(post)

    curl --location 'http://localhost:8080/api/v1/auth/register' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email":"abc@abc.com",
        "password":"password",
        "name":"abc"
    }'

Login(post)

    curl --location 'http://localhost:8080/api/v1/auth/login' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "email":"abc@abc.com",
        "password":"password"
    }'
    
Create post(post)

    curl --location 'http://localhost:8080/api/v1/posts' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg2MzE1MiwiZXhwIjoxNzAxOTA2MzUyfQ.V81JTtWWsZEzFjaQI7yj2_1Lji6H87n3vWhDQAGsF5c' \
    --data '{
        "content":"What a wonderful world"
    }'
    
getAllPosts(get)

    curl --location 'http://localhost:8080/api/v1/posts' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MTA2OSwiZXhwIjoxNzAxODg0MjY5fQ.ZEGun0VbJBwMcS41DH44UODdMraJBQjnGxeh_Z9peuA' \
    --data ''
    
UpdatePost(put)

    curl --location --request PUT 'http://localhost:8080/api/v1/posts/1' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MTA2OSwiZXhwIjoxNzAxODg0MjY5fQ.ZEGun0VbJBwMcS41DH44UODdMraJBQjnGxeh_Z9peuA' \
    --data '{
        "content":"Obi is a boy"
    }'
    
DeletePost(delete)

    curl --location --request DELETE 'http://localhost:8080/api/v1/posts/1' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MjI5NywiZXhwIjoxNzAxODg1NDk3fQ.gJi7rWx0WxDf8c2JYMUaFFDi3TWZ6vidQFWKY87jZx0' \
    --data ''
    
Create comment(post)

    curl --location 'http://localhost:8080/api/v1/comments/1' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg2MzE1MiwiZXhwIjoxNzAxOTA2MzUyfQ.V81JTtWWsZEzFjaQI7yj2_1Lji6H87n3vWhDQAGsF5c' \
    --data '{
        "content":"What a wonderful world"
    }'
    
getAllComments(get)

    curl --location 'http://localhost:8080/api/v1/comments/post/1' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MTA2OSwiZXhwIjoxNzAxODg0MjY5fQ.ZEGun0VbJBwMcS41DH44UODdMraJBQjnGxeh_Z9peuA' \
    --data ''
    
UpdateComment(put)

    curl --location --request PUT 'http://localhost:8080/api/v1/comments/post/1' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MTA2OSwiZXhwIjoxNzAxODg0MjY5fQ.ZEGun0VbJBwMcS41DH44UODdMraJBQjnGxeh_Z9peuA' \
    --data '{
        "content":"Obi is a boy"
    }'
    
DeleteComment(delete)

    curl --location --request DELETE 'http://localhost:8080/api/v1/comments/post/1' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlcmhhYm9yb3NhZ2llQGdtYWlsLmNvbSIsImlhdCI6MTcwMTg0MjI5NywiZXhwIjoxNzAxODg1NDk3fQ.gJi7rWx0WxDf8c2JYMUaFFDi3TWZ6vidQFWKY87jZx0' \
    --data ''