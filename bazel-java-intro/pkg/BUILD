load("@rules_java//java:defs.bzl", "java_binary")

# 1. Compilar el código Java
java_library(
    name = "aplicacion_lib",
    # Ajustamos la ruta: de "java/com/..." a "com/..." porque ahora estamos directamente en pkg
    srcs = glob(["com/miempresa/Main.java"]),
    visibility = ["//visibility:public"],
)

# 2. Crear el ejecutable y dependencia de la biblioteca
java_binary(
    name = "mi_aplicacion",
    main_class = "com.miempresa.Main",
    runtime_deps = [
        ":aplicacion_lib",
    ],
)