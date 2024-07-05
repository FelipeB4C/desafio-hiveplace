package com.hiveplace.desafio.converter;

import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.dto.DetailTarefaDTO;
import com.hiveplace.desafio.enums.StatusTarefa;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public LocalDate toLocalDate(String data){
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
                            task.getDataTermino().toString());
                    return tarefaDTO;
                }).orElse(null);
    }


    public List<DetailTarefaDTO> listToDTO(List<Tarefa> listaTarefa) {
        return Optional.ofNullable(listaTarefa)
                .map(array -> array.stream()
                        .map(this::toDTO).collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

}
