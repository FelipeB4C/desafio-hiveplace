package com.hiveplace.desafio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="consultaPrioridade", url="${aws.lambda.function}")
public interface LambdaFunctionClient {

    @GetMapping(value="/get-priority?dias={dias}", consumes="application/json")
    Integer prioridade(@PathVariable("dias") String dias, @RequestHeader("authorizationToken") String senha);

}
