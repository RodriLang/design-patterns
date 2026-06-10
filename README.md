# Design Patterns

Repositorio dedicado a la implementación práctica de patrones de diseño utilizando Java, con el objetivo de demostrar conocimientos de diseño orientado a objetos, principios SOLID y buenas prácticas de arquitectura de software.

A diferencia de una colección de ejemplos teóricos, este proyecto busca mostrar cómo los patrones pueden utilizarse para resolver problemas reales de diseño, favoreciendo la mantenibilidad, extensibilidad y escalabilidad de las aplicaciones.

## Objetivo

Los patrones de diseño representan soluciones probadas a problemas recurrentes en el desarrollo de software. Sin embargo, conocer su definición no es suficiente; es fundamental comprender cuándo utilizarlos, qué problemas resuelven y cuáles son los compromisos asociados a su implementación.

Este repositorio tiene como finalidad:

* Aplicar patrones de diseño en escenarios prácticos.
* Demostrar conocimientos de diseño orientado a objetos.
* Implementar soluciones alineadas con los principios SOLID.
* Analizar ventajas, limitaciones y casos de uso de cada patrón.
* Construir ejemplos claros, mantenibles y fácilmente extensibles.
* Servir como referencia técnica para desarrolladores y equipos de desarrollo.

## Tecnologías Utilizadas

* Java
* Maven
* JUnit
* Git

## Categorías de Patrones

### Creational Patterns

Patrones enfocados en la creación de objetos de manera flexible y desacoplada.

* Singleton
* Factory Method
* Abstract Factory
* Builder
* Prototype

### Structural Patterns

Patrones orientados a la composición de clases y objetos para formar estructuras más complejas.

* Adapter
* Bridge
* Composite
* Decorator
* Facade
* Flyweight
* Proxy

### Behavioral Patterns

Patrones centrados en la comunicación y colaboración entre objetos.

* Chain of Responsibility
* Command
* Iterator
* Mediator
* Memento
* Observer
* State
* Strategy
* Template Method
* Visitor

> La lista de patrones implementados se ampliará progresivamente a medida que evolucione el proyecto.

## Estructura del Proyecto

```text
src/main/java/
└── com.designpatterns
    ├── creational
    ├── structural
    └── behavioral
```

Cada patrón se organiza de forma independiente para facilitar su comprensión y análisis.

Las implementaciones pueden incluir:

* Código fuente.
* Casos de uso.
* Diagramas UML.
* Explicación del problema que resuelve.
* Ventajas y desventajas.
* Consideraciones de diseño.

## Principios Aplicados

Durante el desarrollo de los ejemplos se busca aplicar conceptos fundamentales de ingeniería de software:

* Single Responsibility Principle (SRP)
* Open/Closed Principle (OCP)
* Liskov Substitution Principle (LSP)
* Interface Segregation Principle (ISP)
* Dependency Inversion Principle (DIP)
* Programación orientada a interfaces
* Bajo acoplamiento
* Alta cohesión
* Composición sobre herencia

## Compilación y Ejecución

### Clonar el repositorio

```bash
git clone <repository-url>
cd design-patterns
```

### Compilar el proyecto

```bash
mvn clean compile
```

### Ejecutar pruebas

```bash
mvn test
```

### Generar artefacto

```bash
mvn clean package
```

## Valor Profesional

Este proyecto forma parte de un portfolio orientado al desarrollo de software y tiene como propósito evidenciar capacidades relacionadas con:

* Diseño de software orientado a objetos.
* Aplicación práctica de patrones de diseño.
* Modelado de soluciones mantenibles y extensibles.
* Toma de decisiones arquitectónicas.
* Desarrollo de código limpio y desacoplado.
* Buenas prácticas de ingeniería de software.

## Contributors

- Ignacio Rebeiro
- Joaquín Aman
- Rodrigo Lang

Proyecto colaborativo orientado al estudio y aplicación práctica de patrones de diseño y buenas prácticas de desarrollo de software.

## Licencia

Proyecto desarrollado con fines de aprendizaje continuo, práctica profesional y difusión de buenas prácticas de desarrollo de software.
