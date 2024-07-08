package com.hiveplace.desafio.converter;

import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.dto.DetailTarefaDTO;
import com.hiveplace.desafio.dto.UpdateTarefaDTO;
import com.hiveplace.desafio.enums.Prioridade;
import com.hiveplace.desafio.enums.StatusTarefa;
import org.springframework.stereotype.Component;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Component
public class TarefaConverter {

    public Tarefa toModel(CreateTarefaDTO tarefaDTO, Integer prioridade){
        return Optional.ofNullable(tarefaDTO)
                .map(task -> Tarefa.builder()
                        .withNome(task.nome())
                        .withDescricao(task.descricao())
                        .withStatus(StatusTarefa.toEnum(0))
                        .withPrioridade(Prioridade.toEnum(prioridade))
                        .withDataTermino(toLocalDate(task.dataTermino()))
                        .build())
                .orElse(null);
    }

    private LocalDate toLocalDate(String data){
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(frmt.parse(data));
    }

    public DetailTarefaDTO toDTO(Tarefa tarefa) {
        return Optional.ofNullable(tarefa)
                .map(task -> {
                    DetailTarefaDTO tarefaDTO = new DetailTarefaDTO(
                            task.getId(),
                            task.getNome(),
                            task.getDescricao(),
                            task.getStatus().toString(),
                            task.getPrioridade().toString(),
                            converterData(task.getDataTermino()),
                            task.getListAnexosUrl());
                    return tarefaDTO;
                }).orElse(null);
    }

    private String converterData(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public Tarefa toModelUpdate(UpdateTarefaDTO updateTarefaDTO){
        return Optional.ofNullable(updateTarefaDTO)
                .map(task -> Tarefa.builder()
                        .withId(task.id())
                        .withNome(task.nome())
                        .withDescricao(task.descricao())
                        .withStatus(StatusTarefa.toEnum(task.status()))
                        .withPrioridade(Prioridade.toEnum(task.prioridade()))
                        .withDataTermino(toLocalDate(task.dataTermino()))
                        .build())
                .orElse(null);
    }

    public Tarefa toAnexosUpdate(Tarefa tarefa, URI anexoUri){
        return Optional.ofNullable(tarefa)
                .map(task -> {
                    task.setListAnexosUrl(anexoUri.toString());
                    return task;
                })
                .orElse(null);
    }

}
