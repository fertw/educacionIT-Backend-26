# 🧪 Pruebas con Postman — JWT LimpiezaIT

## Configuración previa

- La app debe estar corriendo en `http://localhost:8080`
- En todos los requests protegidos: pestaña **Authorization** → Type: **Bearer Token** → pegar el token

---

## 1. Autenticación

### 1.1 Login como ADMIN

```
POST http://localhost:8080/auth/login
```

Body (raw → JSON):

```json
{
    "username": "admin",
    "password": "admin123"
}
```

Respuesta esperada (200 OK):

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcy...",
    "username": "admin"
}
```

> ✅ Copiar el token para usarlo en los siguientes requests.

---

### 1.2 Login como USER

```
POST http://localhost:8080/auth/login
```

Body (raw → JSON):

```json
{
    "username": "user",
    "password": "password"
}
```

Respuesta esperada (200 OK):

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "username": "user"
}
```

---

### 1.3 Login con credenciales incorrectas

```
POST http://localhost:8080/auth/login
```

Body (raw → JSON):

```json
{
    "username": "admin",
    "password": "contraseñaMal"
}
```

Respuesta esperada: **401 Unauthorized** o **403 Forbidden**

---

### 1.4 Registrar usuario nuevo

```
POST http://localhost:8080/auth/register
```

Body (raw → JSON):

```json
{
    "username": "fernando",
    "password": "miPassword123"
}
```

Respuesta esperada (200 OK):

```
Usuario registrado correctamente
```

---

## 2. Productos — Con token de ADMIN

> Usar el token obtenido en 1.1

### 2.1 Listar todos los productos

```
GET http://localhost:8080/api/productos
```

Headers:

```
Authorization: Bearer <token_admin>
```

Respuesta esperada: **200 OK** con la lista de productos.

---

### 2.2 Buscar producto por ID

```
GET http://localhost:8080/api/productos/1
```

Headers:

```
Authorization: Bearer <token_admin>
```

Respuesta esperada: **200 OK** con los datos del producto.

---

### 2.3 Crear un producto

```
POST http://localhost:8080/api/productos
```

Headers:

```
Authorization: Bearer <token_admin>
Content-Type: application/json
```

Body (raw → JSON):

```json
{
    "nombre": "Lavandina Concentrada",
    "precio": 1500.0,
    "descripcion": "Lavandina concentrada 1 litro",
    "url_foto": "https://ejemplo.com/lavandina.jpg"
}
```

Respuesta esperada: **201 Created** con el producto creado.

---

### 2.4 Actualizar un producto

```
PUT http://localhost:8080/api/productos/1
```

Headers:

```
Authorization: Bearer <token_admin>
Content-Type: application/json
```

Body (raw → JSON):

```json
{
    "nombre": "Lavandina Concentrada Premium",
    "precio": 1800.0,
    "descripcion": "Lavandina concentrada premium 1 litro",
    "url_foto": "https://ejemplo.com/lavandina-premium.jpg"
}
```

Respuesta esperada: **200 OK** con el producto actualizado.

---

### 2.5 Eliminar un producto

```
DELETE http://localhost:8080/api/productos/1
```

Headers:

```
Authorization: Bearer <token_admin>
```

Respuesta esperada: **200 OK** o **204 No Content**

---

## 3. Clientes — Con token de ADMIN

### 3.1 Listar todos los clientes

```
GET http://localhost:8080/api/clientes
```

Headers:

```
Authorization: Bearer <token_admin>
```

Respuesta esperada: **200 OK** con la lista de clientes.

---

### 3.2 Crear un cliente

```
POST http://localhost:8080/api/clientes
```

Headers:

```
Authorization: Bearer <token_admin>
Content-Type: application/json
```

Body (raw → JSON):

```json
{
    "nombre": "Juan Pérez",
    "email": "juan@email.com",
    "telefono": "2804551234"
}
```

Respuesta esperada: **201 Created** con el cliente creado.

---

### 3.3 Eliminar un cliente

```
DELETE http://localhost:8080/api/clientes/1
```

Headers:

```
Authorization: Bearer <token_admin>
```

Respuesta esperada: **200 OK** o **204 No Content**

---

## 4. Pruebas de seguridad

### 4.1 Request SIN token → 403

```
GET http://localhost:8080/api/productos
```

Sin header Authorization.

Respuesta esperada: **403 Forbidden**

```json
{
    "timestamp": "2026-04-29T...",
    "status": 403,
    "error": "Forbidden",
    "path": "/api/productos"
}
```

---

### 4.2 USER intenta crear producto → 403

Loguearse como `user`/`password` (request 1.2), copiar el token:

```
POST http://localhost:8080/api/productos
```

Headers:

```
Authorization: Bearer <token_user>
Content-Type: application/json
```

Body (raw → JSON):

```json
{
    "nombre": "Detergente",
    "precio": 900.0,
    "descripcion": "Detergente líquido 500ml",
    "url_foto": "https://ejemplo.com/detergente.jpg"
}
```

Respuesta esperada: **403 Forbidden** (USER no tiene permiso de crear)

---

### 4.3 USER intenta eliminar producto → 403

```
DELETE http://localhost:8080/api/productos/1
```

Headers:

```
Authorization: Bearer <token_user>
```

Respuesta esperada: **403 Forbidden**

---

### 4.4 USER puede listar productos → 200

```
GET http://localhost:8080/api/productos
```

Headers:

```
Authorization: Bearer <token_user>
```

Respuesta esperada: **200 OK** con la lista de productos.

---

### 4.5 Token inventado o expirado → 403

```
GET http://localhost:8080/api/productos
```

Headers:

```
Authorization: Bearer tokenInventado123456
```

Respuesta esperada: **403 Forbidden**

---

## 5. Resumen de permisos

| Endpoint                      | Método   | Sin token | USER   | ADMIN |
|-------------------------------|----------|-----------|--------|-------|
| `/auth/login`                 | POST     | ✅        | ✅     | ✅    |
| `/auth/register`              | POST     | ✅        | ✅     | ✅    |
| `/api/productos`              | GET      | ❌ 403    | ✅     | ✅    |
| `/api/productos`              | POST     | ❌ 403    | ❌ 403 | ✅    |
| `/api/productos/{id}`         | PUT      | ❌ 403    | ❌ 403 | ✅    |
| `/api/productos/{id}`         | DELETE   | ❌ 403    | ❌ 403 | ✅    |
| `/api/clientes`               | GET      | ❌ 403    | ✅     | ✅    |
| `/api/clientes`               | POST     | ❌ 403    | ❌ 403 | ✅    |
| `/api/clientes/{id}`          | DELETE   | ❌ 403    | ❌ 403 | ✅    |
