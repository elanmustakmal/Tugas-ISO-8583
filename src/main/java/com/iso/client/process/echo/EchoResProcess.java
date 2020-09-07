package com.iso.client.process.echo;


import com.iso.client.configuration.PackagerConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jpos.iso.ISOException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//created By Dwi S - Agustus 2020

@Slf4j
public class EchoResProcess implements Processor {

    PackagerConfig packagerConfig = new PackagerConfig();

    @Override
    public void process(Exchange exchange) throws Exception {
        String isoMessage = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(unpackFromIso(isoMessage));
    }

    public Map unpackFromIso(String stringMsg) {
        Map resultMap = new HashMap();
        try {
            org.jpos.iso.ISOMsg isoMsg = new org.jpos.iso.ISOMsg();
            isoMsg.setPackager(packagerConfig.getPackagerNetworkManagement());
            isoMsg.unpack(stringMsg.getBytes());

            if (isoMsg.getString(39).equals("00")) {
                resultMap.put("responseCode", isoMsg.getString(39));
                resultMap.put("message", "success");
            } else {
                resultMap.put("responseCode", isoMsg.getString(39));
                resultMap.put("message", "failed ");
            }

            resultMap.put("ISO", stringMsg);
        } catch (ISOException e) {
            e.printStackTrace();
            resultMap.put("responseCode", "99");
            resultMap.put("message", "failed ");
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("message", "failed ");
            resultMap.put("responseCode", "99");
        }
        return resultMap;
    }


}
