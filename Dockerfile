 FROM openjdk:11
 EXPOSE 9090
 ADD target/AuthentificationJWT.jar AuthentificationJWT.jar
 ENTRYPOINT ["java","-jar","/AuthentificationJWT.jar"]