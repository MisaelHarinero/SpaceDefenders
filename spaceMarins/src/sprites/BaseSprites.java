package sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Misael Harinero
 * Clase en la que cargamos todos los sprites de la base
 */
public class BaseSprites {
    private BufferedImage imageTotal;
    private BufferedImage secuencesImages [] ;
    private final static int DIMENSIONS_WIDTH = 120;
    private final static int DIMENSIONS_HEIGHT = 110;

    public BaseSprites() {
            chargeImages();
    }
    public void chargeImages(){
        int x = 0, y = 0;
        this.imageTotal = chargeImages("resource/base/base.png");
        this.secuencesImages = new BufferedImage[5];
        for (int i = 0; i < this.secuencesImages.length ; i++) {
            this.secuencesImages[i] = cargarImgRescal(x,y);
            x+=DIMENSIONS_WIDTH;
        }



    }

    /**
     * Metodo en el que hacemos un subImage con la SpriteSheet cargada iniciamente
     * @param x
     * @param y
     * @return
     */
    public BufferedImage cargarImgRescal(int x, int y){
        BufferedImage bf = this.imageTotal.getSubimage(x,y,DIMENSIONS_WIDTH,DIMENSIONS_HEIGHT);
        return  bf;
    }
    public BufferedImage chargeImages(String rute){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(rute));
        } catch (IOException e) {
            System.out.println(rute);
            e.printStackTrace();
        }

        return  bf;
    }

    public BufferedImage[] getSecuencesImages() {
        return secuencesImages;
    }

    public void setSecuencesImages(BufferedImage[] secuencesImages) {
        this.secuencesImages = secuencesImages;
    }
}
