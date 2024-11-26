# Etapa de build
FROM maven:3.9-amazoncorretto-23-debian AS builder
WORKDIR /usr/src/app

# Copiar apenas o arquivo de configuração inicialmente para otimizar o cache
COPY pom.xml .

# Copiar o código-fonte para o build
COPY src src
RUN mvn package -DskipTests

# Etapa final
FROM maven:3.9-amazoncorretto-23-debian
WORKDIR /app
COPY --from=builder /usr/src/app/target/smile-go-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
