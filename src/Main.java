import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void saveImage  (BufferedImage image, String filePath)  {
        String format = "png";
        try {
            ImageIO.write(image, format, new File(filePath+"."+format));
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
    public static String thePath;

    public static void main(String[] args) {
        System.out.println("IMAGE-DECODE\n");
        while (true){
            System.out.println("Image to Code(1)\tCode to image(2)\tExit(3)\t Upscale an image(U)");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("U")){
                System.out.println("Enter the image's title");
                String title = scanner.nextLine();
                BufferedImage image = new Image("src/imagesDecoded/"+title+".png").getImage();
                if (image!=null) {
                    System.out.println("Enter the scale (has to be an integer number)");
                    int scale = scanner.nextInt();
                    if (scale*Math.max(image.getHeight(), image.getWidth())>10000){
                        System.out.println("Resolution super high!");
                    } else{
                        image = new ImageUpscaler(image,  scale).getImage();
                        saveImage(image, "src/imagesDecoded/"+title+"-upscaled");
                        System.out.println("success");
                    }

                } else {
                    System.out.println("The image's title might be wrong or don't exist. Try again");
                }
            }
            else if (input.equals("1")){
                String imageName;
                do {
                    System.out.println("Enter the image name to search:");
                    imageName = scanner.nextLine();
                    thePath = new PathSearcher("", imageName, false).getThePath();
                    if (thePath!=null){
                        BufferedImage image = new Image(thePath).getImage();
                        System.out.println("Enter the image's title");
                        String title = scanner.nextLine();
                        ImageDecode.imageCode(image, title);
                        System.out.println("Successfully coded");
                        thePath = "";
                    } else {
                        System.out.println("imagem não encontrada");
                    }
                } while (imageName.equalsIgnoreCase("S") || thePath == null);

            }
            else if(input.equals("2")){
                System.out.println("Enter the image's title");
                String title = scanner.nextLine();
                System.out.println("processing...");
                BufferedImage newImage = ImageDecode.imageDecode(title);
                if (newImage == null){
                    System.out.println("title not found");
                } else {
                    saveImage(newImage, "src/imagesDecoded/"+title);
                    System.out.println("Successfully decoded and saved");
                }
            }
            else if (input.equals("3")){
                break;
            }
            else {
                System.out.println("Invalid input");
            }
        }
    }
}