package com.iso.client.configuration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

//adding by Dwi Sutrisno
public class Util {

    //convert string value to currency value
    public static String formatCurrency(String value, int dec) {
        NumberFormat numberFormater = NumberFormat.getInstance();
        numberFormater.setMaximumFractionDigits(dec);
        numberFormater.setMinimumFractionDigits(dec);
        return "Rp. " + numberFormater.format(Double.parseDouble(value)).replace(",", ".");
    }


    //change string data to rupiah currency
    public static String formatCurrencyMulti(int dec, String... texts) {
        double total = 0.0;
        for (String text : texts)
            total += Double.parseDouble(text);

        NumberFormat numberFormater = NumberFormat.getInstance();
        numberFormater.setMaximumFractionDigits(dec);
        numberFormater.setMinimumFractionDigits(dec);
        return "Rp. " + numberFormater.format(total).replace(",", ".");
    }

    //change string data to rupiah currency
    public static String decimalFormater(String value1) {
        double d1 = Double.parseDouble(value1);

        DecimalFormat df2 = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df2.setDecimalFormatSymbols(dfs);

        if ((d1 - (int) d1) != 0) {
            return "Rp. " + df2.format(d1);
        } else {
            String value = df2.format(d1);
            return "Rp. " + value.substring(0, value.length() - 3);
        }

    }

    //mutiple value change string data to rupiah currency
    public static String decimalFormaterMulti(String... value1) {
        double d1 = 0.0;
        for (String text : value1)
            d1 += Double.parseDouble(text);

        DecimalFormat df2 = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setCurrencySymbol("");
        dfs.setMonetaryDecimalSeparator(',');
        dfs.setGroupingSeparator('.');
        df2.setDecimalFormatSymbols(dfs);

        if ((d1 - (int) d1) != 0) {
            return "Rp. " + df2.format(d1);
        } else {
            String value = df2.format(d1);
            return "Rp. " + value.substring(0, value.length() - 3);
        }
    }


    //mask string value
    public static String maskString(String strText, int start, int end, char maskChar)
            throws Exception {

        if (strText == null || strText.equals(""))
            return "";

        if (start < 0)
            start = 0;

        if (end > strText.length())
            end = strText.length();

        if (start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if (maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for (int i = 0; i < maskLength; i++) {
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }


}
