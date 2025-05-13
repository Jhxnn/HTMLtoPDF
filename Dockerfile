# Usar uma imagem base do Maven para construir o JAR
FROM maven:3.8.6-openjdk-17-slim AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o código fonte do projeto para o container
COPY . /app

# Rodar o Maven para gerar o arquivo .jar
RUN mvn clean package -DskipTests

# Usar uma imagem base do OpenJDK para rodar a aplicação
FROM openjdk:11-jre-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado do estágio de build para o container
COPY --from=build /app/target/*.jar /app/app.jar

# Expor a porta onde a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/app.jar"]
