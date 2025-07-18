package com.example.peliculas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class Nombres {

    @FXML
    private Button consultarButton;

    @FXML
    private TextArea nombresTextArea;

    @FXML
    private void onHelloButtonClick() {
        // Mensaje mientras consulta
        nombresTextArea.setText("Consultando nombres...");

        ConexionBD.consultarNombres(mensaje -> {
            Platform.runLater(() -> {
                nombresTextArea.setText(mensaje);
            });
        });
    }
}



