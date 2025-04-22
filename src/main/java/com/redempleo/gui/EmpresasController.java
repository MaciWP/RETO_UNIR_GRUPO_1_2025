package com.redempleo.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmpresasController {

    @FXML
    private Button altaEmpresaButton;

    @FXML
    private Button editarEmpresaButton;

    @FXML
    private Button verEmpresaButton;

    @FXML
    private Button eliminarEmpresaButton;


    @FXML
    private void onDarAltaEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/redempleo/gui/AltaEmpresaView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("ALTA EMPRESA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onEditarEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/redempleo/gui/EditarEmpresaView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("EDITAR EMPRESA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }    }

    @FXML
    private void onVerEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/redempleo/gui/ListaEmpresasView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("LISTA DE EMPRESAS");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onEliminarEmpresa() {
        System.out.println("Eliminar empresa pulsado");
    }

}
