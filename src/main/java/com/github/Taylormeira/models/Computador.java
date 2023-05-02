package com.github.Taylormeira.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Computador {
    private String nome;
    private String processador;
    private String memoria;
    private String observacao;
    private String hd;
    private String setor;
    private boolean locado;

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public void setProcessador(String processador) {
        this.processador = processador.toUpperCase();
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria.toUpperCase();
    }
    public void setObservacao(String observacao) {this.observacao = observacao.toUpperCase();}
    public void setHd(String hd) {this.hd = hd.toUpperCase();}
    public void setSetor(String setor) {this.setor = setor.toUpperCase();}
}
