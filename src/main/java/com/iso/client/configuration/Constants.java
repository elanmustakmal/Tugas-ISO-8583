package com.iso.client.configuration;

import java.text.SimpleDateFormat;

//created By Dwi S - Agustus 2020

public class Constants {

    public static final String RC_INTERNAL_ERROR = "362";

    public static final SimpleDateFormat DE_007_TIME_FORMATTER = new SimpleDateFormat("MMddhhmmss");
    public static final SimpleDateFormat DE_012_TIME_FORMATTER = new SimpleDateFormat("hhmmss");
    public static final SimpleDateFormat DE_013_TIME_FORMATTER = new SimpleDateFormat("MMdd");


    public static final String INQUIRY_DE3_INQUIRY_PROCESS_CODE = "111111";     // process code untuk inquiry
    public static final String INQUIRY_DE3_PAYMENT_PROCESS_CODE = "222222";     // process code untuk payment
    public static final String INQUIRY_DE3_INTERNAL_TRANSFER_PROCESS_CODE = "333333";    // process code untuk transfer
    public static final String INQUIRY_DE3_TRANSFER_PROCESS_CODE = "555555";    // process code untuk transfer
    public static final String INQUIRY_DE3_SETOR_PROCESS_CODE = "444444";    // process code untuk transfer

    public static final String INQUIRY_DE18_MERCHANT_TYPE = "1234";
    public static final String INQUIRY_DE29_AMOUNT = "00000000";
    public static final String INQUIRY_DE32_ACQUIRING_INSTITUTION = "451";
    public static final String INQUIRY_DE33_FORWARDING_INSTITUTION = "451004";
    public static final String INQUIRY_DE41_INQUIRY = "00307183";
    public static final String INQUIRY_DE41_PAYMENT = "00307181";
    public static final String INQUIRY_DE49_CURRENCY = "360";                   //kode rupiah "360"
    public static final String INQUIRY_DE126 = "000";
    public static final String INQUIRY_DE127 = "0";
    public static final String PAN = "7777777";


}
