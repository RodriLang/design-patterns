# Patrón State - Gestión de Reservas de Restaurante

## Descripción General

Este proyecto demuestra la implementación del **Patrón de Diseño State (Estado)** utilizando **Spring Boot**, a través de un sistema de gestión de reservas para un restaurante.

El objetivo es mostrar cómo el comportamiento de un objeto puede variar dinámicamente según su estado actual, evitando el uso excesivo de estructuras condicionales (`if/else` o `switch`) y favoreciendo un código más mantenible, extensible y alineado con los principios de diseño orientado a objetos.

---

## Problemática

En un sistema de reservas, una reserva atraviesa diferentes etapas durante su ciclo de vida.

```text
SOLICITADA → CONFIRMADA → OCUPADA → FINALIZADA
      ↓             ↓
   CANCELADA    CANCELADA
```

Cada estado define:

* Qué acciones están permitidas.
* A qué estados puede cambiar la reserva.
* Cómo debe comportarse la reserva mientras se encuentra en dicho estado.

| Estado actual | Acciones permitidas |
| ------------- | ------------------- |
| SOLICITADA    | Confirmar, Cancelar |
| CONFIRMADA    | Ocupar, Cancelar    |
| OCUPADA       | Finalizar           |
| FINALIZADA    | Ninguna             |
| CANCELADA     | Ninguna             |

Una implementación tradicional suele derivar en servicios con múltiples validaciones condicionales:

```java
if (reservation.getStatus() == ReservationStatus.REQUESTED) {
    // lógica
} else if (reservation.getStatus() == ReservationStatus.CONFIRMED) {
    // lógica
}
```

A medida que aumentan los estados y las reglas de negocio, el código se vuelve más complejo, difícil de mantener y propenso a errores.

---

## Comportamientos Dependientes del Estado

Además de controlar las transiciones válidas, cada estado implementa comportamientos específicos.

Por ejemplo, el cálculo de penalización por cancelación varía según el estado actual de la reserva:

| Estado    | Penalización |
| --------- | ------------ |
| REQUESTED | $0           |
| CONFIRMED | $1000        |
| OCCUPIED  | $5000        |
| COMPLETED | $0           |
| CANCELLED | $0           |

Asimismo, cada estado genera un mensaje diferente para el cliente:

| Estado    | Mensaje                                              |
| --------- | ---------------------------------------------------- |
| REQUESTED | La solicitud de reserva fue registrada correctamente |
| CONFIRMED | La reserva fue confirmada                            |
| OCCUPIED  | La mesa ya se encuentra ocupada                      |
| COMPLETED | La reserva fue finalizada correctamente              |
| CANCELLED | La reserva fue cancelada                             |

Sin el patrón State, este comportamiento normalmente implicaría múltiples estructuras condicionales distribuidas por toda la aplicación.

---

## Solución

El patrón **State** propone encapsular el comportamiento asociado a cada estado en clases independientes.

De esta forma, en lugar de preguntar constantemente:

> "¿En qué estado se encuentra la reserva?"

la aplicación delega la operación al objeto que representa el estado actual.

Cada estado es responsable de:

* Definir qué acciones son válidas.
* Gestionar las transiciones permitidas.
* Rechazar operaciones inválidas.
* Implementar comportamientos específicos.
* Mantener las reglas de negocio asociadas a dicho estado.

Esta implementación utiliza el patrón State para resolver dos problemas simultáneamente:

1. Controlar las transiciones válidas entre estados de una reserva.
2. Delegar comportamientos específicos al estado actual.

De esta manera, el patrón no solo funciona como una máquina de estados, sino también como un mecanismo para encapsular lógica de negocio dependiente del estado.

Esto permite cumplir con el principio **Open/Closed**, ya que es posible incorporar nuevos estados sin modificar significativamente el código existente.

---

## Estructura del Proyecto

```text
src/main/java/com/example/designpatterns/behavioral/state

├── controller
│   └── ReservationController
│
├── service
│   └── ReservationService
│
├── repository
│   └── ReservationRepository
│
├── model
│   ├── Reservation
│   └── ReservationStatus
│
├── states
│   ├── ReservationState
│   ├── RequestedState
│   ├── ConfirmedState
│   ├── OccupiedState
│   ├── CompletedState
│   └── CancelledState
│
├── resolver
│   └── ReservationStateResolver
│
└── exception
    └── InvalidStateTransitionException
```

---

## Contrato de Estados

Todos los estados implementan una interfaz común que define las acciones y comportamientos disponibles dentro del sistema.

```java
public interface ReservationState {

    ReservationStatus getStatus();

    void confirm(Reservation reservation);

    void markAsOccupied(Reservation reservation);

    void complete(Reservation reservation);

    void cancel(Reservation reservation);

    BigDecimal calculateCancellationPenalty();

    String getNotificationMessage();
}
```

Cada implementación concreta decidirá cuáles de estas acciones son válidas según el estado que representa.

---

## Estados Implementados

### RequestedState (Reserva Solicitada)

Representa una reserva recientemente creada.

**Acciones permitidas:**

* Confirmar reserva.
* Cancelar reserva.

**Comportamientos:**

* Penalización por cancelación: $0.
* Notificación de solicitud registrada.

---

### ConfirmedState (Reserva Confirmada)

Representa una reserva aceptada por el establecimiento.

**Acciones permitidas:**

* Ocupar reserva.
* Cancelar reserva.

**Comportamientos:**

* Penalización por cancelación: $1000.
* Notificación de reserva confirmada.

---

### OccupiedState (Reserva Ocupada)

Representa una reserva cuyos clientes ya se encuentran utilizando la mesa.

**Acciones permitidas:**

* Finalizar reserva.

**Comportamientos:**

* Penalización por cancelación: $5000.
* Notificación de mesa ocupada.

---

### CompletedState (Reserva Finalizada)

Representa una reserva concluida exitosamente.

**Acciones permitidas:**

* Ninguna.

**Comportamientos:**

* No permite nuevas transiciones.
* Notificación de reserva finalizada.

---

### CancelledState (Reserva Cancelada)

Representa una reserva cancelada.

**Acciones permitidas:**

* Ninguna.

**Comportamientos:**

* No permite nuevas transiciones.
* Notificación de reserva cancelada.

---

## Resolución del Estado Actual

Spring detecta automáticamente todas las implementaciones de `ReservationState` y el resolver construye un mapa entre cada estado y su implementación correspondiente.

```java
@Component
public class ReservationStateResolver {

    private final Map<ReservationStatus, ReservationState> states;

    public ReservationStateResolver(List<ReservationState> states) {
        this.states = states.stream()
                .collect(Collectors.toMap(
                        ReservationState::getStatus,
                        Function.identity()
                ));
    }

    public ReservationState resolve(ReservationStatus status) {
        return states.get(status);
    }
}
```

La aplicación determina qué implementación utilizar en función del estado almacenado en la reserva.

```java
ReservationState state =
        reservationStateResolver.resolve(
                reservation.getStatus()
        );
```

Una vez obtenido el estado correspondiente, el servicio delega la operación:

```java
state.confirm(reservation);
```

Gracias a esto, el servicio no necesita conocer las reglas específicas de cada estado.

---

## Ejemplo de Delegación de Comportamiento

La reserva cambia dinámicamente su comportamiento dependiendo del estado actual.

```java
Reservation reservation = findById(id);

ReservationState state =
        stateResolver.resolve(
                reservation.getStatus()
        );

BigDecimal penalty =
        state.calculateCancellationPenalty();

String notification =
        state.getNotificationMessage();
```

Dependiendo del estado actual de la reserva, los resultados serán diferentes sin necesidad de utilizar estructuras condicionales.

---

## Beneficios del Patrón State

### Eliminación de Condicionales Complejos

Reduce el uso de múltiples bloques `if/else` o `switch`.

### Mayor Mantenibilidad

Cada estado concentra únicamente la lógica que le corresponde.

### Extensibilidad

Permite agregar nuevos estados sin modificar el comportamiento existente.

### Responsabilidades Mejor Definidas

Las reglas de negocio se encuentran distribuidas de manera coherente y organizada.

### Código Más Legible

Las transiciones entre estados son explícitas y fáciles de comprender.

### Cumplimiento del Principio Open/Closed

Es posible incorporar nuevos estados sin modificar la lógica ya existente.

---

## Flujo de Ejemplo

### 1. Creación de la Reserva

```text
SOLICITADA
```

### 2. Confirmación

```text
SOLICITADA → CONFIRMADA
```

### 3. Llegada del Cliente

```text
CONFIRMADA → OCUPADA
```

### 4. Finalización

```text
OCUPADA → FINALIZADA
```

### 5. Cancelación

```text
SOLICITADA → CANCELADA

o

CONFIRMADA → CANCELADA
```

---

## Patrón Aplicado

### State (Estado)

El patrón State permite que un objeto modifique su comportamiento cuando cambia su estado interno. Desde la perspectiva del cliente, el objeto parece cambiar de comportamiento dinámicamente, sin necesidad de utilizar estructuras condicionales complejas.

En esta implementación, el patrón se utiliza tanto para gestionar las transiciones válidas entre estados como para encapsular comportamientos específicos asociados a cada uno de ellos.

Este patrón pertenece a la categoría de **Patrones de Comportamiento (Behavioral Patterns)** definida por el catálogo de patrones de diseño de Gang of Four (GoF).
