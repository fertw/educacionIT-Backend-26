# Clase 1 – Java Backend

## Etapa 1: Creación de una API para LimpiezaIT

## 🎯 Objetivo de la clase

Comprender qué es un servicio web y cómo se estructuran los datos que intercambia una API.
Iniciar el proyecto LimpiezaIT, creando la clase base `Producto` que representará los recursos de nuestra API REST.

## 🧠 Temas vistos en clase

- Introducción a los servicios web
- Qué es un servicio web
- Diferencias entre SOAP y REST
- Comunicación cliente-servidor con HTTP
- Concepto de API REST
- Métodos HTTP (`GET`, `POST`, `PUT`, `DELETE`)
- Recursos y URIs
- Códigos de estado HTTP
- Formatos de intercambio de datos
- JSON y XML: estructura, ventajas y diferencias
- Primer acercamiento a Spring Boot
- Estructura del proyecto
- Configuración inicial con Spring Initializr
- Creación de la clase `Producto`

## 🧱 Clase `Producto.java`

Archivo base del proyecto LimpiezaIT, que modela los productos que la empresa gestiona.

```java
package com.eit.limpiezait.model;

public class Producto {

    private Long id;
    private String nombre;
    private Double precio;
    private String descripcion;
    private String urlFoto;

    public Producto() {}

    public Producto(Long id, String nombre, Double precio, String descripcion, String urlFoto) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.urlFoto = urlFoto;
    }

    // Getters y Setters
    // ...

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\\'' +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\\'' +
                ", urlFoto='" + urlFoto + '\\'' +
                '}';
    }
}
```
