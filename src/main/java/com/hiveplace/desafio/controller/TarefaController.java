package com.hiveplace.desafio.controller;

import com.hiveplace.desafio.converter.TarefaConverter;
import com.hiveplace.desafio.domain.Tarefa;
import com.hiveplace.desafio.dto.CreateTarefaDTO;
import com.hiveplace.desafio.dto.DetailTarefaDTO;
import com.hiveplace.desafio.dto.UpdateTarefaDTO;
import com.hiveplace.desafio.enums.StatusTarefa;
import com.hiveplace.desafio.repository.TarefaRepository;
import com.hiveplace.desafio.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RequestMapping("/tarefa")
@RestController
public class TarefaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarefaController.class);

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private TarefaConverter converter;

    @Autowired
    private S3Service s3Service;

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
        return repository.findAll().map(converter::toDTO);
    }

    @GetMapping("/listarTodas/{numStatus}")
    public Flux<DetailTarefaDTO> listaPorStatus(@PathVariable Integer numStatus){
        return repository.findByStatus(StatusTarefa.toEnum(numStatus)).map(converter::toDTO);
    }

    @PutMapping("/atualizar")
    public Mono<DetailTarefaDTO> atualizaTarefa(@RequestBody UpdateTarefaDTO request){
        Tarefa modelUpdate = converter.toModelUpdate(request);
        System.out.println(modelUpdate.toString());
        return  repository.findById(modelUpdate.getId())
                .map(modelUpdate::atualiza)
                .flatMap(repository::save)
                .map(converter::toDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/anexo")
    public Mono<URI> subirAnexo(@RequestParam(name = "file") MultipartFile file){
        URI uri = s3Service.uploadFile(file);
        return Mono.just(uri);
    }


}
