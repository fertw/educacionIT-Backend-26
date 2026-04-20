# java-Backend

# Clase 2 – Java Backend

## Etapa 2: Arquitectura y Primeros Endpoints REST

---

### 🎯 Objetivo de la clase

Comprender la **arquitectura de una aplicación Spring Boot**, cómo se comunican las capas (Controller → Service → Repository → Model) y cómo se manejan las solicitudes HTTP en una API REST.  
Implementar los primeros **endpoints del proyecto LimpiezaIT**, incorporando controladores, servicios y repositorios en memoria para probar las operaciones básicas.

---

### 🧠 Temas vistos en clase

#### 🏗️ Arquitectura Spring Boot

- Estructura en capas: **Controller → Service → Repository → Model**
- Flujo de una petición REST (del cliente al servidor y viceversa)
- Responsabilidad de cada capa en la aplicación

#### ⚙️ Anotaciones principales de Spring

- `@RestController` → Define un controlador REST
- `@RequestMapping` → Establece la ruta base del recurso
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` → Vinculan métodos HTTP

#### 💡 Inversión de Control (IoC) e Inyección de Dependencias (DI)

- **IoC (Inversión de Control):** el framework (Spring) crea y gestiona los objetos (beans)
- **Contenedor de Spring:** almacena y entrega instancias cuando se necesitan
- **Ventajas:** código más modular, reutilizable y fácil de mantener

#### 🌐 Desarrollo de endpoints REST en LimpiezaIT

- Creación del **controlador `ProductoController`**
- Implementación del **servicio `ProductoService`**
- Repositorio en memoria **`ProductoRepository`** (uso de `ArrayList` / `Map`)
- Pruebas de la API con **Postman** (`GET`, `POST`, `PUT`, `DELETE`, `BUSCAR`)

---

### 🧱 Estructura base del proyecto LimpiezaIT

#### 🧩 Controller – `ProductoController.java`

```java
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        Producto p = service.getById(id);
        return (p == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto p) {
        Producto creado = service.create(p);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto p) {
        Producto actualizado = service.update(id, p);
        return (actualizado == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
```
