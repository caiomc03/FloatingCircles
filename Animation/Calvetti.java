package Animation;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Calvetti {
    private static final int RESIZED_WIDTH = 200;
    private static final int RESIZED_HEIGHT = 200;

    public BufferedImage image;
    public List<BufferedImage> spriteSheet;
    public int calvetti_x;

    
    public Calvetti(){

        spriteSheet = new ArrayList<>();
        addImage("projeto_final_v3\\Assets\\sprite_calvetti0.png", spriteSheet);
        addImage("projeto_final_v3\\Assets\\sprite_calvetti1.png", spriteSheet);
        addImage("projeto_final_v3\\Assets\\sprite_calvetti2.png", spriteSheet);
        addImage("projeto_final_v3\\Assets\\sprite_calvetti3.png", spriteSheet);

        
    }


    private void addImage(String filepath, List<BufferedImage> sheet){
        try {
            image = ImageIO.read(new File(filepath));
            image = resizeImage(image, RESIZED_WIDTH, RESIZED_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet.add(image);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    public List<BufferedImage> getSpriteSheet() {
        return spriteSheet;
    }



    public int getHeight(){
        return RESIZED_HEIGHT;
    }
    public int getWidth(){
        return RESIZED_WIDTH;
    }

}
