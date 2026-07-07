package com.masorange.registry.infrastructure.student;

import com.masorange.registry.application.student.StudentRepository;
import com.masorange.registry.domain.student.Student;
import com.masorange.registry.domain.student.StudentId;
import com.masorange.registry.domain.student.StudentName;
import com.masorange.registry.domain.student.Email;

import java.sql.*;
import java.util.Optional;

public final class SqliteStudentRepository implements StudentRepository {

    // El fichero 'registry.db' se creará automáticamente en la raíz de tu proyecto
    private final String connectionUrl = "jdbc:sqlite:registry.db";

    public SqliteStudentRepository() {
        // Asegura que la tabla exista en la base de datos al arrancar el componente
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (" +
                         "id TEXT PRIMARY KEY, " +
                         "name TEXT NOT NULL, " +
                         "email TEXT NOT NULL)");
        } catch (SQLException e) {
            throw new RuntimeException("Error crítico al inicializar la base de datos SQLite", e);
        }
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT OR REPLACE INTO students(id, name, email) VALUES(?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Usamos las lecturas de métodos puros de tu dominio (.id(), .name(), .email())
            pstmt.setString(1, student.id().value());
            pstmt.setString(2, student.name().value());
            pstmt.setString(3, student.email().value());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el estudiante en SQLite", e);
        }
    }

    @Override
    public Optional<Student> findById(StudentId id) {
        String sql = "SELECT id, name, email FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, id.value());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    StudentId studentId = StudentId.of(rs.getString("id"));
                    StudentName studentName = StudentName.of(rs.getString("name"));
                    // ¡Volvemos a usar NEW como exige tu dominio original!
                    Email email = new Email(rs.getString("email"));
                    
                    return Optional.of(new Student(studentId, studentName, email));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el estudiante en SQLite", e);
        }
        return Optional.empty();
    }
}