package com.hiveplace.desafio.enums;

public enum StatusTarefa {

    A_FAZER(0, "A fazer"),
    EM_DESENVOLVIMENTO(1, "Em desenvolvimento"),
    CONCLUIDA(2, "Concluída"),
    NAO_CONCLUIDA(3, "Não concluída");

    private final Integer id;
    private final String descricao;

    StatusTarefa(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusTarefa toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (StatusTarefa x : StatusTarefa.values()) {
            if (cod.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}