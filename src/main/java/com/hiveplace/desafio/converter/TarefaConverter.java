package com.hiveplace.desafio.converter;

import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.enums.StatusTarefa;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class TarefaConverter {

    public Tarefa toModel(CreateTarefaDTO tarefaDTO){
        return Optional.ofNullable(tarefaDTO)
                .map(task -> Tarefa.builder()
                        .withNome(task.nome())
                        .withDescricao(task.descricao())
                        .withStatus(StatusTarefa.toEnum(0))
                        .withDataTermino(toLocalDate(task.dataTermino()))
                        .build())
                .orElse(null);
    }

    public LocalDate toLocalDate(String data){
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(frmt.parse(data));
    }

}
