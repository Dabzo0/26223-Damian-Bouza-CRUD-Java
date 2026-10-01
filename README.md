# Sistema de Gestión de Inventario (CRUD en Consola)

Una aplicación de consola robusta desarrollada en Java para la gestión de productos. Este proyecto implementa un CRUD (Create, Read, Update, Delete) completo en memoria, enfocado en la integridad de los datos, validaciones estrictas y una excelente experiencia de usuario (UX) en terminal.

# Características Principales

* **CRUD Completo:** Ingreso, listado general, búsqueda por ID, modificación y eliminación de productos.
* **Búsqueda y Filtrado Avanzado:** Capacidad de filtrar productos por coincidencias parciales (tanto en nombre como en código). Implementa `java.text.Normalizer` para realizar búsquedas insensibles a mayúsculas y acentos (ej. "limon" encuentra "LIMÓN").
* **Validación de Entradas Estricta:** Uso de la clase de utilidad `CapturarEntrada` para prevenir cuelgues (crashes) por tipos de datos incorrectos, evitar números negativos en precios/stock y bloquear la creación de códigos duplicados.
* **Actualización Parcial (Smart Update):** Flujo de modificación interactivo campo por campo que permite conservar los valores originales si el usuario decide no alterarlos.
* **Confirmación de Acciones:** Prevención de pérdida de datos mediante validaciones booleanas antes de sobrescribir o eliminar registros (Patrón de "Antes y Después").

# Arquitectura del Proyecto

El código está estructurado siguiendo el principio de separación de responsabilidades (Separation of Concerns), dividiendo el sistema en Modelos, Servicios y Utilidades:

                src/
                └── com/talento/crud/
                    ├── modelos/
                    │   └── ProductoModelo.java     # POJO (Plain Old Java Object) representativo de la entidad.
                    ├── servicios/
                    │   └── ProductoServicios.java  # Lógica de negocio y control del ArrayList de datos.
                    ├── utilidades/
                    │   └── CapturarEntrada.java    # Control de I/O, sanitización y normalización de strings.
                    └── App.java                    # Controlador del menú principal y punto de entrada.

# Tecnologías Utilizadas

    Java (Core / SE): Lógica del programa.

    Scanner: Para el manejo interactivo de entrada de datos (I/O) en consola.

    ArrayList: Estructura de datos dinámica para el almacenamiento en memoria (RAM).

    Expresiones Regulares (Regex) & Normalizer: Para el procesamiento avanzado de cadenas de texto.

# Cómo ejecutar el proyecto

    Clona este repositorio en tu máquina local.

    Abre el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, NetBeans, VS Code).

    Asegúrate de tener instalado el JDK 8 o superior.

    Compila y ejecuta el archivo principal App.java.

# Próximos Pasos

El proyecto se encuentra en transición hacia la persistencia de datos. Las próximas fases de desarrollo incluyen:

    [x] CRUD completo en memoria (RAM).

    [x] Validaciones de I/O y formato de consola.

    [ ] Fase 2: Integración con motor de Base de Datos Relacional (MySQL / PostgreSQL).

    [ ] Fase 2: Implementación del patrón de diseño DAO (Data Access Object).

    [ ] Fase 2: Conexión a la base de datos mediante JDBC.