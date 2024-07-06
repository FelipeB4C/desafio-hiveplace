package com.hiveplace.desafio.dto;

public record UpdateTarefaDTO(String id, String nome, String descricao, int status, String dataTermino) {
}
