package com.example.peliculas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Genero {

    @FXML
    private Button consultarButton;

    @FXML
    private TextArea generosTextArea;

    @FXML
    private void onHelloButtonClick() {
        // Mensaje mientras consulta
        generosTextArea.setText("Consultando generos...");

        ConexionBD.consultarGenero(mensaje -> {
            Platform.runLater(() -> {
                generosTextArea.setText(mensaje);
            });
        });
    }
}
