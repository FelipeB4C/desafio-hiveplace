package com.hiveplace.desafio.controller;

import com.hiveplace.desafio.converter.TarefaConverter;
import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.dto.DetailTarefaDTO;
import com.hiveplace.desafio.enums.StatusTarefa;
import com.hiveplace.desafio.repository.TarefaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequestMapping("/tarefa")
@RestController
public class TarefaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarefaController.class);

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private TarefaConverter converter;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/salvar")
    public Mono<Tarefa> salvarTarefa(@RequestBody CreateTarefaDTO request){
        Tarefa model = converter.toModel(request);
        return Mono.just(model)
                .flatMap(repository::save)
                .doOnNext(it -> LOGGER.info("Tarefa salva com o id {}", it.getId()));
    }

    @GetMapping("/listarTodas")
    public Flux<DetailTarefaDTO> listarTodasTarefas(){
        Flux<Tarefa> listaTarefa = repository.findAll();
        return repository.findAll().map(converter::toDTO);
    }


}
