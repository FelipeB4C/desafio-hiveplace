package com.hiveplace.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

public record DetailTarefaDTO(String id, String nome, String descricao, String status,
                              String dataTermino, List<String> listAnexosUrl) {
}
