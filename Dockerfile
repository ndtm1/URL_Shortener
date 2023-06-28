FROM openjdk:17-oracle
COPY ./target/UrlShortener-0.0.1-SNAPSHOT.jar /Users/nikitadegtarev/Containers/URL_Shortener/src/UrlShortener-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/Users/nikitadegtarev/Containers/URL_Shortener/src/UrlShortener-0.0.1-SNAPSHOT.jar"]


