# Usa una imagen de Java como base
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR de la aplicación al contenedor
COPY target/*.jar sistemaventas.jar

EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "sistemaventas.jar"]
