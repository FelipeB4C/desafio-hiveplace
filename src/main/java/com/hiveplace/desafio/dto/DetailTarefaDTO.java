package com.hiveplace.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DetailTarefaDTO(String nome, String descricao, String status, String dataTermino) {
}
