package com.example.peliculas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Anio {

    @FXML
    private Button consultarButton;

    @FXML
    private TextArea anioTextArea;

    @FXML
    private void onHelloButtonClick() {
        // Mensaje mientras consulta
        anioTextArea.setText("Consultando anios...");

        ConexionBD.consultarAnio(mensaje -> {
            Platform.runLater(() -> {
                anioTextArea.setText(mensaje);
            });
        });
    }
}
