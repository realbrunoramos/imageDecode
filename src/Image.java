import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    BufferedImage image;

    public Image(String imagePath) {
        try {
            imagePath = imagePath==null?"":imagePath.replaceAll("\"", "");
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Invalid path");;
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}