package com.iso.client.process.echo;

import com.iso.client.configuration.Constants;
import com.iso.client.configuration.PackagerConfig;
import com.iso.client.utility.CurrentId;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;

import java.util.Date;

//created By Dwi S - Agustus 2020
@Slf4j
public class EchoReqProcess implements Processor {

    private PackagerConfig config = new PackagerConfig();
    CurrentId currentId = new CurrentId();

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setBody(new String(buildISOMessage(currentId.stanGenerator())));
    }

    private byte[] buildISOMessage(String stan) throws Exception {
        Date dateNow = new Date();

        try {
            ISOMsg isoMsg = new ISOMsg("0800"); //network mti
            isoMsg.setPackager(config.getPackagerNetworkManagement());
            isoMsg.set(7, Constants.DE_007_TIME_FORMATTER.format(dateNow)); //TRANSMISSION DATE AND TIME
            isoMsg.set(11, stan); //SYSTEM TRACE AUDIT NUMBER
            isoMsg.set(12, Constants.DE_012_TIME_FORMATTER.format(dateNow)); //TIME, LOCAL TRANSACTION
            isoMsg.set(13, Constants.DE_013_TIME_FORMATTER.format(dateNow)); //DATE, LOCAL TRANSACTION
            isoMsg.set(18, "0001"); //DELIVERY CHANNEL
            isoMsg.set(70, "001"); //NETWORK MANAGEMENT INFORMATION CODE

            return isoMsg.pack();
        } catch (ISOException e) {
            throw new Exception(e);
        }
    }

}
