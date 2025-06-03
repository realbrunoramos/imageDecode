import java.util.ArrayList;

public class LongBase {
    static String LongBaseDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static int len = LongBaseDigits.length();
    public static int convertToDecimalCI(String longBase) {
        boolean isPositive = longBase.startsWith("+");
        if (isPositive){
            longBase = longBase.replaceAll("\\+", "");
        }
        int decimalNumber = 0;
        int digitValue = 0;
        int exp = 0;
        for(int c = longBase.length()-1; c>=0; c--){
            for (int i = 0; i<len; i++){
                if (LongBaseDigits.charAt(i) == longBase.charAt(c)){
                    digitValue = i;
                    break;
                }
            }
            int ratio = (int)Math.pow(len, exp);
            decimalNumber += digitValue*ratio;
            exp++;
        }
        if (!isPositive){
            decimalNumber *= -1;
        }
        return decimalNumber;
    }
    public static String convertToLongBaseCI(int decimal) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> arr = new ArrayList<>();
        boolean isNegative = decimal<0;
        if (!isNegative){
            result.append("+");
        } else {
            decimal *= -1;
        }
        while (decimal > 0) {
            int remainder = decimal % len;
            arr.add(LongBaseDigits.charAt(remainder)+"");
            decimal /= len;
        }
        for (int s = arr.size()-1; s>=0; s--){
            result.append(arr.get(s));
        }
        return result.toString();
    }
    public static int convertToDecimal(String longBase) {
        boolean isNegative = longBase.startsWith("-");
        if (isNegative){
            longBase = longBase.replaceAll("-", "");
        }
        int decimalNumber = 0;
        int digitValue = 0;
        int exp = 0;
        for(int c = longBase.length()-1; c>=0; c--){
            for (int i = 0; i<len; i++){
                if (LongBaseDigits.charAt(i) == longBase.charAt(c)){
                    digitValue = i;
                    break;
                }
            }
            int ratio = (int)Math.pow(len, exp);
            decimalNumber += digitValue*ratio;
            exp++;
        }
        if (isNegative){
            decimalNumber *= -1;
        }
        return decimalNumber;
    }
    public static String convertToLongBase(int decimal) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> arr = new ArrayList<>();
        boolean isNegative = decimal<0;
        if (isNegative){
            result.append("-");
            decimal *= -1;
        }
        while (decimal > 0) {
            int remainder = decimal % len;
            arr.add(LongBaseDigits.charAt(remainder)+"");
            decimal /= len;
        }
        for (int s = arr.size()-1; s>=0; s--){
            result.append(arr.get(s));
        }
        return result.toString();
    }
}