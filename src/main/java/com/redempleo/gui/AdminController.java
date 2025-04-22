package com.redempleo.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {

    @FXML
    private Button gestionarEmpresasButton;

    @FXML
    private Button gestionarCategoriasButton;

    @FXML
    private Button gestionarUsuariosButton;

    @FXML
    private Button gestionarAdministradoresButton;

    // Métodos que se llamarán al hacer clic en cada botón
    @FXML
    private void onGestionarEmpresas() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/redempleo/gui/EmpresasView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("GESTIÓN DE EMPRESAS");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onGestionarCategorias() {
        System.out.println("Gestionar Categorías pulsado");
    }

    @FXML
    private void onGestionarUsuarios() {
        System.out.println("Gestionar Usuarios pulsado");
    }

    @FXML
    private void onGestionarAdministradores() {
        System.out.println("Gestionar Administradores pulsado");
    }
}
