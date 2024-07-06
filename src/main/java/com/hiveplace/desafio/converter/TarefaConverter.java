package com.hiveplace.desafio.converter;

import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.dto.DetailTarefaDTO;
import com.hiveplace.desafio.dto.UpdateTarefaDTO;
import com.hiveplace.desafio.enums.StatusTarefa;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private LocalDate toLocalDate(String data){
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(frmt.parse(data));
    }

    public DetailTarefaDTO toDTO(Tarefa tarefa) {
        return Optional.ofNullable(tarefa)
                .map(task -> {
                    DetailTarefaDTO tarefaDTO = new DetailTarefaDTO(
                            task.getNome(),
                            task.getDescricao(),
                            task.getStatus().toString(),
                            converterData(task.getDataTermino()));
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
                        .withDataTermino(toLocalDate(task.dataTermino()))
                        .build())
                .orElse(null);
    }

}
