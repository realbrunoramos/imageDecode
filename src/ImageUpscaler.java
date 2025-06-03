import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class ImageUpscaler {
    private File inputFile;
    public BufferedImage image;
    int scale;

    public ImageUpscaler(BufferedImage image, int scale) {
        this.image = image;
        this.scale = scale;
        upscaleImage();
    }
    private void upscaleImage() {
        // Carrega a imagem PNG existente
        BufferedImage inputImage = image;

        // Define o fator de aumento da resolução (por exemplo, 2 significa o dobro da resolução)

        // Calcula as dimensões da nova imagem
        int newWidth = inputImage.getWidth() * scale;
        int newHeight = inputImage.getHeight() * scale;

        // Cria uma nova imagem com as dimensões aumentadas
        image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        // Aplica a interpolação bilinear para aumentar a resolução da imagem
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int srcX = x / scale;
                int srcY = y / scale;
                int color = inputImage.getRGB(srcX, srcY);
                image.setRGB(x, y, color);
            }
        }

    }

    public BufferedImage getImage() {
        return image;
    }


}
