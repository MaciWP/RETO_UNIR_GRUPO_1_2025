package com.redempleo.gui;

import com.redempleo.client.EmpresaAPI;
import com.redempleo.dto.empresa.EmpresaDTO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.List;

public class EditarEmpresaController {

    @FXML
    private ComboBox<EmpresaDTO> comboBoxEmpresas;

    @FXML
    private TextField textRazonSocial;

    @FXML
    private TextArea textDescripcion;

    @FXML
    private TextField textDireccionFiscal;

    @FXML
    private TextField textPais;

    @FXML
    private Button modificarEmpresaButton;

    @FXML
    private Button cancelarAltaEmpresaButton;

    @FXML
    public void initialize() {
        List<EmpresaDTO> lista = EmpresaAPI.getEmpresas();
        comboBoxEmpresas.getItems().addAll(lista);
    }

    // Metodo que se ejecuta al pulsar "Modificar"
    @FXML
    private void onModificarEmpresa() {
        if (textRazonSocial.getText().isBlank() ||
                textDescripcion.getText().isBlank() ||
                textDireccionFiscal.getText().isBlank() ||
                textPais.getText().isBlank()) {
            Alert alert = new Alert(AlertType.WARNING);
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
