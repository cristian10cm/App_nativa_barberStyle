# App Native Store

Aplicación móvil desarrollada en Kotlin con Jetpack Compose enfocada en la gestión de tiendas de belleza y usuarios.  
El proyecto maneja autenticación, navegación entre pantallas, administración de perfiles y gestión de tiendas.

---

# Tecnologías utilizadas

- Kotlin
- Jetpack Compose
- Navigation Compose
- Material 3
- State Management con remember y mutableStateOf

---

# Arquitectura del proyecto

El proyecto está organizado por dominios y responsabilidades para mantener una estructura limpia y escalable.



# Carpetas principales

## components

Contiene componentes reutilizables de la interfaz.

Ejemplos:
- ButtonComponent
- InputComponent
- SelectComp
- Header
- BarStore

---

## data

Contiene listas globales, objetos mock y almacenamiento temporal de información.

Ejemplos:
- UserList
- storeList
- UserInfo
- StoreInfo

---

## navigation

Contiene toda la navegación de la aplicación usando Navigation Compose.

Ejemplos:
- RouterStore
- AppRouter
- configuración de NavHost
- rutas de navegación

La aplicación utiliza Navigator mediante Navigation Compose para el manejo entre pantallas.

---

## screens

Contiene todas las pantallas principales de la aplicación.

Ejemplos:
- Login
- User
- Store
- admin
- register

---

## types

Contiene los modelos y tipos de datos de la aplicación.

Ejemplos:
- UsuarioType
- StoreType
- AppointmentType

---

## utils

Funciones auxiliares reutilizables y lógica compartida.

Ejemplos:
- validaciones
- helpers
- actualización de objetos
- manejo de fechas

---

# Características principales

- Registro de usuarios
- Inicio de sesión
- Validaciones de formularios
- Navegación entre pantallas
- Gestión de tiendas
- Edición de perfiles
- Gestión de servicios
- Manejo de estados con Compose
- Componentes reutilizables

---

# Manejo de estado

La aplicación utiliza:
- remember
- mutableStateOf
- mutableStateListOf

para el manejo reactivo de estados en Compose.

---

# Navegación

La navegación se realiza usando:

NavHost
NavController
composable()

Cada módulo maneja sus rutas mediante Navigation Compose.

---

# Estilo de desarrollo

El proyecto está desarrollado siguiendo:
- separación por responsabilidades
- reutilización de componentes
- organización modular
- arquitectura simple escalable
- manejo declarativo de UI con Compose

---



1. Abrir en Android Studio

2. Ejecutar el proyecto en un emulador o dispositivo físico


# Autor

Proyecto desarrollado por Cristhian Cruz.





