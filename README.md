# Sistema de Gestión de Barrios Privados


El objetivo del sistema es modelar una solución orientada a objetos para la administración operativa de barrios privados, contemplando la gestión de usuarios, viviendas, visitantes, accesos, reservas de amenidades, reclamos, incidentes, novedades y expensas administrativas.

---

## Alcance del sistema

El sistema contempla las siguientes funcionalidades principales:

* Gestión de barrios y viviendas.
* Gestión de usuarios del sistema.
* Registro de propietarios, administradores, técnicos y personal de seguridad.
* Gestión de visitantes como entidades del dominio.
* Autorización de visitas.
* Registro de ingresos y egresos.
* Gestión de amenidades.
* Reserva de amenidades.
* Registro y seguimiento de reclamos.
* Registro y seguimiento de incidentes.
* Publicación de novedades.
* Registro administrativo de expensas.

Las expensas se modelan únicamente como información administrativa de estado. El sistema no implementa pagos, cobranzas, facturación ni procesamiento financiero.

---

## Tecnologías utilizadas

* Java 21 LTS
* Maven
* UML
* Principios SOLID
* Patrones GRASP

---

## Arquitectura del proyecto

La estructura inicial del proyecto se organiza por responsabilidad:

```text
src/main/java/com/barrios
├── modelo
├── servicio
├── notificacion
├── estado
├── factory
├── proxy
└── main
```
---

## Compilación

Para compilar el proyecto:

```bash
mvn clean compile
```

---

## Ejecución

Para ejecutar la clase principal:

```bash
mvn exec:java
```

La ejecución inicial solo valida el arranque básico del sistema. La lógica funcional completa será desarrollada en etapas posteriores.

---
