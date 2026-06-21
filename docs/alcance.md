# Alcance del Proyecto

## Contexto

Este documento describe las decisiones de alcance tomadas durante el desarrollo del sistema de gestión de barrios privados.

El sistema fue construido como proyecto académico con foco en diseño orientado a objetos y aplicación de patrones de diseño. Las decisiones de alcance que se detallan a continuación no son limitaciones técnicas ni fallas: son elecciones deliberadas que permitieron priorizar la calidad del diseño, la correcta aplicación de patrones y la claridad de la demo funcional.

---

## Qué incluye el proyecto

| Tecnología / Funcionalidad | Detalle |
|---|---|
| **Java 21** | Lenguaje principal del sistema |
| **Spring Boot** | Framework de aplicación web |
| **Thymeleaf** | Motor de plantillas HTML para las vistas |
| **Datos en memoria** | `DatosDemoService` inicializa toda la información al arrancar; no hay persistencia externa |
| **Login simulado** | Selección de rol en la pantalla inicial sin autenticación real |
| **Roles** | `Rol` es un enum con cuatro valores: `ADMINISTRADOR`, `PROPIETARIO`, `SEGURIDAD`, `TECNICO` |
| **Separación por barrio** | Cada ruta `/barrios/{id}/...` opera como tenant; los datos de un barrio no son visibles desde otro |
| **Patrón State** | `IEstadoReclamo` con tres implementaciones: `EstadoPendiente`, `EstadoEnProceso`, `EstadoResuelto` |
| **Patrón Proxy** | `SistemaProxy` intercepta todas las operaciones del sistema y verifica permisos por rol antes de delegar a `GestionPrincipal` |
| **Patrón Singleton + Facade** | `GestionPrincipal` mantiene una única instancia y actúa como punto de entrada unificado a todos los servicios |
| **Patrón Observer** | `CentroNotificaciones` implementa `IObservable`; los observadores reciben eventos de reclamos, incidentes, novedades y reservas |
| **Patrón Factory** | `NotificacionFactory` instancia el tipo de notificación correspondiente según el evento |
| **Demo web** | Interfaz navegable desde el browser en `http://localhost:8080` |
| **Demo por consola** | Clase `Main` ejecutable con `mvn exec:java` que recorre el mismo flujo sin interfaz gráfica |

---

## Qué queda fuera del alcance

| Elemento | Decisión |
|---|---|
| **Base de datos** | Se optó por datos en memoria (`DatosDemoService`) para eliminar dependencias externas y mantener el foco en el diseño OO |
| **Autenticación real** | La selección de rol en pantalla es suficiente para demostrar el control de acceso sin agregar complejidad de infraestructura |
| **Spring Security** | No se incorporó para mantener el proyecto liviano y sin configuración adicional al momento de la demo |
| **JWT / tokens** | No aplica dado que no hay autenticación real |
| **Pagos reales** | El módulo de expensas es informativo; el sistema no procesa transacciones ni cobranzas |
| **Facturación** | Fuera del dominio modelado |
| **API REST** | El sistema expone una interfaz web HTML; no hay endpoints de consumo externo |
| **CRUD completo** | Las vistas muestran lectura de datos demo; los formularios de alta, baja y modificación no están implementados |
| **Deploy productivo** | El sistema está diseñado para correr localmente como demo académica, no para producción |
| **Tests unitarios** | No se incluyen; el foco estuvo en la demostración funcional de los patrones |

---

## Criterio general

Cada decisión buscó maximizar la claridad del diseño orientado a objetos y la demostrabilidad de los patrones aplicados, sin agregar complejidad accidental que no aportara valor al objetivo del proyecto.