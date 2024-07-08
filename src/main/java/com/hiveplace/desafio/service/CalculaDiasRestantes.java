package com.hiveplace.desafio.service;

import com.hiveplace.desafio.controller.TarefaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class CalculaDiasRestantes {

    private static final Logger LOGGER = LoggerFactory.getLogger(TarefaController.class);

    public String calculaDias(String dataTermino) {


        try {
            // Obtém a data atual
            Calendar dataAtual = Calendar.getInstance();

            // Converte a string para um objeto Date
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInformada = sdf.parse(dataTermino);

            // Configura a data informada no objeto Calendar
            Calendar dataInformadaCal = Calendar.getInstance();
            dataInformadaCal.setTime(dataInformada);

            // Calcula a diferença de dias
            long diferencaMillis = dataInformadaCal.getTimeInMillis() - dataAtual.getTimeInMillis();
            long diferencaDias = diferencaMillis / (24 * 60 * 60 * 1000);
            diferencaDias=diferencaDias+1;

            return Long.toString(diferencaDias);
        } catch (ParseException ex){
            LOGGER.info("ParseException Calcula dias: " + ex);
        }


        return null;
    }


}
