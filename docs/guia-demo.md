# Guía de Demo

Paso a paso para mostrar el sistema durante la presentación.

## Requisitos previos

- Java 21 instalado
- Maven disponible en el PATH
- Puerto 8080 libre

---

## Pasos de la demo

### 1. Iniciar la aplicación

```bash
mvn spring-boot:run
```

Esperar hasta que Spring Boot indique que el servidor arrancó en el puerto 8080.

---

### 2. Abrir el sistema en el navegador

```
http://localhost:8080
```

---

### 3. Seleccionar rol: ADMINISTRADOR

En la pantalla inicial, elegir el rol **ADMINISTRADOR**.

El sistema tiene cuatro roles definidos en el enum `Rol`: `ADMINISTRADOR`, `PROPIETARIO`, `SEGURIDAD` y `TÉCNICO`. El rol ADMINISTRADOR tiene acceso a consulta general, creación de barrios, gestión de viviendas y amenidades, novedades y expensas.

---

### 4. Seleccionar barrio: Los Robles

Elegir el barrio **Los Robles**.

`DatosDemoService` inicializa en memoria un conjunto completo de datos para Los Robles: vivienda, amenidad, visita autorizada, ingreso, egreso, reserva, reclamo resuelto, incidente, novedad y expensa. Cada ruta `/barrios/{id}/...` filtra por barrio como tenant, sin mezclar datos.

---

### 5. Dashboard

Ir a `/dashboard`.

Muestra el resumen general del barrio: conteo de visitas, accesos, reservas, reclamos, incidentes, novedades y expensas cargados en memoria para el barrio seleccionado.

---

### 6. Visitas

Ir a `/barrios/{id}/visitas`.

Lista las autorizaciones de visita registradas con nombre del visitante, fecha y estado de autorización.

---

### 7. Accesos

Ir a `/barrios/{id}/accesos`.

Muestra el registro de ingresos y egresos. Cada `RegistroAcceso` tiene tipo (INGRESO/EGRESO), visitante y timestamp.

---

### 8. Reservas

Ir a `/barrios/{id}/reservas`.

Lista las reservas de amenidades (`ReservaAmenidad`) con propietario, amenidad, fecha y estado de confirmación.

---

### 9. Reclamos — patrón State

Ir a `/barrios/{id}/reclamos`.

Cada `Reclamo` tiene un estado que implementa `IEstadoReclamo`. Las tres implementaciones son:

- `EstadoPendiente` → al avanzar, pasa a `EstadoEnProceso`
- `EstadoEnProceso` → al avanzar, pasa a `EstadoResuelto`
- `EstadoResuelto` → estado terminal

El patrón State encapsula las transiciones válidas en objetos independientes. En la demo se muestra el reclamo ya resuelto cargado por `DatosDemoService`.

---

### 10. Incidentes

Ir a `/barrios/{id}/incidentes`.

Lista los incidentes registrados con descripción, estado y fecha. La gestión de incidentes también dispara notificaciones vía `CentroNotificaciones`.

---

### 11. Novedades

Ir a `/barrios/{id}/novedades`.

Muestra los comunicados publicados para el barrio. La publicación de una novedad notifica a los observadores registrados en `CentroNotificaciones`.

---

### 12. Expensas administrativas

Ir a `/barrios/{id}/expensas`.

Muestra los montos y períodos de expensas. El sistema no procesa pagos ni facturación; las expensas son informativas.

---

### 13. Cambiar a barrio: La Escondida — separación de datos

Volver al selector y elegir **La Escondida**.

`DatosDemoService` no carga datos en La Escondida intencionalmente. Al navegar por las mismas secciones se obtienen listas vacías, demostrando que el sistema aísla datos por barrio sin que exista ninguna filtración entre tenants.

---

### 14. Diseño OO

Ir a la sección **Diseño OO** (`/diseno`).

Muestra la estructura orientada a objetos del sistema y los patrones aplicados:

| Patrón | Clase(s) |
|---|---|
| **Singleton** | `GestionPrincipal` — instancia única via `getInstance()` |
| **Facade** | `GestionPrincipal` — punto de entrada único a todos los servicios |
| **Proxy** | `SistemaProxy` — intercepta operaciones y verifica permisos por rol antes de delegar |
| **Factory** | `NotificacionFactory` — crea el tipo de notificación según el evento |
| **Observer** | `CentroNotificaciones` / `IObservable` / `IObserver` — notifica a suscriptores ante eventos |
| **State** | `IEstadoReclamo` / `EstadoPendiente` / `EstadoEnProceso` / `EstadoResuelto` |

---

## Demo por consola (alternativa)

La clase `Main` ejecuta el mismo flujo sin interfaz web:

```bash
mvn exec:java
```