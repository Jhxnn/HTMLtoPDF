# Usar uma imagem base do OpenJDK
FROM openjdk:11-jre-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR gerado (ajuste o nome do arquivo JAR conforme necessário)
COPY target/htmltopdf-0.0.1-SNAPSHOT.jar /app/htmltopdf.jar

# Expor a porta onde a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "htmltopdf.jar"]
