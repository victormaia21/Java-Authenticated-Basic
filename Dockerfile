# Etapa 1: Compilação
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho como /app
WORKDIR /app

# Copia o arquivo pom.xml e outras dependências para o contêiner
COPY pom.xml .

# Baixa as dependências do Maven
RUN mvn dependency:go-offline -B

# Copia o código-fonte do aplicativo para o contêiner
COPY src ./src

# Compila o projeto e empacota-o como um JAR
RUN mvn package -DskipTests

# Etapa 2: Criação da imagem final
FROM openjdk:17-jdk

# Define o diretório de trabalho como /app
WORKDIR /app

# Copia o arquivo JAR do aplicativo Spring Boot da etapa de construção para o contêiner
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta definida para o Spring Boot
EXPOSE 8080

# Comando a ser executado quando o contêiner for iniciado
CMD ["java", "-jar", "app.jar"]
