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
* Patrones de diseño GoF
* Principios SOLID
* Patrones GRASP

El proyecto no utiliza frameworks externos, base de datos ni dependencias adicionales, ya que el objetivo de esta etapa es representar el diseño orientado a objetos y las relaciones principales entre clases.

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

### `com.barrios.modelo`

Contiene las clases principales del dominio del sistema.

Incluye entidades como:

* `Usuario`
* `Administrador`
* `Propietario`
* `Tecnico`
* `PersonalSeguridad`
* `Visitante`
* `Barrio`
* `Vivienda`
* `Amenidad`
* `AutorizacionVisita`
* `RegistroAcceso`
* `ReservaAmenidad`
* `Reclamo`
* `Incidente`
* `Novedad`
* `Expensa`

`Usuario` se define como clase abstracta.
`Visitante` no hereda de `Usuario`, ya que no se autentica ni opera directamente el sistema.

---

### `com.barrios.servicio`

Contiene las clases responsables de coordinar operaciones del sistema.

Incluye:

* `ISistema`
* `GestionPrincipal`
* servicios específicos de gestión

`GestionPrincipal` se modela como una fachada principal del sistema y aplica el patrón Singleton. Su responsabilidad es coordinar operaciones y delegar en servicios específicos, evitando concentrar lógica de negocio.

---

### `com.barrios.notificacion`

Contiene las interfaces y clases vinculadas al sistema de notificaciones.

Se utiliza para representar el patrón Observer y desacoplar los eventos del sistema de la forma en que se notifican.

Incluye:

* `INotificacion`
* `IObserver`
* `IObservable`

---

### `com.barrios.estado`

Contiene las clases asociadas al patrón State aplicado a los reclamos.

Incluye:

* `IEstadoReclamo`
* `EstadoPendiente`
* `EstadoEnProceso`
* `EstadoResuelto`

Este paquete permite representar los cambios de estado de un reclamo sin concentrar la lógica en condicionales dentro de la clase principal.

---

### `com.barrios.factory`

Contiene la fábrica encargada de crear notificaciones según el tipo de evento.

Incluye:

* `NotificacionFactory`

Este paquete representa la aplicación del patrón Factory Method o Simple Factory.

---

### `com.barrios.proxy`

Contiene la implementación del patrón Proxy.

Incluye:

* `SistemaProxy`

El proxy controla el acceso a determinadas operaciones del sistema antes de delegarlas a la fachada principal.

---

### `com.barrios.main`

Contiene la clase principal de ejecución.

Incluye:

* `Main`

Su objetivo es validar el arranque básico del sistema y demostrar la inicialización de la estructura principal.

---

## Patrones de diseño aplicados

### Singleton

Aplicado en `GestionPrincipal`.

Permite contar con una única instancia coordinadora del sistema.

### Facade

Aplicado en `GestionPrincipal`.

Permite exponer un punto de entrada simplificado a las operaciones generales del sistema.

### Proxy

Aplicado en `SistemaProxy`.

Permite controlar permisos antes de acceder a las operaciones principales.

### Factory Method / Simple Factory

Aplicado en `NotificacionFactory`.

Permite centralizar la creación de notificaciones según el tipo de evento.

### Observer

Aplicado en el mecanismo de notificaciones.

Permite que determinados objetos sean notificados ante cambios relevantes, como actualizaciones de reclamos o incidentes.

### State

Aplicado en la gestión de estados de `Reclamo`.

Permite encapsular el comportamiento de cada estado y evitar condicionales extensos.

---

## Principios de diseño considerados

El diseño busca respetar los siguientes principios:

* **SRP:** cada clase debe tener una responsabilidad principal.
* **OCP:** el sistema puede extenderse con nuevos tipos de notificación o estados sin modificar la lógica existente.
* **DIP:** las clases de alto nivel dependen de interfaces cuando corresponde.
* **Bajo acoplamiento:** los servicios, notificaciones y estados se vinculan mediante abstracciones.
* **Alta cohesión:** las clases se agrupan por responsabilidad dentro de paquetes específicos.

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
