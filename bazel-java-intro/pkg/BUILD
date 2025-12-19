load("@rules_java//java:defs.bzl", "java_binary")

# 1. Compilar el código Java (LIBRERÍA)
java_library(
    name = "aplicacion_lib",
    # Ajustamos la ruta:  "com/..." porque ahora estamos directamente en pkg
    srcs = ["com/miempresa/Main.java",
            "com/miempresa/Saludo.java"],
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

# 3. Configurar pruebas unitarias
java_test(
    name = "SaludoTest",
    srcs = ["com/miempresa/SaludoTest.java"],
    test_class = "com.miempresa.SaludoTest",
    deps = [
        ":aplicacion_lib",       # Tu código
        "@maven//:junit_junit",  # La librería JUnit 4
    ],
)