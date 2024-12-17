# Указываем базовый образ
FROM maven:3.8-openjdk-18-slim AS build
WORKDIR /app

# Копируем все исходники и файл pom.xml в контейнер
COPY . /app

# Строим приложение
RUN mvn clean compile --file pom.xml

# Указываем базовый образ для финальной стадии
FROM openjdk:18-jdk-slim

# Копируем собранный артефакт из первого слоя
COPY --from=build /app/target/chertchill_task_5-*.jar /usr/local/lib/chertchill_task_5.jar

# Настроим контейнер для работы
CMD ["java", "-jar", "/usr/local/lib/chertchill_task_5.jar"]
