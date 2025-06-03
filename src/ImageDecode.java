import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImageDecode {
    public static void imageCode(BufferedImage image, String imageName){
        int width = image.getWidth();
        int height = image.getHeight();
        String width35 = LongBase.convertToLongBase(width);
        String height35 = LongBase.convertToLongBase(height);
        LinkedHashMap<Integer, Integer> colorIntAndNumColor = new LinkedHashMap<>();
        ArrayList<String> toZip = new ArrayList<>();
        int numColor = 0;
        StringBuilder result = new StringBuilder();
        result.append(width35).append("x").append(height35).append("@").append(imageName).append("@");

        for (int y=0; y<height; y++){
            for (int x=0; x<width; x++){
                int colorInt = image.getRGB(x,y);
                if (colorIntAndNumColor.containsKey(colorInt)){
                    if (colorIntAndNumColor.get(colorInt)==0){
                        toZip.add("0");
                    }else {
                        toZip.add(LongBase.convertToLongBase(colorIntAndNumColor.get(colorInt)));
                    }
                } else{
                    colorIntAndNumColor.put(colorInt, numColor);
                    if (numColor==0){
                        toZip.add("0");
                    }else {
                        toZip.add(LongBase.convertToLongBase(numColor));
                    }
                    numColor++;
                }
            }
        }
        result.append(zipColorNums(toZip));
        result.setCharAt(result.length()-1, '@');
        for (Map.Entry<Integer, Integer> entry : colorIntAndNumColor.entrySet()){
            int colorInt = entry.getKey();
            result.append(LongBase.convertToLongBaseCI(colorInt)).append(",");
        }
        new AppendToFile("src/codeImages/"+imageName+".txt", result+"");
    }
    public static StringBuilder zipColorNums(ArrayList<String> colorNums){
        String previous = colorNums.get(0);
        StringBuilder result = new StringBuilder();
        int mult = 1;
        for (int c=1; c<colorNums.size(); c++){
            String atual = colorNums.get(c);
            if(previous.equals(atual)){
                mult++;
            } else {
                String mux = LongBase.convertToLongBase(mult);
                mux = mux.equals("1")?"":"*"+mux;
                result.append(previous).append(mux).append(",");
                mult = 1;
                previous = atual;
                if (c==colorNums.size()-1){
                    result.append(previous).append("*").append(mult).append(",");
                }
            }
        }
        return result;
    }
    public static String[] unzipColorNums(String[] colorNums){
        ArrayList<String> result = new ArrayList<>();
        for (String colorNum : colorNums) {
            String[] arr = colorNum.split("\\*");
            int mux = arr.length==1?1: LongBase.convertToDecimal(arr[1]);
            String num = arr[0];
            for (int n = 0; n < mux; n++) {
                result.add(num);
            }
        }
        return result.toArray(new String[0]);
    }
    public static void setPixel(BufferedImage image, int x, int y, Color color, float opacity) {
        Graphics2D g2d = image.createGraphics();

        Color transparentColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(opacity* 255));

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(transparentColor);
        g2d.fillRect(x, y, 1, 1);

        g2d.dispose();
    }
    public static BufferedImage imageDecode(String imageName){
        int height;
        int width;
        String line0;
        String line;
        BufferedImage image = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/codeImages/"+imageName+".txt"))) {
            while ((line0 = reader.readLine()) != null) {
                line = line0.split("@")[1];
                if (line.equals(imageName)){
                    line = line0.split("@")[0];
                    String[] arr = line.split("x");
                    width = LongBase.convertToDecimal(arr[0]);
                    height = LongBase.convertToDecimal(arr[1]);
                    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                    String[] numColors = unzipColorNums(line0.split("@")[2].split(","));
                    String[] colorInts = line0.split("@")[3].split(",");
                    int posNC = 0;
                    for (int y=0; y<height; y++){
                        for (int x=0; x<width; x++){
                            int indCI = 0;
                            if (posNC < numColors.length) {
                                indCI = LongBase.convertToDecimal(numColors[posNC]);
                            }

                            int colorInt = LongBase.convertToDecimalCI(colorInts[indCI]);
                            Color color = new Color(colorInt);
                            setPixel(image, x, y, color, colorInt == 0 ? 0f : 1f);
                            posNC++;
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("error >> decodeImage");
        }
        return image;
    }
}