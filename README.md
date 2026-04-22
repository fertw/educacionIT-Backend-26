# Clase 3 – Java Backend

## Etapa 3: Serialización, Deserialización y uso de DTO en Spring Boot

---

### 🎯 Objetivo de la clase

Comprender cómo funciona la **serialización y deserialización** de datos en una API REST con Spring Boot,  
y aplicar el uso de **DTO (Data Transfer Object)** para controlar qué datos se envían y reciben,  
manteniendo la lógica simple y clara dentro del proyecto **LimpiezaIT**.

---

### 🧠 Temas vistos en clase

#### 📦 Serialización y Deserialización

- **Serialización:** convertir objetos Java en JSON para enviarlos al cliente.
- **Deserialización:** convertir el JSON recibido en objetos Java que la aplicación pueda manejar.
- Spring Boot realiza ambos procesos automáticamente mediante la librería **Jackson**.

#### ⚙️ Librerías de serialización más comunes

| Librería    | Uso principal            | Notas                                       |
| ----------- | ------------------------ | ------------------------------------------- |
| **JSONP**   | Estándar Java EE         | Lectura/escritura básica de JSON            |
| **Jackson** | _Default en Spring Boot_ | Rápida, flexible y muy completa             |
| **Gson**    | De Google                | Simple, ideal para proyectos pequeños       |
| **JAXB**    | Para XML                 | Útil en integraciones con sistemas antiguos |

#### 🔄 Proceso dentro de Spring Boot

1. El cliente envía un **JSON** en una solicitud HTTP (`@RequestBody`).
2. Spring Boot lo **deserializa** automáticamente a un objeto Java.
3. La aplicación procesa el objeto en su capa de servicio.
4. Spring Boot **serializa** el resultado en formato JSON y lo envía como respuesta.

#### 🧩 Uso de DTO (Data Transfer Object)

- Un **DTO** es un objeto simple para transportar datos entre capas.
- Permite controlar qué información se expone o recibe desde la API.
- En este proyecto se implementa **sin validadores ni mappers**,  
  realizando el mapeo directo dentro del servicio.

---

### 🧱 Estructura base del proyecto LimpiezaIT

#### 🧾 DTO – `ProductoDTO.java`

```java
package com.eit.demo.dto;

public record ProductoDTO(
    Long id,
    String nombre,
    Double precio,
    String descripcion,
    String urlFoto
) {}
```
