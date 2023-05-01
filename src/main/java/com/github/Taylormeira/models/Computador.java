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
}
