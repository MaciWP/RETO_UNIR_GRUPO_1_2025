package com.redempleo.gui;

import com.redempleo.client.EmpresaAPI;
import com.redempleo.dto.empresa.EmpresaDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javafx.scene.control.Button;

import java.util.List;

public class ListaEmpresasController {

    @FXML private TableView<EmpresaDTO> tableViewListaEmpresas;
    @FXML private TableColumn<EmpresaDTO, String> colRazonSocial;
    @FXML private TableColumn<EmpresaDTO, String> colDescripcion;
    @FXML private TableColumn<EmpresaDTO, String> colDireccionFiscal;
    @FXML private TableColumn<EmpresaDTO, String> colPais;
    @FXML private Button buttonVolver;

    @FXML
    public void initialize() {
        // Configurar columnas
        colRazonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colDireccionFiscal.setCellValueFactory(new PropertyValueFactory<>("direccionFiscal"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));

        // Ajustar columnas automáticamente
        tableViewListaEmpresas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Cargar datos
        List<EmpresaDTO> lista = EmpresaAPI.getEmpresas();
        ObservableList<EmpresaDTO> datos = FXCollections.observableArrayList(lista);
        tableViewListaEmpresas.setItems(datos);
    }

    @FXML
    private void onVolver() {
        Stage stage = (Stage) buttonVolver.getScene().getWindow();
        stage.close();
    }
}