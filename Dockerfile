FROM eclipse-temurin:17-jdk  # Use a versão do Java que seu projeto usa

WORKDIR /app
COPY backend .  # Copia o conteúdo da pasta backend para o container
RUN ./mvnw package -DskipTests  # Compila o projeto

CMD ["java", "-jar", "target/backend.jar"]  # Substitua pelo nome do seu JAR