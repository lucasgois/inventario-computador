package com.github.Taylormeira.telas;

import com.github.Taylormeira.exception.InventarioException;
import com.github.Taylormeira.models.Computador;
import com.github.Taylormeira.planilha.Planilha;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {
    private final Planilha planilha = new Planilha();
    @FXML
    private Button btnExportar;
    @FXML
    private Button btnAdicionar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnSair;
    @FXML
    private TableView<Computador> tbComputadores;
    @FXML
    private TableColumn<Computador, String> colAlocado;
    @FXML
    private TableColumn<Computador, String> colNome;
    @FXML
    private TableColumn<Computador, String> colMemoria;
    @FXML
    private TableColumn<Computador, String> colProcessador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExportar.setOnAction(event -> botaoExportar());
        btnAdicionar.setOnAction(event -> botaoAdicionar());
        btnAlterar.setOnAction(event -> botaoAlterar());
        btnExcluir.setOnAction(event -> botaoExcluir());
        btnSair.setOnAction(event -> botaoSair());

        colAlocado.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().isLocado() ? "SIM" : "NÃO"));
        colNome.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNome()));
        colMemoria.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getMemoria()));
        colProcessador.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProcessador()));

        carregarPlanilha();
    }

    private void carregarPlanilha() {
        try {
            tbComputadores.getItems().clear();
            tbComputadores.getItems().addAll(planilha.carregar());
        } catch (InventarioException e) {
            Mensagem.erro("Erro ao carregar planilha", e);
        }
    }

    private void botaoExportar() {
        planilha.salvar(tbComputadores.getItems());
        carregarPlanilha();
    }

    private void botaoAdicionar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/cadastro_computador.fxml"));

            Stage cadastro = new Stage();
            cadastro.setScene(new Scene(loader.load()));
            cadastro.setTitle("Cadastrar computador");
            cadastro.showAndWait();

            CadastroComputadorController cadastroComputadorController = loader.getController();

            Computador computador = cadastroComputadorController.getComputador();

            if (computador != null) {
                tbComputadores.getItems().add(computador);
            }

        } catch (IOException e) {
            Mensagem.erro("Erro ao cadastrar", e);
        }
    }

    private void botaoAlterar() {
        int index = tbComputadores.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/cadastro_computador.fxml"));

                Stage cadastro = new Stage();
                cadastro.setScene(new Scene(loader.load()));

                cadastro.setTitle("Alterar computador");

                CadastroComputadorController controller = loader.getController();
                controller.carregar(tbComputadores.getItems().get(index));

                cadastro.showAndWait();

                Computador computador = controller.getComputador();

                if (computador != null) {
                    tbComputadores.getItems().set(index, computador);
                }

            } catch (IOException e) {
                Mensagem.erro("Erro ao alterar", e);
            }
        }
    }

    private void botaoExcluir() {
        int index = tbComputadores.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            tbComputadores.getItems().remove(index);
        }
    }

    private void botaoSair() {
        Stage stage = (Stage) btnSair.getScene().getWindow();
        stage.close();
    }
}
