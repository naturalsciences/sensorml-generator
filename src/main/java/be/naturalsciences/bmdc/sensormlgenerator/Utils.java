/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.naturalsciences.bmdc.sensormlgenerator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thomas
 */
public class Utils {
    
        public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        str = str.replace(",", ".");
        try {
            double d = Integer.parseInt(str);
            return false;
        } catch (NumberFormatException nfe) {
            try {
                double d = Double.parseDouble(str);
            } catch (NumberFormatException nfe2) {
                return false;
            }
        }

        return true;
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isText(String str) {
        Pattern p = Pattern.compile("[a-zA-Z_\\-/\\\\]+");
        Matcher m = p.matcher(str);
        return m.find();
    }
}
