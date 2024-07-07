package com.hiveplace.desafio.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hiveplace.desafio.enums.StatusTarefa;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
public class Tarefa {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private StatusTarefa status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataTermino;

    private List<String> listAnexosUrl = new ArrayList<>();

    public Tarefa(){
    }

    private Tarefa(Builder builder) {
        this.id = builder.id;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.status = builder.status;
        this.dataTermino = builder.dataTermino;
        this.listAnexosUrl = builder.listAnexosUrl;
    }

    public Tarefa atualiza(Tarefa tarefaAntiga){
        return builderFrom(this)
                .withId(tarefaAntiga.getId())
                .withArrayListAnexosUrl(tarefaAntiga.getListAnexosUrl())
                .build();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public List<String> getListAnexosUrl() {
        return listAnexosUrl;
    }

    public void setListAnexosUrl(String url) {
        listAnexosUrl.add(url);
    }


    public static Builder builder(){
        return new Builder();
    }

    public static Builder builderFrom(Tarefa tarefa){
        return new Builder(tarefa);
    }

    public static class Builder {

        private String id;
        private String nome;
        private String descricao;
        private StatusTarefa status;
        private LocalDate dataTermino;

        private List<String> listAnexosUrl = new ArrayList<>();

        public Builder(){

        }

        public Builder(Tarefa tarefa) {
            this.id = tarefa.id;
            this.nome = tarefa.nome;
            this.descricao = tarefa.descricao;
            this.status = tarefa.status;
            this.dataTermino = tarefa.dataTermino;
            this.listAnexosUrl = tarefa.listAnexosUrl;
        }

        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withNome(String nome){
            this.nome = nome;
            return this;
        }

        public Builder withDescricao(String descricao){
            this.descricao = descricao;
            return this;
        }

        public Builder withStatus(StatusTarefa status){
            this.status = status;
            return this;
        }

        public Builder withDataTermino(LocalDate dataTermino){
            this.dataTermino = dataTermino;
            return this;
        }

        public Builder withListAnexosUrl(String listAnexosUrl){
            this.listAnexosUrl.add(listAnexosUrl);
            return this;
        }

        public Builder withArrayListAnexosUrl(List<String> listAnexosUrl){
            this.listAnexosUrl = listAnexosUrl;
            return this;
        }

        public Tarefa build(){
            return new Tarefa(this);
        }

    }

}
