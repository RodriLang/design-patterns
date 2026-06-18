# Patrón de Diseño: Proxy

Este proyecto es una implementación sencilla y directa del patrón de diseño estructural **Proxy** en Java (usando Spring Boot). 

## 📝 Descripción

El patrón Proxy proporciona un sustituto o intermediario para otro objeto con el fin de controlar el acceso a él. En este ejemplo, simulamos una conexión a una base de datos pesada y utilizamos un proxy para resolver dos problemas comunes:

1. **Proxy Virtual (Lazy Loading):** La conexión real a la base de datos no se instancia ni consume memoria hasta que el cliente hace la primera consulta real.
2. **Proxy de Caché:** El proxy guarda en memoria los resultados de consultas previas. Si el cliente pide los mismos datos, el proxy responde al instante sin tocar la base de datos real.

## 🏗️ Estructura del Proyecto

* **`DatabaseService` (La Interfaz):** Define el contrato principal. El cliente solo conoce esta interfaz, ignorando si habla con el proxy o con la base de datos real.
* **`RealDatabaseService` (El Objeto Real):** Simula el servicio pesado. Emula un retraso (`Thread.sleep`) al conectarse a la base de datos y al procesar consultas.
* **`DatabaseProxy` (El Intermediario):** Controla el acceso a la base de datos real. Gestiona la inicialización diferida y la caché en memoria usando un `HashMap`.
* **`DesignPatternsApplication` (El Cliente):** La clase principal donde se ejecuta la demostración práctica del patrón.

## 🚀 Salida Esperada (Demostración)

Al ejecutar la aplicación, el sistema realiza tres consultas estratégicas para demostrar la eficiencia del Proxy. Esto es lo que verás en la consola:

```text
Design Patterns Application is running...

=== 1° Consulta (La BD se inicializa y tarda) ===
⚠️ [PROXY] La BD real no está inicializada. Creándola ahora...
⚙️ [BD Real] Conectando a la base de datos H2... (Operación costosa)
🔄 [BD Real] Ejecutando query en H2: SELECT * FROM usuarios
Resultados de 'SELECT * FROM usuarios' -> [Registro 1, Registro 2, Registro 3]
Tiempo total: 3500 ms

=== 2° Consulta (Misma query: Debería ser instantánea gracias al Proxy) ===
⚡ [PROXY] ¡Caché encontrada! Evitando llamada a H2.
Resultados de 'SELECT * FROM usuarios' -> [Registro 1, Registro 2, Registro 3]
Tiempo total: 0 ms

=== 3° Consulta (Nueva query: Va a la BD pero ya está inicializada) ===
🔄 [BD Real] Ejecutando query en H2: SELECT * FROM productos
Resultados de 'SELECT * FROM productos' -> [Registro 1, Registro 2, Registro 3]
Tiempo total: 2000 ms