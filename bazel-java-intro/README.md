# Registry, Grading & Booking — Proyecto de entrenamiento con Bazel y Java

Este repositorio contiene **3 aplicaciones Java independientes** (desplegables) construidas con **Bazel** como sistema de build. El proyecto simula un sistema escolar con tres contextos de negocio separados, cada uno con su propia pila de dependencias Maven.

El objetivo principal es servir como **campo de entrenamiento** para practicar:

- Arquitectura hexagonal en Java
- Gestión de dependencias con Bazel (bzlmod)
- Detección y actualización de CVEs en librerías
- Resolución de conflictos transitivos en Maven
- Diseño dirigido por dominio (DDD táctico): value objects, agregados, repositorios

---

## Tabla de contenidos

1. [Requisitos previos](#requisitos-previos)
2. [Inicio rápido](#inicio-rápido)
3. [Estructura del proyecto](#estructura-del-proyecto)
4. [Los 3 desplegables](#los-3-desplegables)
5. [Navegación paso a paso](#navegación-paso-a-paso)
6. [Arquitectura y patrones](#arquitectura-y-patrones)
7. [Gestión de dependencias Maven](#gestión-de-dependencias-maven)
8. [Vulnerabilidades intencionadas (CVEs)](#vulnerabilidades-intencionadas-cves)
9. [Ejercicios propuestos](#ejercicios-propuestos)
10. [Referencia Bazel](#referencia-bazel)

---

## Requisitos previos

- **Java 21** o superior
- **Bazel 7.x** (con bzlmod habilitado — es el modo por defecto)
- Un terminal con acceso a `bazel` en el PATH

No hace falta instalar Maven ni Gradle. Bazel descarga las dependencias automáticamente.

---

## Inicio rápido

```bash
# Clonar y entrar al directorio del workspace de Bazel
cd bazel-java-intro

# Compilar todo el proyecto
bazel build //...

# Ejecutar todos los tests
bazel test //...

# Ejecutar cada aplicación
bazel run //pkg/registry/app:main
bazel run //pkg/grading/app:main
bazel run //pkg/booking/app:main
```

---

## Estructura del proyecto

```
bazel-java-intro/
├── MODULE.bazel                              # Raíz del workspace Bazel
├── jvm-deps/
│   ├── BUILD.bazel                           # Targets agrupados de dependencias Maven
│   └── maven.MODULE.bazel                    # Declaración de artefactos Maven
│
└── pkg/
    ├── registry/                             # DESPLEGABLE 1 — Matriculación
    │   ├── domain/
    │   │   ├── student/                      # StudentId (target :id) + Student, StudentName (target :domain)
    │   │   ├── teacher/                      # TeacherId (target :id) + Teacher, TeacherName, SubjectName, TeachingAssignment (target :domain)
    │   │   └── classgroup/                   # ClassGroup, ClassGroupId, ClassGroupName (target :domain)
    │   ├── application/
    │   │   ├── student/                      # StudentRepository, CreateStudent
    │   │   ├── teacher/                      # TeacherRepository, TeachingAssignmentRepository, CreateTeacher, AssignSubject
    │   │   └── classgroup/                   # ClassGroupRepository, CreateClassGroup, AddStudentToClassGroup
    │   ├── infrastructure/
    │   │   ├── student/                      # InMemoryStudentRepository
    │   │   ├── teacher/                      # InMemoryTeacherRepository, InMemoryTeachingAssignmentRepository
    │   │   └── classgroup/                   # InMemoryClassGroupRepository
    │   └── app/                              # Main.java (java_binary)
    │
    ├── grading/                              # DESPLEGABLE 2 — Calificaciones
    │   ├── domain/                           # Grade, Subject, StudentGrade
    │   ├── application/                      # GradeRepository, RecordGrade, CalculateAverage
    │   ├── infrastructure/                   # InMemoryGradeRepository
    │   └── app/                              # Main.java (java_binary)
    │
    └── booking/                              # DESPLEGABLE 3 — Reserva de salas
        ├── domain/                           # Room, RoomId, RoomName, Capacity
        ├── application/                      # RoomRepository, CreateRoom
        ├── infrastructure/                   # InMemoryRoomRepository
        └── app/                              # Main.java (java_binary)
```

---

## Los 3 desplegables

Cada desplegable es una aplicación Java independiente con su propio `Main.java` y su propio conjunto de librerías externas.

### Registry (Matriculación)

Gestiona el registro de estudiantes, profesores, asignaturas y grupos de clase.

| Aspecto | Detalle |
|---|---|
| **Main** | `//pkg/registry/app:main` |
| **Librerías** | Jackson (JSON) + SLF4J/Logback (logging) |
| **Entidades** | Student, Teacher, TeachingAssignment, ClassGroup |
| **Qué hace** | Crea un profesor, le asigna asignaturas, crea un estudiante, lo añade a un grupo y serializa el resultado a JSON con logging |

### Grading (Calificaciones)

Gestiona las notas de los estudiantes por asignatura.

| Aspecto | Detalle |
|---|---|
| **Main** | `//pkg/grading/app:main` |
| **Librerías** | Gson (JSON) + Commons Text (plantillas de texto) |
| **Entidades** | Grade, Subject, StudentGrade |
| **Qué hace** | Registra notas en varias asignaturas, calcula la media y genera un informe usando `StringSubstitutor` |

### Booking (Reserva de salas)

Gestiona la creación y listado de aulas y laboratorios.

| Aspecto | Detalle |
|---|---|
| **Main** | `//pkg/booking/app:main` |
| **Librerías** | Jackson (JSON) + Guava (colecciones inmutables) |
| **Entidades** | Room, RoomId, RoomName, Capacity |
| **Qué hace** | Crea varias salas con capacidad y las serializa a JSON usando `ImmutableList` de Guava |

### Grafo de dependencias entre desplegables

```
registry/domain/student:id  ◄── grading/domain
registry/domain/teacher:id  ◄── grading/domain

booking (totalmente independiente)
```

Los desplegables **nunca dependen del dominio completo** de otro. Solo de los módulos de identidad (`:id`), que contienen exclusivamente el value object del identificador (ej. `StudentId`). Esto permite que cada desplegable evolucione de forma independiente.

---

## Navegación paso a paso

### Paso 1 — Entender el workspace de Bazel

Empieza por el fichero raíz:

```
MODULE.bazel
```

Este fichero declara el nombre del módulo y las dependencias externas de Bazel (`rules_java`, `rules_jvm_external`). La declaración de artefactos Maven está separada en un fichero incluido:

```python
include("//jvm-deps:maven.MODULE.bazel")
```

### Paso 2 — Ver las dependencias Maven

Abre `jvm-deps/maven.MODULE.bazel`. Aquí están **todos** los artefactos Maven del proyecto con sus versiones. Observa que algunas versiones son deliberadamente antiguas (tienen CVEs conocidas — ver la [sección de vulnerabilidades](#vulnerabilidades-intencionadas-cves)).

Después mira `jvm-deps/BUILD.bazel`. Este fichero agrupa los artefactos Maven en **targets con nombre** para que los módulos no referencien directamente `@maven//...`:

```python
# En vez de escribir esto en cada BUILD:
"@maven//:com_fasterxml_jackson_core_jackson_databind"
"@maven//:com_fasterxml_jackson_core_jackson_core"
"@maven//:com_fasterxml_jackson_core_jackson_annotations"

# Los módulos simplemente escriben:
"//jvm-deps:jackson"
```

### Paso 3 — Explorar un dominio (empezar por booking, el más simple)

El dominio de booking es el más sencillo y no tiene dependencias a otros dominios. Es el mejor punto de partida.

1. **Value objects**: Abre `pkg/booking/domain/com/masorange/booking/domain/`. Verás 4 clases:
   - `RoomId.java` — Identificador basado en UUID
   - `RoomName.java` — Nombre con validación (not null, not empty, trim)
   - `Capacity.java` — Entero positivo con validación
   - `Room.java` — Agregado que combina los tres anteriores

2. **Tests**: Cada value object tiene su test. Observa el patrón:
   - Test de creación válida
   - Test de validación (null, empty, valores inválidos)
   - Test de igualdad (`equals`/`hashCode`)

3. **BUILD.bazel**: Mira cómo el fichero `pkg/booking/domain/BUILD.bazel` declara un target `:domain` y varios `java_test`.

### Paso 4 — Subir a la capa de aplicación

Abre `pkg/booking/application/`:

1. **Interfaz del repositorio**: `RoomRepository.java` — define `save()` y `findById()`. Es una interfaz, no una implementación.
2. **Caso de uso**: `CreateRoom.java` — recibe strings/primitivos, crea value objects, construye el agregado y lo persiste.
3. **Test**: `CreateRoomTest.java` — usa la implementación in-memory directamente (sin mocks).

### Paso 5 — Bajar a la infraestructura

Abre `pkg/booking/infrastructure/`:

1. **InMemoryRoomRepository.java** — Implementa `RoomRepository` con un `HashMap`. Es la implementación concreta de la interfaz definida en application.

Observa la **dirección de las dependencias**:
```
domain ← application ← infrastructure
```
El dominio no conoce ni a application ni a infrastructure. Application define interfaces. Infrastructure las implementa.

### Paso 6 — Ver el punto de entrada

Abre `pkg/booking/app/com/masorange/booking/app/Main.java`:

1. Instancia la implementación concreta (`InMemoryRoomRepository`)
2. Crea el caso de uso (`CreateRoom`)
3. Ejecuta operaciones de negocio
4. Serializa con Jackson + Guava

### Paso 7 — Explorar registry (el más complejo)

Registry tiene una particularidad: los targets de identidad están **extraídos** en targets Bazel separados.

En `pkg/registry/domain/student/BUILD.bazel` verás **dos** targets de librería:

```python
java_library(name = "id", ...)      # Solo StudentId.java
java_library(name = "domain", ...)  # Student.java, StudentName.java — depende de :id
```

Esto permite que `grading` dependa solo de `//pkg/registry/domain/student:id` sin arrastrar todo el dominio de student.

### Paso 8 — Seguir el grafo de dependencias

Ejecuta este comando para ver todos los targets del proyecto:

```bash
bazel query //pkg/...
```

Para ver las dependencias de un target específico:

```bash
bazel query "deps(//pkg/registry/app:main)" --output graph
```

---

## Arquitectura y patrones

### Arquitectura hexagonal

Cada desplegable sigue la arquitectura hexagonal (ports & adapters):

```
┌─────────────────────────────────────────────────┐
│                    app/                          │
│              (Main.java — composición)           │
├─────────────────────────────────────────────────┤
│              infrastructure/                     │
│         (InMemory*Repository — adapters)         │
├─────────────────────────────────────────────────┤
│              application/                        │
│     (Use cases + Repository interfaces — ports)  │
├─────────────────────────────────────────────────┤
│                domain/                           │
│    (Value objects, agregados — lógica de negocio)│
└─────────────────────────────────────────────────┘
```

**Regla de dependencia**: las flechas solo apuntan hacia adentro. El dominio no depende de nada. Application depende del dominio. Infrastructure depende de application y dominio. App los conecta todos.

### Patrones aplicados

| Patrón | Dónde se ve | Ejemplo |
|---|---|---|
| **Value Object** | Todos los `*Id`, `*Name`, `Grade`, `Capacity`, `Subject`, `SubjectName` | `StudentId.of("uuid-string")` |
| **Agregado** | `ClassGroup`, `Room`, `Student`, `Teacher` | `ClassGroup.addStudent(id)` devuelve nueva instancia |
| **Factory Method** | Todas las clases de dominio | `Student.create(id, name)` — constructor privado |
| **Repository** | Interfaces en application, implementaciones en infrastructure | `StudentRepository` / `InMemoryStudentRepository` |
| **Use Case** | Clases en application | `CreateStudent`, `RecordGrade`, `AddStudentToClassGroup` |
| **Inmutabilidad** | Todos los objetos de dominio | `Collections.unmodifiableList`, copias defensivas |
| **Fail Fast** | Constructores de value objects | `Objects.requireNonNull`, validación en el constructor |

---

## Gestión de dependencias Maven

### Cómo funciona en Bazel

1. **`MODULE.bazel`** declara las dependencias de Bazel (`rules_java`, `rules_jvm_external`) e incluye la configuración Maven.

2. **`jvm-deps/maven.MODULE.bazel`** contiene `maven.install()` con la lista de artefactos. Bazel descarga estos JARs automáticamente al compilar.

3. **`jvm-deps/BUILD.bazel`** agrupa los artefactos en targets lógicos. Esto centraliza la gestión de versiones y facilita los cambios.

### Qué usa cada desplegable

| Desplegable | Target | Librerías | Para qué |
|---|---|---|---|
| **registry** | `//jvm-deps:jackson` | jackson-databind, jackson-core, jackson-annotations | Serialización JSON con `ObjectMapper` |
| **registry** | `//jvm-deps:logging` | slf4j-api, logback-classic | Logging estructurado |
| **grading** | `//jvm-deps:gson` | gson | Serialización JSON con `Gson` |
| **grading** | `//jvm-deps:commons-text` | commons-text | Plantillas con `StringSubstitutor` |
| **booking** | `//jvm-deps:jackson` | jackson-databind, jackson-core, jackson-annotations | Serialización JSON con `ObjectMapper` |
| **booking** | `//jvm-deps:guava` | guava | `ImmutableList` para colecciones inmutables |
| **todos** | `//jvm-deps:junit` | junit 4.13.2 | Tests unitarios |

---

## Vulnerabilidades intencionadas (CVEs)

Las versiones de las librerías se han elegido **a propósito** con CVEs conocidas para practicar la detección y actualización de dependencias.

### Tabla de vulnerabilidades

| Librería | Versión actual | CVE | Severidad | Descripción | Versión fix |
|---|---|---|---|---|---|
| `jackson-databind` | 2.13.4 | [CVE-2022-42003](https://nvd.nist.gov/vuln/detail/CVE-2022-42003) | Alta (7.5) | Deserialización con `UNWRAP_SINGLE_VALUE_ARRAYS` permite DoS por consumo de recursos | 2.13.4.2+ |
| `logback-classic` | 1.2.11 | [CVE-2023-6378](https://nvd.nist.gov/vuln/detail/CVE-2023-6378) | Alta (7.5) | DoS por consumo excesivo de memoria al recibir ciertos patrones de serialización | 1.2.13+ |
| `commons-text` | 1.9 | [CVE-2022-42889](https://nvd.nist.gov/vuln/detail/CVE-2022-42889) | Critica (9.8) | "Text4Shell" — ejecución remota de código a través de `StringSubstitutor` con interpoladores como `${script:javascript:...}` | 1.10.0+ |

### Conflictos transitivos interesantes

Actualizar estas librerías no es trivial. Hay trampas:

| Escenario | Problema | Qué practicar |
|---|---|---|
| **Logback 1.2 a 1.4+** | Logback 1.4 requiere **SLF4J 2.x** (breaking change en la API). No puedes actualizar logback sin actualizar también slf4j. | Gestión de upgrades coordinados |
| **Jackson parcial** | Si actualizas `jackson-databind` a 2.15 pero dejas `jackson-core` en 2.13, obtienes `NoSuchMethodError` en runtime. Las tres librerías deben ir sincronizadas. | Alineación de versiones en familias de librerías |
| **Guava transitiva** | Muchas librerías traen Guava como dependencia transitiva a versiones distintas. Actualizar Guava puede romper la resolución. | Resolución de conflictos de versiones |

### Cómo practicar

1. **Detectar**: Usa herramientas como `bazel query` o inspecciona manualmente `maven.MODULE.bazel`
2. **Investigar**: Busca cada CVE en [NVD](https://nvd.nist.gov/) o [GitHub Advisory](https://github.com/advisories)
3. **Planificar**: Identifica qué otras librerías se ven afectadas (dependencias transitivas)
4. **Actualizar**: Cambia las versiones en `jvm-deps/maven.MODULE.bazel`
5. **Verificar**: `bazel build //...` y `bazel test //...` deben seguir pasando
6. **Documentar**: Justifica por qué se eligió cada versión nueva

---

## Ejercicios propuestos

### Nivel 1 — Familiarización

#### 1.1 Ejecutar y observar (HECHO)
Ejecuta los tres `Main.java` y analiza la salida. Identifica qué librería produce cada formato de JSON (Jackson vs Gson).

#### 1.2 Romper y reparar un test
Modifica la validación de `Capacity` para que acepte 0 y observa qué test falla. Repáralo.

#### 1.3 Añadir un value object
Crea `Email` como value object en `registry/domain/student` con validación de formato. Añádelo como campo opcional a `Student`.

---

### Nivel 2 — Dependencias y CVEs

#### 2.1 Corregir la CVE de Commons Text
Actualiza `commons-text` de 1.9 a 1.10.0+ en `maven.MODULE.bazel`. Verifica que grading sigue compilando y sus tests pasan.

#### 2.2 Corregir la CVE de Jackson
Actualiza las 3 librerías de Jackson a 2.15.x o superior. Verifica que registry y booking siguen funcionando. Observa que las tres deben ir sincronizadas.

#### 2.3 Corregir la CVE de Logback (la trampa)
Intenta actualizar logback de 1.2.11 a 1.4.x. Observa el error de compilación. Investiga por qué y actualiza también SLF4J a 2.x.

#### 2.4 Auditoría completa
Corrige las tres CVEs en un solo cambio. Ejecuta `bazel build //...` y `bazel test //...` tras cada paso.

---

### Nivel 3 — Nuevas funcionalidades

#### 3.1 Añadir listado de estudiantes por grupo
Crea un caso de uso `ListStudentsByClassGroup` en `registry/application/classgroup` que dado un `ClassGroupId` devuelva la lista de `StudentId`. Añade el test correspondiente.

#### 3.2 Añadir nota máxima y mínima
Crea un caso de uso `CalculateExtremes` en `grading/application` que dado un `StudentId` devuelva la nota más alta y la más baja. Piensa en cómo modelar la respuesta (value object? record?).

#### 3.3 Añadir reserva de salas con horario
Extiende booking con el concepto de `TimeSlot` (día + hora inicio + hora fin) y `Booking` (Room + TimeSlot). Implementa las tres capas (domain, application, infrastructure). El dominio debe validar que no haya solapamientos.

#### 3.4 Crear un cuarto desplegable: reporting
Crea un nuevo desplegable `//pkg/reporting/app:main` que dependa de `registry/domain/student:id` y `grading/domain` para generar un informe de notas. Usa una librería nueva (ej. Apache POI para Excel) añadiéndola a `jvm-deps/`.

---

### Nivel 4 — Arquitectura y Bazel avanzado

#### 4.1 Extraer un módulo compartido
`StudentId` y `TeacherId` siguen el mismo patrón (UUID, generate, of, value). Extrae una clase abstracta o genérica `EntityId` en un módulo compartido `//pkg/shared/domain`. Analiza el impacto en el grafo de dependencias.

#### 4.2 Reemplazar InMemory por persistencia real
Sustituye `InMemoryStudentRepository` por una implementación con SQLite o H2. Añade las dependencias Maven necesarias en `jvm-deps/`. Observa que solo cambia la capa de infrastructure — domain y application no se tocan.

#### 4.3 Añadir tests de integración
Crea una carpeta de tests de integración separada que pruebe los repositorios con persistencia real. Configura los `java_test` en Bazel con `size = "medium"` o `"large"`.

#### 4.4 Analizar el grafo de dependencias con Bazel
Usa estos comandos para explorar:

```bash
# Ver todos los targets del proyecto
bazel query //pkg/...

# Ver las dependencias de registry/app
bazel query "deps(//pkg/registry/app:main)" --noimplicit_deps

# Ver qué depende de StudentId
bazel query "rdeps(//pkg/..., //pkg/registry/domain/student:id)"

# Generar un grafo visual (necesita Graphviz)
bazel query "deps(//pkg/registry/app:main)" --noimplicit_deps --output graph | dot -Tpng > graph.png
```

#### 4.5 Separar tests en targets independientes
Actualmente los tests de application usan implementaciones de infrastructure (InMemory). Evalúa si esto es un code smell o una decisión pragmática. Argumenta tu postura.

---

### Nivel 5 — Retos avanzados

#### 5.1 Migrar de JUnit 4 a JUnit 5
Cambia la dependencia de `junit:junit:4.13.2` a JUnit 5 (`org.junit.jupiter:junit-jupiter`). Actualiza todos los tests (anotaciones, asserts, expected exceptions). Configura los `java_test` de Bazel para JUnit 5.

#### 5.2 Añadir un API REST
Usa una librería ligera como Javalin o Spark para exponer un API HTTP en uno de los desplegables. Añade endpoints GET/POST. Configura la dependencia Maven en `jvm-deps/`.

#### 5.3 Implementar eventos de dominio
Cuando se crea un estudiante, publica un evento `StudentCreated`. Implementa un bus de eventos simple (in-memory) y un suscriptor que loguee el evento. Piensa en qué capa vive cada pieza.

#### 5.4 Añadir CI con GitHub Actions
Crea un workflow que ejecute `bazel test //...` en cada push. Investiga cómo cachear las descargas de Bazel entre ejecuciones.

---

## Referencia Bazel

### Comandos esenciales

```bash
# Compilar todo
bazel build //...

# Compilar un target específico
bazel build //pkg/registry/domain/student:domain

# Ejecutar todos los tests
bazel test //...

# Ejecutar tests de un paquete
bazel test //pkg/registry/domain/...

# Ejecutar un test específico
bazel test //pkg/registry/domain/student:StudentIdTest

# Ejecutar una aplicación
bazel run //pkg/registry/app:main

# Limpiar la caché de build
bazel clean

# Limpiar todo incluyendo dependencias descargadas
bazel clean --expunge
```

### Convenciones de naming en targets

| Capa | Target | Referencia completa |
|---|---|---|
| Domain | `:domain` | `//pkg/registry/domain/student:domain` |
| Domain (identidad) | `:id` | `//pkg/registry/domain/student:id` |
| Application | `:application` | `//pkg/registry/application/student:application` |
| Infrastructure | `:infrastructure` | `//pkg/registry/infrastructure/student:infrastructure` |
| App | `:main` | `//pkg/registry/app:main` |
| Dependencia Maven | `:nombre` | `//jvm-deps:jackson` |

### Anatomía de un BUILD.bazel

```python
load("@rules_java//java:defs.bzl", "java_library", "java_test")

# Librería de producción
java_library(
    name = "domain",                                    # Nombre del target
    srcs = glob(["com/masorange/.../*.java"],            # Ficheros fuente
                exclude = ["com/masorange/.../*Test.java"]),  # Excluir tests
    visibility = ["//visibility:public"],               # Visible para otros paquetes
    deps = ["//pkg/registry/domain/student:id"],        # Dependencias
)

# Test unitario
java_test(
    name = "StudentIdTest",
    srcs = ["com/.../StudentIdTest.java"],
    test_class = "com.masorange.registry.domain.student.StudentIdTest",
    size = "small",                                     # small = timeout corto
    deps = [":domain", "//jvm-deps:junit"],
)
```
