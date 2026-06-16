# Sistema de Gestion de Barrios Privados

Proyecto universitario en Java para demostrar la administracion operativa de barrios privados. La version actual mantiene la demo por consola y agrega una interfaz web simple con Spring Boot + Thymeleaf.

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

## Estructura

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
```

## Como compilar

```bash
mvn clean compile
```

## Demo por consola

La clase `Main` sigue disponible:

```bash
mvn exec:java
```

## Interfaz web

Para levantar la aplicacion web:

```bash
mvn spring-boot:run
```

URL principal:

```text
http://localhost:8080
```

Rutas principales:

- `/`
- `/dashboard`
- `/barrios/{id}`
- `/barrios/{id}/visitas`
- `/barrios/{id}/accesos`
- `/barrios/{id}/reservas`
- `/barrios/{id}/reclamos`
- `/barrios/{id}/incidentes`
- `/barrios/{id}/novedades`
- `/barrios/{id}/expensas`

## Datos demo

`DatosDemoService` inicializa datos en memoria para:

- Los Robles: tiene vivienda, amenidad, visita autorizada, ingreso, egreso, reserva, reclamo resuelto, incidente, novedad y expensa.
- La Escondida: queda sin esos datos para demostrar que no se mezclan los datos entre barrios.

Cada ruta `/barrios/{id}/...` toma el barrio como tenant y muestra solo su informacion.

## Patrones aplicados

- Singleton: `GestionPrincipal`.
- Facade: `GestionPrincipal`.
- Proxy: `SistemaProxy`.
- Factory: `NotificacionFactory`.
- Observer: `CentroNotificaciones`.
- State: estados de `Reclamo`.

## Mejoras futuras

- Persistencia con base de datos.
- API REST.
- Autenticacion real.
- Spring Security.
- Tests unitarios e integracion.
- Formularios CRUD completos.
