# Clase 5 – Java Backend

## Etapa 5: Autenticación con JWT (JSON Web Token) en Spring Boot

---

### 🎯 Objetivo de la clase

Comprender el funcionamiento del **JWT (JSON Web Token)** como mecanismo de autenticación **stateless** en una API REST con **Spring Boot**, e implementarlo en el proyecto **LimpiezaIT**:

- Emitir un token al autenticarse (`/auth/login`)
- Validar el token en cada request mediante un **filtro JWT**
- Restringir endpoints según **roles**
- Aplicar **`@PreAuthorize`** para control de acceso a nivel de método

---

## 🧠 Teoría y flujo completo

### ¿Qué es JWT?

Un **JSON Web Token (JWT)** es un **token firmado** (no cifrado) que el servidor genera cuando un usuario se autentica correctamente.

- El cliente lo incluye en cada request HTTP mediante el header:
- El servidor **verifica la firma** del token y **no mantiene sesiones** en memoria (arquitectura _stateless_).

#### Estructura de un JWT

- **Header:** indica algoritmo y tipo (`alg`, `typ`)
- **Payload:** contiene los _claims_ (sub, roles, exp, etc.)
- **Signature:** es la firma creada con el algoritmo (por ejemplo HS256) y la _secret key_
  aaa
