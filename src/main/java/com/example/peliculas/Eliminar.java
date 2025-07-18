package com.example.peliculas;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Eliminar {

    @FXML
    private TextField idEliminarField;

    @FXML
    private Button eliminarButton;

    @FXML
    private void eliminarPelicula() {
        String idTexto = idEliminarField.getText();

        if (idTexto.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingresa el ID de la película.");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número entero.");
            return;
        }

        String sql = "DELETE FROM PELICULAS WHERE ID_PELICULA = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                mostrarAlerta("Éxito", "Película eliminada correctamente.");
                idEliminarField.clear();
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
}

