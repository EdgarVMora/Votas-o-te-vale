# Guía Profesional para la Creación de Proyectos JavaFX con Apache Maven

Este documento proporciona un procedimiento estandarizado para la creación de proyectos JavaFX utilizando Apache Maven como herramienta de gestión y construcción. Se asume que el entorno de desarrollo Java (JDK) ya está configurado y que se utiliza un IDE compatible con Maven, como Visual Studio Code.

---

## 1. Inicialización del Proyecto Maven

1. **Crear un nuevo proyecto Maven**  
   - Abre la paleta de comandos de tu IDE (`Cmd + Shift + P` en VS Code).
   - Ejecuta: `Maven: Create Maven Project`.

2. **Seleccionar Arquetipo**  
   - Elige `maven-archetype-quickstart` para una estructura básica de proyecto Java.

3. **Definir Coordenadas del Proyecto**  
   - `groupId`: Identificador único de la organización (ej. `com.suorganizacion`).
   - `artifactId`: Nombre único del proyecto (ej. `nombre-del-proyecto-javafx`).
   - `version`: Versión inicial (ej. `1.0.0-SNAPSHOT`).
   - `package`: Paquete base (ej. `com.suorganizacion.proyecto`).

4. **Seleccionar Ubicación y Abrir Proyecto**  
   - Elige el directorio de destino y abre la carpeta del proyecto en tu IDE.

---

## 2. Configuración del Archivo `pom.xml`

El archivo `pom.xml` define las propiedades, dependencias y plugins del proyecto.

### Plantilla base para `pom.xml`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.sugroupid</groupId>
    <artifactId>su-artifact-id</artifactId>
    <version>1.0.0-SNAPSHOT</version>

    <name>Nombre Descriptivo del Proyecto</name>
    <url>http://url.del.proyecto.com</url>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <javafx.version>21.0.2</javafx.version>
        <javafx.maven.plugin.version>0.0.8</javafx.maven.plugin.version>
        <main.class>com.sugroupid.supaquete.App</main.class>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>${javafx.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>${javafx.maven.plugin.version}</version>
                <configuration>
                    <mainClass>${main.class}</mainClass>
                </configuration>
                <executions>
                    <execution>
                        <id>default-cli</id>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```

> **Nota:** Ajusta los valores de `groupId`, `artifactId`, `name`, `url` y `main.class` según tu proyecto.

Guarda y recarga la configuración de Maven tras modificar el `pom.xml`.

---

## 3. Estructura del Proyecto y Desarrollo

Maven define una estructura estándar:

- **Código fuente:** `src/main/java/`
- **Recursos:** `src/main/resources/`
- **Pruebas:** `src/test/java/`
- **Recursos de pruebas:** `src/test/resources/`

### Ejemplo de Clase Principal JavaFX

Crea la estructura de paquetes y la clase principal en `src/main/java/com/sugroupid/supaquete/App.java`:

```java
package com.sugroupid.supaquete; // Ajustar al paquete real

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label helloLabel = new Label("Hola, JavaFX con Maven!");
        StackPane root = new StackPane(helloLabel);
        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("Mi Aplicación JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### (Opcional) Clases de Prueba

Crea pruebas unitarias en `src/test/java/` siguiendo la misma estructura de paquetes.

---

## 4. Compilación y Ejecución

Utiliza los siguientes comandos Maven desde la raíz del proyecto:

- **Compilar:**  
  ```sh
  mvn compile
  ```
- **Ejecutar pruebas:**  
  ```sh
  mvn test
  ```
- **Empaquetar:**  
  ```sh
  mvn package
  ```
- **Instalar:**  
  ```sh
  mvn install
  ```
- **Limpiar:**  
  ```sh
  mvn clean
  ```
- **Ejecutar aplicación JavaFX:**  
  ```sh
  mvn javafx:run
  ```

---

## 5. Documentación del Proyecto (`README.md`)

Incluye siempre un archivo `README.md` con:

- **Descripción del Proyecto:** Propósito y funcionalidades.
- **Prerrequisitos:** Software necesario (JDK, Maven, etc.).
- **Instrucciones de Configuración:** Pasos para preparar el entorno.
- **Compilación:** Cómo compilar el proyecto.
- **Ejecución:** Cómo ejecutar la aplicación.
- **Pruebas:** Cómo correr las pruebas unitarias.
- **Información adicional:** Contribuciones, licenciamiento, contacto, etc.

---

