package com.hiveplace.desafio.enums;

public enum Prioridade {

    DEFAULT(0, "Default"),
    BAIXA(1, "Baixa"),
    MEDIA(2, "Média"),
    ALTA(3, "Alta");

    private final Integer id;
    private final String descricao;

    Prioridade(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Prioridade x : Prioridade.values()) {
            if (cod.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
