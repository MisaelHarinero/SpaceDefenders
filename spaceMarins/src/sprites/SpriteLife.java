package sprites;

import model.Marine;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteLife {
    public final static int MAX_HEIGHT = 4;
    public final static int MAX_WIDTH = 20;
    private final static Color BASE_COLOR = Color.BLACK;
    private final static Color MID_LIFE_COLOR = Color.ORANGE;
    private final static Color HEALTH_COLOR = Color.RED;
    private final static Color MAXHEALTH_COLOR = Color.GREEN;
    private int life;
    private int x;
    private int y;
    private BufferedImage bf;
    public SpriteLife(int life,int x, int y){
        bf = new BufferedImage(MAX_WIDTH,MAX_HEIGHT,BufferedImage.TYPE_INT_ARGB);
        this.life = life;
        this.x = x;
        this.y = y;
        genBuffer();
    }
    public void genBuffer(){
        Graphics graphics  = this.bf.getGraphics();
        graphics.setColor(BASE_COLOR);
        graphics.fillRect(0,0,MAX_WIDTH,MAX_HEIGHT);
       if (life>12){
           graphics.setColor(MAXHEALTH_COLOR);
       }else{
           if (life>6){
            graphics.setColor(MID_LIFE_COLOR);
           }else{
               graphics.setColor(HEALTH_COLOR);
           }
       }
        graphics.fillRect(0,1,life,2);
        graphics.dispose();

    }
    public void actualizarCordenadas(int x, int y){
        this.x = x+3;
        this.y = y-3;
    }
    public void recibirDano(int life){
        this.life = life;
        genBuffer();
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getBf() {
        return bf;
    }

    public void setBf(BufferedImage bf) {
        this.bf = bf;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
