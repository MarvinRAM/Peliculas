package com.example.peliculas;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Nombres {

    @FXML
    private Button consultarButton;

    @FXML
    private TextArea nombresTextArea;

    @FXML
    private void onHelloButtonClick() {
        nombresTextArea.setText("Consultando películas...");

        ConexionBD.consultarPeliculas(mensaje -> {
            Platform.runLater(() -> {
                nombresTextArea.setText(mensaje);
            });
        });
    }
}




