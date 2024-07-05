package com.hiveplace.desafio.repository;

import com.hiveplace.desafio.domain.Tarefa;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends ReactiveMongoRepository<Tarefa, String> {
}
