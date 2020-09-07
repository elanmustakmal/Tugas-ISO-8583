package com.iso.client.router;

import com.iso.client.model.User;
import com.iso.client.process.finance.SetorReqProcess;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

//created By Dwi S - Agustus 2020
@Component
public class MainRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("netty-http").host("{{expose.local.http.host}}").port("{{expose.local.http.port}}")
                .bindingMode(RestBindingMode.json);
        //echo
        rest("/service")
                .get("/echo").consumes("application/json").produces("/application/json")
                .type(String.class).to("seda:ECHO_TEST");

        //Setor Tunai
        rest("/service")
                .post("/setorTunai").consumes("/application/json").produces("/application/json")
                .type(User.class).to("seda:SETOR_TEST");

        //Internal Transfer
        rest("/service")
                .post("/internalTransfer").consumes("/application/json").produces("/application/json")
                .type(User.class).to("seda:INTTRANSFER_TEST");

        //Transfer Antar Bank
        rest("/service")
                .post("/transfer").consumes("/application/json").produces("/application/json")
                .type(User.class).to("seda:TRANSFER_TEST");





    }


}
