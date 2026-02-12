load("@rules_java//java:defs.bzl", "java_binary", "java_library", "java_test")

# 1. Compilar el código Java (LIBRERÍA)
java_library(
    name = "aplicacion_lib",
    srcs = ["com/miempresa/Main.java",
            "com/miempresa/Saludo.java"],
    deps = [
        "//utils:calculadora",
        "@maven//:com_google_code_gson_gson", #LIBRERÍA EXTERNA 
        ], 
    visibility = ["//visibility:public"],
)

# 2. Crear el ejecutable
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
    size = "small",
    deps = [
        ":aplicacion_lib",
        "@maven//:junit_junit",
    ],
)