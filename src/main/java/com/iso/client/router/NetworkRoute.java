package com.iso.client.router;

import com.iso.client.process.echo.EchoReqProcess;
import com.iso.client.process.echo.EchoResProcess;
import com.iso.client.process.finance.*;
import org.apache.camel.Exchange;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


//created By Dwi S - Agustus 2020
@Component
public class NetworkRoute extends RouteBuilder {

    EchoReqProcess reqProcess = new EchoReqProcess();
    EchoResProcess resProcess = new EchoResProcess();

    SetorReqProcess finReqProcess = new SetorReqProcess();
    SetorResProcess finResProcess = new SetorResProcess();

    IntTransferReqProcess intTransferReqProcess = new IntTransferReqProcess();
    IntTransferResProcess intTransferResProcess = new IntTransferResProcess();

    TransferReqProcess transferReqProcess = new TransferReqProcess();
    TransferResProcess transferResProcess = new TransferResProcess();

    @Override
    public void configure() {

        //Echo
        from("seda:ECHO_TEST")
                .doTry()
                .process(reqProcess)
                .to("netty:tcp://{{remote.tcp.server.host}}:{{remote.tcp.server.port}}?"
                        + "encoders=#stringToByteEncoder&decoders=#byteToStringDecoder")
                .process(resProcess)
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"));

        //Setor Tunai

        from("seda:SETOR_TEST")
                .doTry()
                .process(finReqProcess)
                .to("netty:tcp://{{remote.tcp.server.host}}:{{remote.tcp.server.port}}?"
                        + "encoders=#stringToByteEncoder&decoders=#byteToStringDecoder")
                .process(finResProcess)
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"));

        //Internal Transfer
        from("seda:INTTRANSFER_TEST")
                .doTry()
                .process(intTransferReqProcess)
                .to("netty:tcp://{{remote.tcp.server.host}}:{{remote.tcp.server.port}}?"
                        + "encoders=#stringToByteEncoder&decoders=#byteToStringDecoder")
                .process(intTransferResProcess)
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"));

        //Transfer Antar Bank
        from("seda:TRANSFER_TEST")
                .doTry()
                .process(transferReqProcess)
                .to("netty:tcp://{{remote.tcp.server.host}}:{{remote.tcp.server.port}}?"
                        + "encoders=#stringToByteEncoder&decoders=#byteToStringDecoder")
                .process(transferResProcess)
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE, simple("application/json"));



    }
}
