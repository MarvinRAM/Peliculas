package com.example.peliculas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Modificar {

    @FXML private TextField idField;
    @FXML private TextField nombreField;
    @FXML private TextField generoField;
    @FXML private TextField anioField;
    @FXML private Button modificarButton;

    @FXML
    private void modificarPelicula() {
        String idText = idField.getText();
        String nombre = nombreField.getText();
        String genero = generoField.getText();
        String anioText = anioField.getText();

        if (idText.isEmpty() || nombre.isEmpty() || genero.isEmpty() || anioText.isEmpty()) {
            mostrarAlerta("Error", "Por favor completa todos los campos.");
            return;
        }

        int id, anio;
        try {
            id = Integer.parseInt(idText);
            anio = Integer.parseInt(anioText);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "ID y Año deben ser números enteros.");
            return;
        }

        String sql = "UPDATE PELICULAS SET NOMBRE = ?, GENERO = ?, ANIO = ? WHERE ID_PELICULA = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, genero);
            pstmt.setInt(3, anio);
            pstmt.setInt(4, id);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                mostrarAlerta("Éxito", "Película modificada correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("Aviso", "No se encontró ninguna película con ese ID.");
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

