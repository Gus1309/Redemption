# Redemption - Sistema de Gestion de Barrios Privados

Proyecto universitario en Java para demostrar la administracion operativa de barrios privados. La version actual mantiene la demo por consola y agrega una interfaz web simple con Spring Boot + Thymeleaf.

El foco principal del trabajo es mostrar diseno orientado a objetos, separacion de responsabilidades, patrones de diseno y una aplicacion web funcional con datos demo en memoria.

## Alcance

El sistema permite visualizar y demostrar:

- Gestion de multiples barrios.
- Separacion simple de datos por barrio.
- Usuarios con roles basicos.
- Viviendas, visitantes y autorizaciones.
- Registro de ingresos y egresos.
- Reservas de amenidades.
- Reclamos con estados.
- Incidentes.
- Novedades.
- Expensas administrativas.
- Notificaciones basicas.
- Permisos basicos mediante proxy.

Las expensas son solo administrativas. El sistema no procesa pagos, cobranzas ni facturacion.

## Stack

- Java 21
- Maven
- Spring Boot
- Thymeleaf
- HTML y CSS
- Datos en memoria

No se usa base de datos, JPA, SQLite, JWT, Spring Security ni login real.

## Como ejecutar

### Compilar

```bash
mvn clean compile
```

### Interfaz web

```bash
mvn spring-boot:run
```

URL principal:

```text
http://localhost:8080
```

### Demo por consola

La clase `Main` sigue disponible para demostrar el flujo sin interfaz web:

```bash
mvn exec:java
```

## Estructura

```text
src/main/java/com/barrios
|-- BarriosApplication.java
|-- controller      # Capa web MVC: recibe requests y devuelve vistas Thymeleaf.
|-- estado          # Patron State para el ciclo de vida de Reclamo.
|-- factory         # Factory para crear notificaciones.
|-- main            # Demo por consola.
|-- modelo          # Entidades de dominio: Barrio, Usuario, Reclamo, Reserva, etc.
|-- notificacion    # Observer y abstracciones de notificacion.
|-- proxy           # Proxy de proteccion por rol.
|-- servicio        # Casos de uso y fachada principal del sistema.

src/main/resources
|-- static/css/styles.css
|-- templates
```

## Arquitectura

El proyecto esta organizado en capas simples:

- **Modelo de dominio**: clases que representan conceptos del negocio, como `Barrio`, `Usuario`, `Vivienda`, `Reclamo`, `Incidente`, `Expensa` y `ReservaAmenidad`.
- **Servicios**: clases que concentran operaciones del sistema, por ejemplo `GestionBarrios`, `GestionVisitas`, `GestionReclamos`, `GestionReservas`, `GestionIncidentes` y `GestionExpensas`.
- **Fachada principal**: `GestionPrincipal`, que ofrece un punto de entrada unico para operar con los servicios.
- **Proxy de permisos**: `SistemaProxy`, que valida si el rol del usuario puede ejecutar una operacion antes de delegarla.
- **Capa web MVC**: controllers Spring MVC que cargan datos en el `Model` y devuelven templates Thymeleaf.
- **Vistas**: templates HTML en `src/main/resources/templates`.

La aplicacion no persiste en base de datos. Los datos se inicializan en memoria desde `DatosDemoService`, lo que permite demostrar el funcionamiento completo sin agregar infraestructura externa.

## Rutas web

Rutas principales:

- `/`: inicio.
- `/dashboard`: resumen de barrios.
- `/barrios/{id}`: detalle de un barrio.
- `/barrios/{id}/visitas`: visitas autorizadas.
- `/barrios/{id}/accesos`: ingresos y egresos.
- `/barrios/{id}/reservas`: reservas de amenidades.
- `/barrios/{id}/reclamos`: reclamos agrupados por estado.
- `/barrios/{id}/incidentes`: incidentes.
- `/barrios/{id}/novedades`: novedades publicadas.
- `/barrios/{id}/expensas`: expensas administrativas.

## Datos demo

`DatosDemoService` inicializa datos en memoria para:

- **Los Robles**: tiene vivienda, amenidad, visita autorizada, ingreso, egreso, reserva, reclamo resuelto, incidente, novedad y expensa.
- **La Escondida**: queda sin esos datos para demostrar que no se mezclan los datos entre barrios.

Cada ruta `/barrios/{id}/...` toma el barrio como contexto y muestra solo su informacion.

## Roles

El sistema usa roles simples para demostrar autorizacion mediante Proxy:

- `ADMINISTRADOR`: crea barrios, gestiona viviendas, amenidades, novedades, expensas y consulta informacion general.
- `PROPIETARIO`: autoriza visitas, realiza reservas y crea reclamos.
- `SEGURIDAD`: registra ingresos y egresos.
- `TECNICO`: gestiona incidentes y atiende reclamos.

## Patrones aplicados

- **Singleton**: `GestionPrincipal`.
  Mantiene una instancia central del sistema.

- **Facade**: `GestionPrincipal`.
  Expone una interfaz simplificada para acceder a operaciones de barrios, visitas, accesos, reservas, reclamos, incidentes, novedades y expensas.

- **Proxy**: `SistemaProxy`.
  Valida permisos segun rol antes de delegar la operacion al sistema real.

- **Factory**: `NotificacionFactory`.
  Centraliza la creacion de notificaciones y evita instanciaciones concretas dispersas.

- **Observer**: `CentroNotificaciones`, `IObservable`, `IObserver`.
  Permite notificar eventos desacoplando al emisor de los receptores.

- **State**: `Reclamo`, `IEstadoReclamo`, `EstadoPendiente`, `EstadoEnProceso`, `EstadoResuelto`.
  Modela el flujo de un reclamo: `PENDIENTE -> EN_PROCESO -> RESUELTO`.

## SOLID y GRASP

- **Responsabilidad unica**: los controllers solo atienden requests y devuelven vistas; la logica queda en servicios y modelos.
- **Abierto/Cerrado**: las notificaciones y los estados pueden extenderse agregando nuevas clases sin concentrar toda la logica en condicionales.
- **Inversion de dependencias**: `SistemaProxy` y `GestionPrincipal` trabajan contra la interfaz `ISistema`.
- **Bajo acoplamiento**: Observer desacopla emisores y receptores de notificaciones.
- **Alta cohesion**: cada paquete agrupa clases con una responsabilidad clara.
- **Controller GRASP**: los controllers web reciben eventos del usuario y coordinan con servicios.
- **Information Expert**: las entidades de dominio conservan la informacion propia de cada concepto del sistema.

## Decisiones tecnicas para defender

- Se eligio Java 21 y Maven por ser un stack estable para proyectos academicos Java.
- Se uso Spring Boot para levantar una aplicacion web simple sin configuracion manual excesiva.
- Se uso Thymeleaf porque permite renderizar vistas HTML desde el servidor de forma directa.
- Se mantuvieron datos en memoria para priorizar el diseno OO, patrones y flujo funcional.
- No se agrego Spring Security ni base de datos para no ampliar el alcance del trabajo.
- La separacion por barrio se demuestra filtrando y navegando siempre desde un barrio especifico.
- Las expensas son administrativas: no hay pagos, facturacion ni cobranzas reales.

## Guia rapida para defensa oral

1. Mostrar `GestionPrincipal` para explicar Singleton + Facade.
2. Mostrar `SistemaProxy` para explicar permisos por rol.
3. Mostrar `Reclamo` y el paquete `estado` para explicar State.
4. Mostrar `CentroNotificaciones`, `IObserver` e `IObservable` para explicar Observer.
5. Mostrar `NotificacionFactory` para explicar Factory.
6. Mostrar un controller, por ejemplo `BarrioWebController`, para explicar MVC.
7. Mostrar `DatosDemoService` para explicar datos en memoria y alcance del proyecto.
8. Ejecutar `mvn clean compile` para validar que el proyecto compila.
9. Ejecutar `mvn spring-boot:run` y navegar a `http://localhost:8080`.

## Mejoras futuras

- Persistencia con base de datos.
- API REST.
- Autenticacion real.
- Spring Security.
- Tests unitarios e integracion.
- Formularios CRUD completos.
