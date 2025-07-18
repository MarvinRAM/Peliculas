package com.example.peliculas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Id {

    @FXML
    private Button consultarButton;

    @FXML
    private TextArea idTextArea;

    @FXML
    private void onHelloButtonClick() {
        // Mensaje mientras consulta
        idTextArea.setText("Consultando ID...");

        ConexionBD.consultarID(mensaje -> {
            Platform.runLater(() -> {
                idTextArea.setText(mensaje);
            });
        });
    }
}
