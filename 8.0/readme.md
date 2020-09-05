
```sh
docker-compose up

mvn spring-boot:run

http://localhost:8080/reservations
http://localhost:9999/proxy/reservations

curl -v -H "Host: 7788.spring.io" http://localhost:9999/proxy
curl -vu user:password -H "Host: 7788.spring.io" http://localhost:9999/proxy

while true; do curl -vu user:password -H "Host: 7788.spring.io" http://localhost:9999/proxy; done
```