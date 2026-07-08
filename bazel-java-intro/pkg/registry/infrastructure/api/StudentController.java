package pkg.registry.infrastructure.api;

import static spark.Spark.*;
import pkg.registry.application.student.CreateStudent;
import pkg.registry.application.student.StudentRepositoryStub;
import com.google.gson.Gson;

public class StudentController {
    // Usamos el stub que ya funciona
    private static final StudentRepositoryStub repository = new StudentRepositoryStub();

    public static void main(String[] args) {
        port(4567); // El servidor web escuchará en este puerto

        // Configuración CORS (permite que tu HTML se comunique con este servidor)
        options("/*", (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "POST,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type");
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        // Definimos la ruta /register
        post("/register", (req, res) -> {
            Gson gson = new Gson();
            StudentData data = gson.fromJson(req.body(), StudentData.class);
            
            CreateStudent useCase = new CreateStudent(repository);
            useCase.execute(data.id, data.name);
            
            res.status(201);
            return "Estudiante " + data.name + " registrado!";
        });
    }

    static class StudentData { String id; String name; }
}
