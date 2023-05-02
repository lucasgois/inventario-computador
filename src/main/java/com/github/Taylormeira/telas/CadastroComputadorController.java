package com.github.Taylormeira.telas;

import com.github.Taylormeira.models.Computador;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField tfMemoria;
    @FXML
    private TextField tfObs;
    @FXML
    private TextField tfHd;
    @FXML
    private TextField tfSetor;
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
        computador.setMemoria(tfMemoria.getText());
        computador.setObservacao(tfObs.getText());
        computador.setHd(tfHd.getText());
        computador.setSetor(tfSetor.getText());
        computador.setLocado(cbxAlocado.isSelected());

        Mensagem.sucesso("Computador salvo com sucesso!");

        botaoSair();
    }

    private void botaoSair() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public void carregar(Computador computador) {
        tfNome.setText(computador.getNome());
        tfProcessador.setText(computador.getProcessador());
        tfMemoria.setText(computador.getMemoria());
        tfObs.setText(computador.getObservacao());
        tfHd.setText(computador.getHd());
        tfSetor.setText(computador.getSetor());
        cbxAlocado.setSelected(computador.isLocado());
    }
}