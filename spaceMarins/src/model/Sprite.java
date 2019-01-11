package model;

import sprites.MarineSprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    private final static int MAX_WIDTH_HEIGHT = 40;
    private final static int INITIAL_POINT_X = 500;
    private final static int INITIAL_POINT_Y = 500;
    private final static  int ACCEL = 1;
    private BufferedImage canvas;
    private int width;
    private int height;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private static  MarineSprites marineSprites;
    private boolean colission;
    private int movement;
    private int xPointDestiny;
    private int yPointDestiny;

    public Sprite() {
        this.canvas = Sprite.getMarineSprites().getmSs();
        if (this.canvas == null){
            generarCuadradoNegro();
        }
        this.movement = 1;
        this.width = MAX_WIDTH_HEIGHT;
        this.height = MAX_WIDTH_HEIGHT;
        this.x = INITIAL_POINT_X;
        this.y = INITIAL_POINT_Y;
        this.xPointDestiny = -1;
        this.yPointDestiny = -1;
        this.xSpeed = ACCEL;
        this.ySpeed = ACCEL;

        this.colission = false;
    }

    public Sprite(BufferedImage img, int width, int height, int x, int y) {
        this.canvas = img;
        if (this.canvas == null){
            generarCuadradoNegro();
        }
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;


    }
    public void generarCuadradoNegro() {
        this.canvas = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.canvas.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.dispose();
    }


    public BufferedImage getCanvas() {
        return canvas;
    }


    public void setCanvas(BufferedImage canvas) {
        this.canvas = canvas;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void move(int maxScreenWidth, int maxScreenHeight) {
        if ((x + width) >= maxScreenWidth) {
            this.xSpeed = -1 *  Math.abs(xSpeed);
        }
        if (x <= 0) {
            this.xSpeed =  Math.abs(xSpeed);
        }

        if (y + height >= maxScreenHeight) {
            this.ySpeed = -1 *  Math.abs(ySpeed);
        }
        if (y <= 0) {
            this.ySpeed =  Math.abs(ySpeed);
        }
        this.x += this.xSpeed;
        this.y += this.ySpeed;

    }


    public int genAlt() {
        return (int) Math.floor(Math.random() * 5);
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public boolean isColission() {
        return colission;
    }

    public void setColission(boolean colission) {
        this.colission = colission;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public static void comprobarSprites(ArrayList<Sprite> sprites) {
        int speedAuxX = 0;
        int speedAuxY = 0;
        ArrayList<Integer> post = new ArrayList<>();
        for (int i = 0; i < sprites.size(); i++) {
            for (int j = 0; j < sprites.size(); j++) {
                if (i != j) {
                    if (sprites.get(i)!=null&&sprites.get(j)!=null) {

                        if (((sprites.get(i).getX() + sprites.get(i).getWidth()) > (sprites.get(j).getX())) && (sprites.get(j).getX() >= (sprites.get(i).getX()))
                                && ((sprites.get(i).getY() + sprites.get(i).getHeight()) > (sprites.get(j).getY())) && (sprites.get(j).getY() >= (sprites.get(i).getY()))) {
                                   if (!sprites.get(i).isColission()&&!sprites.get(j).isColission()){
                                       if ((sprites.get(i).getxSpeed()>0 && sprites.get(j).getxSpeed()>0) ||(sprites.get(i).getxSpeed()<0 && sprites.get(j).getxSpeed()<0)
                                               || (sprites.get(i).getySpeed()>0 && sprites.get(j).getySpeed()>0)||(sprites.get(i).getySpeed()<0 && sprites.get(j).getySpeed()<0)){

                                           speedAuxX = sprites.get(i).getxSpeed();
                                           speedAuxY = sprites.get(i).getySpeed();
                                           sprites.get(i).setxSpeed(sprites.get(j).getxSpeed());
                                           sprites.get(i).setySpeed(sprites.get(j).getySpeed());
                                           sprites.get(j).setxSpeed(speedAuxX);
                                           sprites.get(j).setySpeed(speedAuxY);

                                       }else{
                                           sprites.get(j).setxSpeed(0-sprites.get(j).getxSpeed());
                                           sprites.get(j).setySpeed(0-sprites.get(j).getySpeed());
                                           sprites.get(i).setxSpeed(0-sprites.get(i).getxSpeed());
                                           sprites.get(i).setySpeed(0-sprites.get(i).getySpeed());
                                       }
                                       sprites.get(i).setColission(true);
                                       sprites.get(j).setColission(true);
                                   }else{
                                       sprites.get(i).setColission(false);
                                       sprites.get(j).setColission(false);

                                   }


                        }else{
                            if (sprites.get(i).isColission()&&sprites.get(j).isColission()){
                                sprites.get(i).setColission(false);
                                sprites.get(j).setColission(false);
                            }

                        }
                    }


                }

            }


        }


    }

    public static MarineSprites getMarineSprites() {
        return marineSprites;
    }

    public static void setMarineSprites(MarineSprites marineSprites) {
        Sprite.marineSprites = marineSprites;
    }


    public void changeSprite(){
        if (movement >= 1&&movement!=10){
            movement ++;

        }if (movement == 10){
            movement =1;
        }
    }
    public  void walk(){
        if (x != xPointDestiny && xPointDestiny !=-1){
            x+= xSpeed;
        }else{
            xPointDestiny = -1;
            xSpeed=0;
        }
        if (y != yPointDestiny && yPointDestiny != -1){
            y+=ySpeed;
        }else{
            yPointDestiny = -1;
            ySpeed = 0;
        }

    }
    public void setEndPoints(int xEnd,int yEnd){
        this.xPointDestiny = xEnd;
        this.yPointDestiny = yEnd;
        if (xEnd<x){
            xSpeed = -1*Math.abs(ACCEL);
        }else{
            xSpeed = Math.abs(ACCEL);
        }
        if (yEnd<y){
            ySpeed = -1*Math.abs(ACCEL);
        }else {
            ySpeed = Math.abs(ACCEL);
        }


    }

    public int getxPointDestiny() {
        return xPointDestiny;
    }

    public void setxPointDestiny(int xPointDestiny) {
        this.xPointDestiny = xPointDestiny;
    }

    public int getyPointDestiny() {
        return yPointDestiny;
    }

    public void setyPointDestiny(int yPointDestiny) {
        this.yPointDestiny = yPointDestiny;
    }
}
