package com.iso.client.utility;

import org.jpos.iso.ISOUtil;

//Create by Dwi S - Agustus 2020
public class CurrentId {

    private ISOUtil isoUtil = new ISOUtil();
    private static int stan = 0;
    private static int externalId = 0;
    private static int journalId = 0;

    public synchronized static String nextExternalId() {
        if (externalId >= Integer.MAX_VALUE) {
            externalId = 0;
        }
        externalId++;

        return String.format("%06d", externalId);
    }

    public synchronized static String nextJournalId() {
        if (journalId >= Integer.MAX_VALUE) {
            journalId = 0;
        }
        journalId++;
        return String.format("%05d", journalId);
    }


    /**
     * Generate System Trace Audit Number
     */
    public synchronized static String stanGenerator() {
        if (stan == 999999) {
            stan = 0;
        }
        stan++;
        ISOUtil isoUtil = new ISOUtil();
        return isoUtil.zeropad(stan, 6);
    }

}
