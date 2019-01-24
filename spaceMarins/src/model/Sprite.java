package model;

import sprites.MarineSprites;
import sprites.SpriteLife;
import sprites.ZergsSprites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite {
    private final static int MAX_WIDTH_HEIGHT = 40;
    private final static int INITIAL_POINT_X = 500;
    private final static int INITIAL_POINT_Y = 500;
    private final static int ATACK_RATIO = 100;
    private final static  int ACCEL = 1;
    private BufferedImage canvas;
    private int width;
    private int height;
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;
    private static  MarineSprites marineSprites;
    private static ZergsSprites zergSprites;
    private boolean colission;
    private int movement;
    private int xPointDestiny;
    private int yPointDestiny;
    private Sprite enemy;
    private String state;
    private int life;
    private SpriteLife lifeBar;

    public Sprite() {
        this.canvas = Sprite.getMarineSprites().getmSs();
        if (this.canvas == null){
            generarCuadradoNegro();

        }
        state = "S";
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
    public Sprite(int xFinally, int yFinally, int maxWith, int maxHeight,int num) {
        this.canvas = Sprite.getZergSprites().getImages()[8][0];
        if (this.canvas == null){
            generarCuadradoNegro();

        }
        state = "W";
        this.movement = 2;
        this.width = MAX_WIDTH_HEIGHT;
        this.height = MAX_WIDTH_HEIGHT;
        pointStart(maxWith, maxHeight);
        this.xSpeed = ACCEL;
        this.ySpeed = ACCEL;
        setEndPoints(xFinally,yFinally);
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
    public Sprite( int width, int height, int x, int y) {
        this.canvas = null;
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
        if (yPointDestiny == -1 && xPointDestiny == -1){
        }
        this.getLifeBar().actualizarCordenadas(getX(),getY());
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
        if (yPointDestiny == -1 && xPointDestiny == -1){
            this.state = "S";
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
        this.state = "W";


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



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Sprite getEnemy() {
        return enemy;
    }

    public void setEnemy(Sprite enemy) {
        this.enemy = enemy;
    }
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public SpriteLife getLifeBar() {
        return lifeBar;
    }

    public void setLifeBar(SpriteLife lifeBar) {
        this.lifeBar = lifeBar;
    }

    public void atackEnemy(){
        if (enemy != null){
            if (this.enemy.getState().equals("M")){
                this.enemy = null;
                this.state = "S";

            }else {
                if (this.enemy.getLife() <= 0 && !enemy.getState().equals("M")) {
                    this.enemy.setState("M");
                    this.state = "S";
                    this.enemy = null;
                } else {
                  if (!enemy.getState().equals("B")){
                      this.enemy.setLife(this.enemy.getLife() - 1);
                  }
                }
            }
        }else{
            state = "S";
        }

    }

    public static ZergsSprites getZergSprites() {
        return zergSprites;
    }

    public static void setZergSprites(ZergsSprites zergSprites) {
        Sprite.zergSprites = zergSprites;
    }

    public void pointStart(int maxWith, int maxHeight) {
        switch ((int) Math.floor(Math.random() * 4)) {
            case 0: {
                setX((int) Math.floor(Math.random() * (maxWith-10)));
                setY(10);
                break;
            }
            case 1: {
                setX((int) Math.floor(Math.random() * maxWith));
                setY(maxHeight-10);
                break;
            }
            case 2: {
                setX(10);
                setY((int) Math.floor(Math.random() * (maxWith-10)));

                break;
            }
            case 3: {
                setX(maxWith-10);
                setY((int) Math.floor(Math.random() * (maxWith-10)));


                break;
            }
        }
    }
}
