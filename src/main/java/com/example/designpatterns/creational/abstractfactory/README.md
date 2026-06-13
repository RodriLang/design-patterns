# Abstract Factory - Notification System

## Problema

Una plataforma necesita enviar notificaciones a sus usuarios a través de distintos canales de comunicación:

* Email
* SMS
* Push Notifications

Cada canal posee características particulares:

* El formato del mensaje es diferente.
* El mecanismo de envío es diferente.
* Nuevos canales pueden incorporarse en el futuro.

Por ejemplo, una notificación de bienvenida podría representarse de la siguiente manera:

| Canal | Formato                                            |
| ----- | -------------------------------------------------- |
| Email | HTML enriquecido                                   |
| SMS   | Texto plano                                        |
| Push  | Mensaje breve optimizado para dispositivos móviles |

Una implementación tradicional basada en condicionales podría verse así:

```java
if(channel == EMAIL){
    formatter = new EmailFormatter();
    sender = new EmailSender();
}
else if(channel == SMS){
    formatter = new SmsFormatter();
    sender = new SmsSender();
}
else if(channel == PUSH){
    formatter = new PushFormatter();
    sender = new PushSender();
}
```

A medida que se agregan nuevos canales, esta solución se vuelve difícil de mantener, aumenta el acoplamiento y viola el principio Open/Closed.

---

## Solución

Para resolver este problema se implementó el patrón **Abstract Factory**.

Este patrón permite crear familias de objetos relacionados sin depender de sus implementaciones concretas.

En este caso:

### Fábrica Abstracta

```java
NotificationFactory
```

Define cómo crear los componentes necesarios para enviar una notificación.

### Productos Abstractos

```java
MessageFormatter
MessageSender
```

Representan las capacidades requeridas por cualquier canal.

### Fábricas Concretas

```java
EmailNotificationFactory
SmsNotificationFactory
PushNotificationFactory
```

Cada una conoce cómo crear los productos correspondientes a su canal.

### Productos Concretos

```java
EmailFormatter
EmailSender

SmsFormatter
SmsSender

PushFormatter
PushSender
```

Cada producto implementa el comportamiento específico de un canal de comunicación.

---

## Estructura

```text
NotificationFactory
│
├── EmailNotificationFactory
│   ├── EmailFormatter
│   └── EmailSender
│
├── SmsNotificationFactory
│   ├── SmsFormatter
│   └── SmsSender
│
└── PushNotificationFactory
    ├── PushFormatter
    └── PushSender
```

Cada fábrica crea una familia consistente de objetos que trabajan correctamente entre sí.

---

## Beneficios Obtenidos

### Bajo Acoplamiento

El servicio de notificaciones trabaja únicamente contra interfaces:

```java
NotificationFactory
MessageFormatter
MessageSender
```

sin depender de implementaciones concretas.

### Cumplimiento del Principio Open/Closed

Para incorporar un nuevo canal, por ejemplo WhatsApp, únicamente sería necesario crear:

```java
WhatsAppNotificationFactory
WhatsAppFormatter
WhatsAppSender
```

sin modificar la lógica existente.

### Mayor Mantenibilidad

Las responsabilidades de formateo y envío se encuentran separadas y encapsuladas en componentes específicos.

### Escalabilidad

La arquitectura permite incorporar nuevas familias de productos sin afectar el comportamiento actual del sistema.

---

## Trade-Offs

Como contrapartida, el patrón introduce:

* Mayor cantidad de clases.
* Mayor complejidad inicial.
* Más abstracciones que una implementación basada en condicionales.

Sin embargo, estos costos se justifican cuando el sistema debe soportar múltiples variantes de productos y se espera que evolucione con el tiempo.

---

## Conclusión

El patrón Abstract Factory resulta especialmente útil cuando una aplicación necesita trabajar con múltiples familias de objetos relacionados y garantizar que los componentes creados sean compatibles entre sí.

En este ejemplo, permitió desacoplar la lógica de negocio de los detalles específicos de cada canal de comunicación, facilitando la extensión y el mantenimiento del sistema a medida que se incorporan nuevos mecanismos de notificación.
