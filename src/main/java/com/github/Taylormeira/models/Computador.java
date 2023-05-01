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
}
