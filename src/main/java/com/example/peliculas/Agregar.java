package com.example.peliculas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Agregar {

    @FXML private TextField idField;
    @FXML private TextField nombreField;
    @FXML private TextField generoField;
    @FXML private TextField anioField;
    @FXML private Button guardarButton;

    @FXML
    private void guardarPelicula() {
        int id;
        int anio;

        try {
            id = Integer.parseInt(idField.getText());
            anio = Integer.parseInt(anioField.getText());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "ID y Año deben ser números enteros.");
            return;
        }

        String nombre = nombreField.getText();
        String genero = generoField.getText();

        if (nombre.isEmpty() || genero.isEmpty()) {
            mostrarAlerta("Error", "Por favor, completa todos los campos.");
            return;
        }

        String sql = "INSERT INTO PELICULAS (ID_PELICULA, NOMBRE, GENERO, ANIO) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, nombre);
            pstmt.setString(3, genero);
            pstmt.setInt(4, anio);

            int filasInsertadas = pstmt.executeUpdate();
            if (filasInsertadas > 0) {
                mostrarAlerta("Éxito", "Película agregada correctamente.");
                limpiarCampos();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error de BD", e.getMessage());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        idField.clear();
        nombreField.clear();
        generoField.clear();
        anioField.clear();
    }
}
