# Sistema de Gestión de Barrios Privados

Proyecto final universitario orientado a modelar e implementar una versión funcional, demostrable y extensible de un sistema de gestión para barrios privados.

La entrega actual está implementada en Java puro, con datos en memoria, sin frameworks externos ni base de datos. El objetivo de esta versión es demostrar los casos de uso principales del sistema, los patrones de diseño aplicados y una separación clara entre dominio, servicios, permisos, notificaciones y demo por consola.

---

## Alcance implementado

El sistema permite demostrar:

* Gestión de múltiples barrios.
* Separación de datos por barrio.
* Usuarios con roles básicos.
* Viviendas asociadas a propietarios.
* Visitantes y autorizaciones de visita.
* Registro de ingresos y egresos.
* Reservas de amenidades.
* Reclamos con cambio de estado.
* Incidentes.
* Novedades.
* Expensas administrativas.
* Notificaciones básicas.
* Permisos por rol mediante proxy.

Las expensas se modelan únicamente como información administrativa. No se implementan pagos, cobranzas, facturación ni procesamiento financiero.

---

## Tecnologías

* Java 21
* Maven
* Java puro
* Datos en memoria
* Sin Spring Boot
* Sin base de datos
* Sin dependencias externas innecesarias

---

## Estructura del proyecto

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

## Paquetes principales

### `modelo`

Contiene las entidades principales del dominio:

* `Barrio`
* `Vivienda`
* `Usuario`
* `Administrador`
* `Propietario`
* `PersonalSeguridad`
* `Tecnico`
* `Rol`
* `Visitante`
* `AutorizacionVisita`
* `RegistroAcceso`
* `Amenidad`
* `ReservaAmenidad`
* `Reclamo`
* `Incidente`
* `Novedad`
* `Expensa`

`Barrio` funciona como raíz simple del enfoque multitenant: cada barrio mantiene sus propias viviendas, amenidades, visitantes, autorizaciones, accesos, reservas, reclamos, incidentes, novedades y expensas.

---

### `servicio`

Contiene la lógica reutilizable del sistema. Los servicios no dependen de `Scanner` ni de la consola; devuelven objetos de dominio o resultados de operación.

Servicios principales:

* `GestionPrincipal`: fachada principal y Singleton.
* `GestionBarrios`: gestión de barrios, viviendas y amenidades.
* `GestionVisitas`: autorizaciones de visita.
* `RegistroAccesos`: ingresos y egresos.
* `GestionReservas`: reservas de amenidades.
* `GestionReclamos`: reclamos y avance de estado.
* `GestionIncidentes`: registro y actualización de incidentes.
* `GestionNovedades`: publicación de novedades.
* `GestionExpensas`: expensas administrativas.
* `ResumenBarrio`: resumen de datos por barrio.
* `ResultadoOperacion`: resultado reutilizable de las operaciones del sistema.

---

### `proxy`

Contiene la implementación del patrón Proxy.

`SistemaProxy` aplica un proxy de protección: recibe el usuario, valida su rol y delega en `GestionPrincipal` solamente si la operación está permitida.

---

### `estado`

Implementa el patrón State aplicado a los reclamos.

Flujo de estados:

```text
PENDIENTE → EN_PROCESO → RESUELTO
```

Clases principales:

* `IEstadoReclamo`
* `EstadoPendiente`
* `EstadoEnProceso`
* `EstadoResuelto`

---

### `notificacion`

Implementa el mecanismo de notificaciones mediante Observer.

Clases principales:

* `IObservable`
* `IObserver`
* `CentroNotificaciones`
* `HistorialNotificaciones`
* `INotificacion`
* `NotificacionSistema`

---

### `factory`

Contiene la creación centralizada de notificaciones.

* `NotificacionFactory`

Este componente permite crear notificaciones según el tipo de evento del sistema.

---

### `main`

Contiene la clase `Main`, utilizada únicamente para ejecutar la demo por consola. La lógica principal se encuentra en los servicios, no en la consola.

---

## Roles y permisos

### `ADMINISTRADOR`

Puede:

* Crear barrios.
* Gestionar viviendas.
* Gestionar amenidades.
* Publicar novedades.
* Registrar expensas.
* Consultar información general.

### `PROPIETARIO`

Puede:

* Autorizar visitas.
* Reservar amenidades.
* Crear reclamos.

### `SEGURIDAD`

Puede:

* Registrar ingresos.
* Registrar egresos.

### `TECNICO`

Puede:

* Registrar incidentes.
* Actualizar incidentes.
* Atender reclamos.

---

## Patrones aplicados

* **Singleton:** `GestionPrincipal` mantiene una única instancia de la fachada principal.
* **Facade:** `GestionPrincipal` expone un punto de entrada simplificado y delega en servicios específicos.
* **Proxy:** `SistemaProxy` valida permisos antes de ejecutar operaciones.
* **Factory:** `NotificacionFactory` centraliza la creación de notificaciones por tipo.
* **Observer:** `CentroNotificaciones` notifica a observadores como `HistorialNotificaciones`.
* **State:** `Reclamo` cambia entre `EstadoPendiente`, `EstadoEnProceso` y `EstadoResuelto`.

---

## Compilación

Para compilar el proyecto:

```bash
mvn clean compile
```

---

## Ejecución de la demo

Para ejecutar la demo por consola:

```bash
mvn exec:java
```

---

## Flujo demostrado por la demo

La clase `Main` ejecuta un flujo completo del sistema:

1. Inicia el sistema.
2. Crea los barrios `Los Robles` y `La Escondida`.
3. Crea usuarios con roles: administrador, propietario, seguridad y técnico.
4. Crea una vivienda y la asocia a un propietario.
5. Crea una amenidad.
6. Autoriza una visita en `Los Robles`.
7. Registra ingreso y egreso del visitante.
8. Reserva una amenidad.
9. Crea un reclamo.
10. Avanza el reclamo de `PENDIENTE` a `EN_PROCESO` y luego a `RESUELTO`.
11. Genera notificaciones.
12. Registra y actualiza un incidente.
13. Publica una novedad.
14. Registra una expensa administrativa.
15. Muestra una operación rechazada por permisos.
16. Muestra resumen por barrio.
17. Demuestra que `La Escondida` no tiene los datos cargados en `Los Robles`.

---

## Salida esperada

```text
=== SISTEMA DE GESTION DE BARRIOS PRIVADOS ===

[OK] Barrio creado: Los Robles
[OK] Barrio creado: La Escondida

=== VISITAS ===
[OK] Visita autorizada para Juan Perez en Los Robles
[OK] Ingreso registrado correctamente
[OK] Egreso registrado correctamente

=== RECLAMOS ===
[OK] Reclamo creado en estado PENDIENTE
[OK] Reclamo actualizado a EN_PROCESO
[NOTIFICACION_RECLAMO] Reclamo #1 actualizado a EN_PROCESO
[OK] Reclamo actualizado a RESUELTO
[NOTIFICACION_RECLAMO] Reclamo #1 actualizado a RESUELTO

=== PERMISOS ===
[DENEGADO] El usuario PROPIETARIO no puede registrar expensas

=== RESUMEN POR BARRIO ===
Los Robles:
- Reclamos: 1
- Reservas: 1
- Accesos: 2
- Incidentes: 1
- Expensas: 1

La Escondida:
- Reclamos: 0
- Reservas: 0
- Accesos: 0
- Incidentes: 0
- Expensas: 0
```

---

## Multitenancy simple

El multitenancy se resuelve utilizando `Barrio` como contenedor principal de datos. Las operaciones de los servicios reciben un barrio y almacenan la información dentro de esa instancia. De esta manera, los datos de `Los Robles` no se mezclan con los datos de `La Escondida`.

Esta solución es simple y funcional para la entrega final. Además, deja preparado el diseño para evolucionar posteriormente hacia una base de datos con una clave `barrioId`.

---

## Preparación para evolución futura

El proyecto quedó organizado para poder conectarse a una API REST o a un frontend en una etapa posterior:

* `Main` solo ejecuta la demo.
* Los servicios devuelven resultados y objetos.
* No se utiliza `Scanner`.
* La lógica no está hardcodeada en consola.
* Los permisos están centralizados en `SistemaProxy`.
* Los resúmenes se devuelven como objetos mediante `ResumenBarrio`.

---

## Mejoras futuras

* Agregar frontend con React, Vite y TypeScript.
* Exponer una API REST con Spring Boot.
* Persistir datos en SQLite, PostgreSQL u otra base de datos.
* Agregar autenticación real con sesiones o JWT.
* Incorporar tests unitarios.
* Agregar búsquedas y filtros.
* Mejorar validaciones de fechas, duplicados y disponibilidad de amenidades.
* Agregar estados más completos para reservas, incidentes y expensas.
