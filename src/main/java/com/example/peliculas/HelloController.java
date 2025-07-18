package com.example.peliculas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    protected void onAgregar(ActionEvent event) {
        abrirVentana("Agregar.fxml", "Agregar", event);
    }

    @FXML
    protected void onName(ActionEvent event) {
        abrirVentana("nombres.fxml", "Nombres", event);
    }

    @FXML
    protected void onEliminar(ActionEvent event) {
        abrirVentana("Eliminar.fxml", "Género", event);
    }

    @FXML
    protected void onModificar(ActionEvent event) {
        abrirVentana("Modificar.fxml", "Año", event);
    }

    private void abrirVentana(String fxml, String titulo, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
