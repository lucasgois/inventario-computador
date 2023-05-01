package com.github.Taylormeira.telas;

import com.github.Taylormeira.models.Computador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;

import java.net.URL;
import java.util.ResourceBundle;

public class CadastroComputadorController implements Initializable {
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSalvar;
    @FXML
    private CheckBox cbxAlocado;
    @FXML
    private TextField tfNome;
    @FXML
    private TextField tfProcessador;
    @Getter
    private Computador computador = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSalvar.setOnAction(event -> botaoSalvar());
        btnCancelar.setOnAction(event -> botaoSair());
    }

    private void botaoSalvar() {
        computador = new Computador();

        computador.setNome(tfNome.getText());
        computador.setProcessador(tfProcessador.getText());
        computador.setLocado(cbxAlocado.isSelected());

        Alert alert = new Alert(Alert.AlertType.INFORMATION, computador.toString(), ButtonType.YES);
        alert.setTitle("Aviso");
        alert.setHeaderText("Computador salvo com sucesso!");
        alert.showAndWait();

//        if (alert.getResult() == ButtonType.YES) {
//
//        }
        botaoSair();
    }

    private void botaoSair() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void carregar(Computador computador) {
        tfNome.setText(computador.getNome());
        tfProcessador.setText(computador.getProcessador());
        cbxAlocado.setSelected(computador.isLocado());
    }
}