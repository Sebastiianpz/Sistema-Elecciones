# 🗳️ Sistema de Elecciones

Proyecto desarrollado para la materia **Programación Sobre Redes**, cuyo objetivo es simular un sistema completo de gestión electoral mediante una arquitectura dividida en dos módulos principales.

## 📋 Descripción

El sistema se encuentra organizado en dos proyectos independientes que comparten la misma base de datos:

- **Padrón Electoral** (Grupo 1)
- **Mesas Electorales** (Grupo 2)

Cada módulo fue desarrollado en una rama independiente y posteriormente integrado en este repositorio.

---

# 📂 Organización del repositorio

| Rama | Descripción |
|------|-------------|
| `main` | Versión final del proyecto. |
| `develop` | Rama de integración de ambos proyectos. |
| `padron` | Desarrollo del módulo Padrón Electoral. |
| `mesas` | Desarrollo del módulo Mesas Electorales. |

---

# 👥 Integrantes

## Grupo 1 – Padrón Electoral

- Sebastián Dávalos
- Maximiliano Occhiuzzi

### Funcionalidades

- Inicio de sesión de administrador.
- Alta de ciudadanos.
- Modificación de datos.
- Eliminación de registros.
- Búsqueda por DNI.
- Habilitación e inhabilitación para votar.
- Carga y visualización de imagen del DNI.
- Consultas mediante AJAX.
- Dockerización del proyecto.
- Publicación de imagen en Docker Hub.

---

## Grupo 2 – Mesas Electorales

- Maira Equice
- Camila Landa
- Sabrina Alanoca
- Solange Huallpa

### Funcionalidades

- Administración de mesas electorales.
- Gestión de votantes habilitados.
- Consulta de información del padrón.
- Operaciones sobre la misma base de datos compartida.

---

# 🛠️ Tecnologías utilizadas

- Java 17
- JSP
- Servlets
- Maven
- MySQL
- Docker
- Docker Compose
- Bootstrap 5
- AJAX
- jQuery
- Git
- GitHub

---

# 🗄️ Base de datos

Durante el desarrollo se trabajó con dos modalidades:

- Base de datos MySQL mediante Docker.
- Base de datos MySQL institucional para las pruebas de integración.

Ambos proyectos consumen la misma estructura de datos para garantizar la interoperabilidad entre módulos.

---

# 🚀 Ejecución

Clonar el repositorio:

```bash
git clone https://github.com/<usuario>/<repositorio>.git
```

Ingresar al proyecto:

```bash
cd Sistema-Elecciones
```

Levantar los servicios:

```bash
docker compose up -d
```

---

# 📦 Docker Hub

La imagen del módulo Padrón se encuentra publicada en Docker Hub.

```text
sebastianpzz/sistema-de-elecciones
```

---

# 📄 Licencia

Proyecto desarrollado con fines exclusivamente educativos para la materia Laboratorio de Programación.
