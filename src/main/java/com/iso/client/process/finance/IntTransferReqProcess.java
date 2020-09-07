package com.iso.client.process.finance;

import com.iso.client.configuration.Constants;
import com.iso.client.configuration.PackagerConfig;
import com.iso.client.model.User;
import com.iso.client.utility.CurrentId;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.jpos.iso.ISOBitMap;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOUtil;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public class IntTransferReqProcess implements Processor {
    private PackagerConfig config = new PackagerConfig();
    private ISOUtil isoUtil = new ISOUtil();
    CurrentId currentId = new CurrentId();

    @Override
    public void process(Exchange exchange) throws Exception {
        User user = exchange.getMessage().getBody(User.class);
        exchange.getIn().setBody(new String(buildISOMessage(currentId.stanGenerator(),currentId.nextJournalId(),user)));
    }

    private byte [] buildISOMessage(String stanGenerator, String nextJournalId, User user) throws Exception{
        Date dateNow = new Date();

        try {
            ISOMsg isoMsg = new ISOMsg("0200");
            isoMsg.setPackager(config.getPackagerFinancial());
            isoMsg.set(2, user.getPan());
            isoMsg.set(3, Constants.INQUIRY_DE3_INTERNAL_TRANSFER_PROCESS_CODE);
            isoMsg.set(4, user.getAmount());
            isoMsg.set(7, Constants.DE_007_TIME_FORMATTER.format(dateNow));
            isoMsg.set(11, stanGenerator);
            isoMsg.set(12, Constants.DE_012_TIME_FORMATTER.format(dateNow));
            isoMsg.set(13, Constants.DE_013_TIME_FORMATTER.format(dateNow));
            isoMsg.set(37, nextJournalId); //retrieval reference number
            isoMsg.set(43, user.getSourceAccountName());
            isoMsg.set(48, ISOUtil.strpad(user.getDestinationAccountName(),30) + user.getDestinationAccountNumber() );
            isoMsg.set(49, Constants.INQUIRY_DE49_CURRENCY);
            isoMsg.set(52, user.getEncryptedPinBlock());
            isoMsg.set(102, user.getSourceAccountNumber());

            return isoMsg.pack();

        } catch (ISOException e) {
            throw new Exception(e);
        }


    }

}
