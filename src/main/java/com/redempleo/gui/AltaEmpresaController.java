package com.redempleo.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.redempleo.dto.empresa.EmpresaDTO;
import com.redempleo.client.EmpresaAPI;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AltaEmpresaController {

    @FXML
    private TextField textRazonSocial;

    @FXML
    private TextArea textDescripcion;

    @FXML
    private TextField textDireccionFiscal;

    @FXML
    private TextField textPais;

    @FXML
    private Button guardarAltaEmpresaButton;

    @FXML
    private Button cancelarAltaEmpresaButton;

    // Metodo que se ejecuta al pulsar "Guardar"
    @FXML
    private void onGuardarEmpresa() {
        if (textRazonSocial.getText().isBlank() ||
                textDescripcion.getText().isBlank() ||
                textDireccionFiscal.getText().isBlank() ||
                textPais.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obligatorios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, rellena todos los campos.");
            alert.showAndWait();
            return;
        }

        String razonSocial = textRazonSocial.getText();
        String descripcion = textDescripcion.getText();
        String direccionFiscal = textDireccionFiscal.getText();
        String pais = textPais.getText();

        EmpresaDTO empresaDTO = new EmpresaDTO(
                razonSocial,
                descripcion,
                direccionFiscal,
                pais
        );

        boolean exito = EmpresaAPI.crearEmpresa(empresaDTO);

        if (exito) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Éxito");
            alert.setHeaderText(null);
            alert.setContentText("Empresa creada correctamente.");
            alert.showAndWait();

            limpiarCampos();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo crear la empresa.");
            alert.showAndWait();
        }
    }

    @FXML
    private void onCancelar() {
        Stage stage = (Stage) cancelarAltaEmpresaButton.getScene().getWindow();
        stage.close();
    }

    private void limpiarCampos() {
        textRazonSocial.clear();
        textDescripcion.clear();
        textDireccionFiscal.clear();
        textPais.clear();
    }
}
