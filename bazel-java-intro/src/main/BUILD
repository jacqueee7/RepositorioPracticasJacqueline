load("@rules_java//java:defs.bzl", "java_binary")

# 1. Compilar el código Java

java_library(
    name = "aplicacion_lib",
    srcs = glob(["java/com/miempresa/Main.java"]),
    visibility = ["//visibility:public"],
)

# 2. Crear el ejecutable y dependencia de la biblioteca
java_binary(
    name = "mi_aplicacion",
    main_class = "com.miempresa.Main",
    #dependedencia necesarioa para que la regla compile los srcs
    runtime_deps = [
        ":aplicacion_lib",
    ],
)