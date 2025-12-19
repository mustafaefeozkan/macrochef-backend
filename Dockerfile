# 1. Aşama: Maven ile Projeyi Derle (Build)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 2. Aşama: Uygulamayı Çalıştır (Run)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# target klasöründen jar dosyasını alıp app.jar adıyla kopyalıyoruz
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]