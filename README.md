# post
Microservicio para la gestión de publicaciones de la red social woloxgram

## Tabla de contenido
* [Tecnologías](#tecnologías)
* [Prerequisitos](#prerequisitos)
* [Instalación](#instalación)
* [Uso](#uso)

## Tecnologías
Proyecto creado con:
* Versión Java: 1.8
* Versión Spring-boot: 2.4.5
* Versión Maven: 3.6.1
* Eclipse sts

## Prerequisitos
* Versión Java 8 instalada
* Versión Maven: 3.6.1 or later instalada

## Instalación
Para correr este proyecto en local, ejecuta los siguientes comandos usando maven y java

```
$ cd /{workdir}/post
$ mvn clean install
$ cd target
$ java -jar post-0.0.1-SNAPSHOT.jar
```
Para correr este proyecto en docker, ejecuta los siguientes comandos:
```
$ cd /{workdir}/post
$ mvn clean install
$ (opcional, si la red aún no ha sido creada)docker network create --subnet 172.168.0.1/24 --gateway 172.168.0.2 -d bridge woloxgram-network
$ docker build -t post-microservice:1.0.0 .
$ docker run -d -p 9093:9093 --name PostMicroservice --network woloxgram-network --ip 172.168.0.24 post-microservice:1.0.0
```

## Uso
En el servicio post puede encontrar los siguientes endpoints
* **Obtener todas las publicaciones -** obtiene todas las publicaciones.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9093/posts```
* Si vas a consumirlo de producción  ```http://woloxgrampost.us-east-2.elasticbeanstalk.com/posts```

* **Obtener publicación por usuario -** obtiene la publicación para el usuario dado.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9093/posts?userId={userId}```
* Si vas a consumirlo de producción  ```http://woloxgrampost.us-east-2.elasticbeanstalk.com/posts?userId={userId}```

* **Obtener todos los comentarios por publicación -** obtiene todos los comentarios de una publicación.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9093/comments?postId={postId}```
* Si vas a consumirlo de producción  ```http://woloxgrampost.us-east-2.elasticbeanstalk.com/comments?postId={postId}```

* **Obtener todos los comentarios por usuario -** obtiene todos los comentarios para un usuario.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9093/comments?userId={userId}```
* Si vas a consumirlo de producción  ```http://woloxgrampost.us-east-2.elasticbeanstalk.com/comments?userId={userId}```

* **Obtener todos los comentarios por nombre -** obtiene todos los comentarios dado el nombre.\
Puedes crear una petición tipo GET a la siguiente url: 
* Si estas en local o ejecutando en docker  ```http://localhost:9093/comments?name={name}```
* Si vas a consumirlo de producción  ```http://woloxgrampost.us-east-2.elasticbeanstalk.com/comments?name={name}```


