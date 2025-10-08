# Platzi Market API

Aplicación REST construida con Spring Boot para gestionar un pequeño mercado: productos, categorías y compras. El proyecto sigue una arquitectura por capas (domain, persistence y web) y utiliza JPA con PostgreSQL, mapeos con MapStruct y documentación con OpenAPI/Swagger.

## Tecnologías y librerías
- Java 21
- Spring Boot (Web, Data JPA)
- PostgreSQL (driver JDBC)
- MapStruct (mapeo de entidades a modelos de dominio)
- springdoc-openapi (Swagger UI)
- Gradle

## Requisitos previos
- JDK 21
- Acceso a una base de datos PostgreSQL (el proyecto ya referencia una instancia en Supabase). 
- Variable de entorno DB_PASSWORD configurada con la contraseña del usuario de BD.

## Perfiles y configuración
La aplicación define perfiles dev y pdn.

- application.properties
  - spring.profiles.active=pdn (perfil activo por defecto)
  - springdoc.swagger-ui.path=/swagger-ui.html
- application-dev.properties
  - server.port=8080
  - server.servlet.context-path=/platzi-market/api
  - Configuración de datasource hacia PostgreSQL (usa ${DB_PASSWORD})
- application-pdn.properties
  - server.port=8081
  - server.servlet.context-path=/platzi-market/api
  - Configuración de datasource hacia PostgreSQL (usa ${DB_PASSWORD})

Puedes cambiar el perfil activo de tres formas:
- Editando application.properties (spring.profiles.active=dev)
- Usando un argumento de JVM: -Dspring.profiles.active=dev
- Usando variable de entorno: SPRING_PROFILES_ACTIVE=dev

Asegúrate de exportar la variable DB_PASSWORD antes de ejecutar la app. Ejemplos:
- Windows PowerShell: $Env:DB_PASSWORD = "tu_password"
- Linux/macOS (bash/zsh): export DB_PASSWORD="tu_password"

## Cómo ejecutar
Con Gradle Wrapper (recomendado):
- Compilar: ./gradlew build (Linux/macOS) o .\gradlew.bat build (Windows)
- Ejecutar según perfil activo:
  - Dev: ./gradlew bootRun -Dspring.profiles.active=dev
  - Prod/demo (pdn): ./gradlew bootRun -Dspring.profiles.active=pdn

Puertos y path base según perfil:
- Dev: http://localhost:8080/platzi-market/api
- Pdn: http://localhost:8081/platzi-market/api

## Documentación OpenAPI (Swagger)
- Swagger UI: {baseUrl}/swagger-ui.html
  - Ejemplos:
    - Dev: http://localhost:8080/platzi-market/api/swagger-ui.html
    - Pdn: http://localhost:8081/platzi-market/api/swagger-ui.html
- OpenAPI JSON: {baseUrl}/v3/api-docs

## Endpoints principales
Los controladores están bajo el paquete com.platzi.platzi.market.web.controller

Productos (/products)
- GET /products
  - Obtiene todos los productos.
- GET /products/{id}
  - Obtiene un producto por su ID.
- GET /products/category/{id}
  - Obtiene productos por ID de categoría.
- GET /products/stock/{stock}/{status}
  - Obtiene productos con stock menor a {stock} y con estado {status}.
- POST /products
  - Crea/guarda un producto (cuerpo JSON de Product).
- DELETE /products/{id}
  - Elimina un producto por ID.

Compras (/purchases)
- GET /purchases
  - Obtiene todas las compras.
- GET /purchases/customer/{id}
  - Obtiene compras por ID de cliente.
- POST /purchases
  - Crea/guarda una compra (cuerpo JSON de Purchase, con su detalle de productos).

Nota: Todas las rutas heredan el context-path /platzi-market/api configurado en los properties del perfil.

## Estructura del proyecto
- src/main/java/com/platzi/platzi/market
  - PlatziMarketApplication.java (clase principal)
  - domain
    - model: Product, Category, Purchase, PurchaseProduct
    - repository: interfaces del dominio (ProductRepository, PurchaseRepository)
    - service: lógica de negocio (ProductService, PurchaseService)
  - persistence
    - crud: repositorios JPA (ProductCRUDRepository, PurchaseCRUDRepository)
    - entity: entidades JPA (ProductData, CategoryData, PurchaseData, PurchaseProductData, etc.)
    - mapper: mapeadores MapStruct (ProductMapper, CategoryMapper, PurchaseMapper, PurchaseProductMapper)
    - ProductDataRepository, PurchaseDataRepository (implementaciones de repos del dominio)
  - web
    - controller: controladores REST (ProductController, PurchaseController, HolaMundoController)
    - config: SwaggerConfig
- src/main/resources
  - application.properties, application-dev.properties, application-pdn.properties

## Decisiones técnicas destacadas
- Separación clara entre modelos de dominio y entidades de persistencia usando MapStruct.
- Repositorios del dominio desacoplados de JPA vía adaptadores en el paquete persistence.
- Documentación de API expuesta con springdoc-openapi y configurada para escanear los controladores del paquete web.controller.

## Ejemplos de uso (curl)
Reemplaza {base} por el baseUrl del perfil activo.

- Obtener todos los productos:
  curl -X GET "{base}/products"

- Obtener producto por ID (ej. 7):
  curl -X GET "{base}/products/7"

- Crear producto:
  curl -X POST "{base}/products" \
       -H "Content-Type: application/json" \
       -d '{
            "productId": 0,
            "name": "Manzana",
            "categoryId": 1,
            "price": 1.5,
            "stock": 100,
            "active": true
           }'

## Pruebas
- Ejecutar tests: ./gradlew test

## Problemas comunes
- 401/403/500 al acceder a la BD: verifica que la variable DB_PASSWORD esté configurada correctamente y que la IP tenga acceso a la instancia de PostgreSQL.
- Swagger UI no carga: confirma la URL incluyendo el context-path y el perfil/puerto correcto, y que springdoc.swagger-ui.path sea /swagger-ui.html.

## Licencia
No especificada en el repositorio. Añádela si corresponde.
