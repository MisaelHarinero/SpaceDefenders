package sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class ZergsSprites {
    private BufferedImage bfTotalImg;
    private BufferedImage images [][];
    private BufferedImage abilities [];
    public final static int DIMENSIONS = 60;
    public ZergsSprites (){
        chargeImages();

    }
    public void chargeImages(){
        int x = 5, y = 65;
        bfTotalImg = chargeImages("resource/zerg/zerg.png");
        this.images = new BufferedImage[9][7];
        for (int i = 0; i <this.images.length ; i++) {
            for (int j = 0; j <this.images[i].length ; j++) {
                this.images[i][j] = cargarImgRescal(x,y);

                y+=65;
            }
             x+=72;
             y = 65;
        }
        y = 810;
        x = 435;
        this.abilities = new BufferedImage[9];
        for (int i = 0; i <this.abilities.length ; i++) {
            if (i==2){
                y+=65;
                x = 5;
            }
            if (i>=2){
                this.abilities[i] = cargarImgRescal(x,y);
                x+=72;

            }else{
                this.abilities[i] = cargarImgRescal(x,y);
                x+=72;
            }
        }





    }
    public BufferedImage cargarImgRescal(int x, int y){
        BufferedImage bf = this.bfTotalImg.getSubimage(x,y,DIMENSIONS,DIMENSIONS);
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

    public BufferedImage[][] getImages() {
        return images;
    }

    public void setImages(BufferedImage[][] images) {
        this.images = images;
    }

    public BufferedImage[] getAbilities() {
        return abilities;
    }

    public void setAbilities(BufferedImage[] abilities) {
        this.abilities = abilities;
    }

}

