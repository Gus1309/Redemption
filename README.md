# Redemption - Sistema de Gestion de Barrios Privados

Proyecto universitario desarrollado en Java para demostrar la administracion operativa de barrios privados mediante una aplicacion web simple y una demo tecnica por consola.

El foco del proyecto esta puesto en diseno orientado a objetos, separacion de responsabilidades, aplicacion de patrones de diseno y demostracion funcional con datos en memoria. No es un sistema productivo ni pretende cubrir infraestructura real de autenticacion, persistencia o pagos.

## Estado del proyecto

La aplicacion permite navegar barrios, consultar sus modulos principales y ejecutar flujos operativos segun un rol simulado:

- Administracion y consulta de barrios.
- Consulta de viviendas, lotes y amenidades precargadas.
- Autorizacion de visitas.
- Registro de ingresos y egresos.
- Reservas de amenidades asociadas al propietario del lote ingresado.
- Creacion y seguimiento de reclamos.
- Registro y actualizacion de incidentes.
- Consulta de novedades.
- Consulta de expensas administrativas.
- Notificaciones internas basicas.
- Control de permisos mediante un proxy de dominio.

La informacion se guarda en memoria durante la ejecucion. Al reiniciar la aplicacion, los datos vuelven al estado inicial cargado por `DatosDemoService`.

## Stack tecnico

- Java 21
- Maven
- Spring Boot 3.3.7
- Spring Web
- Thymeleaf
- HTML y CSS
- Datos en memoria

No se usa base de datos, JPA, SQLite, JWT, Spring Security ni login real.

## Requisitos

- Java 21 instalado.
- Maven disponible en el PATH.
- Puerto `8080` libre para ejecutar la interfaz web.

## Como ejecutar

### Compilar

```bash
mvn clean compile
```

### Verificar build

```bash
mvn verify
```

### Ejecutar interfaz web

```bash
mvn spring-boot:run
```

Luego abrir:

```text
http://localhost:8080
```

### Ejecutar demo por consola

```bash
mvn exec:java
```

La clase `com.barrios.main.Main` recorre un flujo tecnico por consola: crea usuarios, barrios, viviendas, visitas, accesos, reservas, reclamos, incidentes, novedades, expensas, notificaciones y una prueba de permiso denegado.

Nota: la demo principal recomendada para la entrega es la interfaz web. La consola queda como apoyo tecnico para mostrar fachada, proxy, notificaciones y datos en memoria.

## Estructura principal

```text
src/main/java/com/barrios
|-- BarriosApplication.java
|-- controller
|-- estado
|-- factory
|-- main
|-- modelo
|-- notificacion
|-- proxy
|-- servicio

src/main/resources
|-- static/css/styles.css
|-- templates

docs
|-- Contribuciones.md
|-- alcance.md
|-- guia-demo.md

pom.xml
README.md
```

## Paquetes y responsabilidades

| Paquete | Responsabilidad |
|---|---|
| `com.barrios` | Clase de arranque Spring Boot: `BarriosApplication`. |
| `controller` | Controladores MVC que reciben requests web, cargan el `Model` y devuelven vistas Thymeleaf. |
| `modelo` | Entidades de dominio: barrio, usuarios, vivienda, visita, acceso, reserva, reclamo, incidente, novedad y expensa. |
| `servicio` | Casos de uso y coordinacion de operaciones del sistema. Incluye la fachada `GestionPrincipal`. |
| `proxy` | Proxy de proteccion por rol mediante `SistemaProxy`. |
| `estado` | Implementacion del patron State para el ciclo de vida de `Reclamo`. |
| `factory` | Simple Factory para crear notificaciones internas. |
| `notificacion` | Interfaces y clases para notificaciones internas mediante Observer. |
| `main` | Demo por consola con `Main`. |

## Arquitectura

El sistema esta organizado en capas simples:

- **Capa web MVC**: controllers Spring MVC y templates Thymeleaf.
- **Servicios de aplicacion**: clases `Gestion*` que encapsulan operaciones por modulo.
- **Modelo de dominio**: entidades Java puras con atributos y relaciones principales.
- **Fachada**: `GestionPrincipal` expone un punto de entrada unificado.
- **Proxy**: `SistemaProxy` valida permisos antes de delegar en la fachada.
- **Datos demo**: `DatosDemoService` inicializa informacion en memoria para la interfaz web.

Esta separacion permite mostrar una aplicacion funcional sin incorporar infraestructura externa.

## Rutas web principales

| Ruta | Vista / uso |
|---|---|
| `/` | Pantalla inicial con seleccion simulada de rol y, para propietario, numero de lote. |
| `/dashboard` | Resumen general de barrios. |
| `/diseno` | Vista de apoyo para explicar arquitectura y patrones. |
| `/barrios/{id}` | Panel de un barrio. |
| `/barrios/{id}/visitas` | Autorizaciones de visita. |
| `/barrios/{id}/visitas/nueva` | Formulario de nueva autorizacion de visita. |
| `/barrios/{id}/accesos` | Registro de ingresos y egresos. |
| `/barrios/{id}/reservas` | Reservas de amenidades. |
| `/barrios/{id}/reservas/nueva` | Formulario de nueva reserva. |
| `/barrios/{id}/reclamos` | Tablero de reclamos por estado. |
| `/barrios/{id}/reclamos/nuevo` | Formulario de nuevo reclamo. |
| `/barrios/{id}/incidentes` | Incidentes registrados. |
| `/barrios/{id}/incidentes/nuevo` | Formulario de nuevo incidente. |
| `/barrios/{id}/novedades` | Novedades publicadas. |
| `/barrios/{id}/expensas` | Expensas administrativas. |

## Roles y permisos

El sistema no tiene autenticacion real. El rol se simula mediante parametros de navegacion y se valida en la capa de dominio con `SistemaProxy`.

| Rol | Permisos principales |
|---|---|
| `ADMINISTRADOR` | Crear barrios, gestionar viviendas, gestionar amenidades, publicar novedades, registrar expensas y consultar informacion general. |
| `PROPIETARIO` | Autorizar visitas, reservar amenidades y crear reclamos. En la navegacion web puede ingresar numero de lote para asociar reservas al propietario real precargado. |
| `SEGURIDAD` | Registrar ingresos/egresos, atender reclamos y registrar incidentes. |
| `TECNICO` | Actualizar el estado de incidentes. |

El administrador funciona principalmente como perfil de consulta y gestion general. Las operaciones operativas quedan distribuidas entre propietario, seguridad y tecnico.

## Flujos web demostrables

| Flujo | Rol | Descripcion |
|---|---|---|
| Seleccionar rol/lote | Todos | La pantalla inicial permite navegar con un rol simulado. Para propietario, permite ingresar lote. |
| Consultar dashboard | Admin / roles habilitados | Muestra barrios y metricas generales. |
| Autorizar visita | Propietario | Crea una autorizacion de visitante con fecha desde/hasta. |
| Registrar ingreso o egreso | Seguridad | Registra movimientos sobre visitas autorizadas. |
| Crear reserva | Propietario | Reserva una amenidad disponible para una fecha y la asocia al propietario del lote cuando el lote coincide con una vivienda precargada. |
| Crear reclamo | Propietario | Registra un reclamo en estado `PENDIENTE`. |
| Atender reclamo | Seguridad | Avanza el reclamo por el flujo `PENDIENTE -> EN_PROCESO -> RESUELTO`. |
| Registrar incidente | Seguridad | Crea un incidente operativo en estado `ABIERTO`. |
| Actualizar incidente | Tecnico | Cambia el estado del incidente, por ejemplo a `EN_REVISION` o `RESUELTO`. |
| Consultar novedades | Todos / segun navegacion | Muestra comunicaciones precargadas. |
| Consultar expensas | Admin / propietario | Muestra expensas administrativas precargadas. |

## Datos precargados

`DatosDemoService` carga datos de ejemplo en memoria al iniciar la aplicacion web.

Incluye:

- Barrios `Los Robles` y `La Escondida`.
- Viviendas demo en `Los Robles` con lotes `15`, `98`, `23`, `47` y `61`.
- Propietarios demo asociados a esos lotes.
- Amenidades demo: quincho, pileta, SUM y cancha de futbol.
- Visitante y autorizacion de visita.
- Ingreso y egreso registrados.
- Reserva de amenidad.
- Reclamo con estado avanzado.
- Incidente en seguimiento.
- Novedad.
- Expensas administrativas.

Los datos se consultan siempre desde el contexto de un barrio (`/barrios/{id}/...`) para demostrar separacion por barrio. Las reservas pueden usar el parametro `lote` para resolver el propietario real asociado a una vivienda precargada.

## Patrones GoF aplicados

### Singleton

- Clase principal: `GestionPrincipal`.
- Implementacion: atributo estatico `instancia`, constructor privado y metodo `getInstancia()`.
- Uso: mantener una unica instancia central de la fachada durante la ejecucion.

### Facade

- Clase principal: `GestionPrincipal`.
- Interfaz: `ISistema`.
- Uso: exponer una API simple para operar barrios, visitas, accesos, reservas, reclamos, incidentes, novedades y expensas.

### Proxy

- Clase principal: `SistemaProxy`.
- Interfaz comun: `ISistema`.
- Uso: validar permisos segun rol antes de delegar en `GestionPrincipal`.

### Simple Factory

- Clase principal: `NotificacionFactory`.
- Producto: `INotificacion`.
- Uso: centralizar la creacion de notificaciones internas segun tipo de evento.
- Aclaracion: no implementa canales externos como email, SMS o push.

### Observer

- Clases principales: `CentroNotificaciones`, `IObservable`, `IObserver`, `HistorialNotificaciones`, `ObserverConsola`.
- Uso: permitir que observadores reciban mensajes ante eventos internos.
- Aclaracion: el historial de notificaciones se demuestra principalmente por consola/codigo; no hay bandeja web de notificaciones.

### State

- Clases principales: `Reclamo`, `IEstadoReclamo`, `EstadoPendiente`, `EstadoEnProceso`, `EstadoResuelto`.
- Flujo real: `PENDIENTE -> EN_PROCESO -> RESUELTO`.
- Uso: encapsular las transiciones del reclamo en clases de estado.

## SOLID observado

| Principio | Aplicacion en el proyecto |
|---|---|
| SRP | Controllers, servicios y entidades tienen responsabilidades separadas. |
| OCP | Los estados de reclamo y las notificaciones pueden extenderse agregando implementaciones. |
| LSP | Las subclases de `Usuario` pueden usarse donde el sistema espera un `Usuario`. |
| ISP | Interfaces chicas para Observer, State y notificaciones; `ISistema` actua como contrato general de fachada. |
| DIP | `SistemaProxy` depende de `ISistema`, no de una clase concreta. |

## GRASP observado

| Patron GRASP | Aplicacion |
|---|---|
| Controller | Los controllers MVC reciben la interaccion web y delegan en servicios. |
| Creator | Servicios y controllers crean objetos necesarios para cada flujo. |
| Information Expert | `Barrio` concentra las colecciones del barrio; `Reclamo` conoce su estado. |
| Low Coupling | El proxy, la fachada y las interfaces reducen dependencias directas. |
| High Cohesion | Cada servicio agrupa operaciones de un modulo funcional. |
| Polymorphism | Estados de reclamo y roles concretos usan comportamiento polimorfico. |
| Protected Variations | `ISistema`, State, Proxy y Observer aislan puntos de cambio. |

## Tests y validacion

No hay una suite de tests automatizados en `src/test/java`.

La validacion actual se realiza mediante:

- Compilacion con Maven.
- Ejecucion web con datos precargados.
- Demo por consola con `Main`.
- Navegacion manual de flujos por rol.

Comando recomendado antes de entregar:

```bash
mvn verify
```

## Evidencia recomendada para la entrega

- Build exitoso (`mvn verify`).
- Arranque de Spring Boot.
- Pantalla inicial con seleccion de rol/lote.
- Dashboard.
- Visita creada por propietario.
- Acceso registrado por seguridad.
- Reserva creada por propietario y asociada al lote.
- Reclamo creado por propietario.
- Reclamo avanzado por seguridad.
- Incidente creado por seguridad.
- Incidente actualizado por tecnico.
- Expensas administrativas visibles.
- Denegacion de permisos mediante `SistemaProxy`.

## Alcance y limitaciones

El proyecto no incluye:

- Base de datos.
- Persistencia real.
- Autenticacion real.
- Spring Security.
- JWT o tokens.
- API REST externa.
- Pagos, cobranzas o facturacion.
- Deploy productivo.
- Tests automatizados.

Estas decisiones son parte del alcance academico: se priorizo el modelo de dominio, los patrones, la separacion por capas y una demo funcional simple.

## Historial y aportes

El historial Git muestra aportes de:

- Augusto Miguez: integracion general, arquitectura, Spring Boot, frontend, flujos web por rol y resolucion de conflictos.
- Dante Favereau: funcionalidades web de visitas, reclamos, reservas, accesos, validaciones y preservacion de contexto de navegacion.
- Juan Cegielski: documentacion y aportes iniciales vinculados a estados, factory/notificaciones y consola.

Comandos utiles para revisar aportes:

```bash
git shortlog -sn --all
git log --oneline --author="AgusMig"
git log --oneline --author="DanteFavereau"
git log --oneline --author="Colocegi"
```

## Mejoras futuras

- Persistencia con base de datos.
- Autenticacion real.
- Spring Security.
- API REST.
- Tests unitarios e integracion.
- CRUD completo para todos los modulos.
- Historial web de notificaciones.
- Deploy productivo.
