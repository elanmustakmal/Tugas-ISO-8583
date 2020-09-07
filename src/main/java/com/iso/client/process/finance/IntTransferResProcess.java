package com.iso.client.process.finance;

import com.iso.client.configuration.PackagerConfig;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IntTransferResProcess implements Processor {
    PackagerConfig packagerConfig = new PackagerConfig();

    @Override
    public void process(Exchange exchange) throws Exception {
        String isoMessage = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(unpackFromIso(isoMessage));


    }

    private Map unpackFromIso(String stringMsg) {
        Map resultMap = new HashMap();
        try {
            org.jpos.iso.ISOMsg isoMsg = new org.jpos.iso.ISOMsg();
            isoMsg.setPackager(packagerConfig.getPackagerFinancial());
            isoMsg.unpack(stringMsg.getBytes());

            if (isoMsg.getString(39).equals("00")) {
                resultMap.put("account name", isoMsg.getString(43));
                resultMap.put("account number", isoMsg.getString(102));
                resultMap.put("message", isoMsg.getString(120));
                resultMap.put("response code", isoMsg.getString(39));
                resultMap.put("destinationAccountName",ISOUtil.takeFirstN(isoMsg.getString(48), 30));
                resultMap.put("destinationAccountNumber", ISOUtil.takeLastN(isoMsg.getString(48),10));
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
