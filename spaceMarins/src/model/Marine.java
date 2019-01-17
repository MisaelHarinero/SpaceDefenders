package model;

import sprites.SpriteLife;

import java.util.ArrayList;

public class Marine extends  Sprite {
    public final static int MAX_LIFE = 20;
    private int life;
    private SpriteLife lifeBar;
    private final static int ATACK_RATIO = 50;
    public Marine() {
        super();
        this.life = MAX_LIFE;
        this.lifeBar = new SpriteLife(this.life,getX(),getY());
    }

    @Override
    public void walk() {
        super.walk();
        setSpritesForMovement();
        this.lifeBar.actualizarCordenadas(getX(),getY());
    }

    public  void setSpritesForMovement(){
        if (getxPointDestiny() == -1 && getyPointDestiny() ==-1){
                setCanvas(getMarineSprites().getmSs());
        }else{
            if (getxSpeed()>0&&getySpeed()>0&&getMovement()==1){
                setCanvas(getMarineSprites().getmXml());
            }
            if (getxSpeed()>0&&getySpeed()>0&&getMovement()==6){
                setCanvas(getMarineSprites().getmXmr());

            }
            if (getxSpeed()>0&&getySpeed()==0&&getMovement()==1){
                setCanvas(getMarineSprites().getmDml());

            }
            if (getxSpeed()>0&&getySpeed()==0&&getMovement()==6){
                setCanvas(getMarineSprites().getmDmr());

            }
            if (getxSpeed()>0&&getySpeed()<0&&getMovement()==1){
                setCanvas( getMarineSprites().getmEml());

            }
            if (getxSpeed()>0&&getySpeed()<0&&getMovement()==6){
                setCanvas(getMarineSprites().getmEmr());

            }
            if (getxSpeed()==0&&getySpeed()<0&&getMovement()==1){
                setCanvas(getMarineSprites().getmWml());

            }
            if (getxSpeed()==0&&getySpeed()<0&&getMovement()==6){
                setCanvas(getMarineSprites().getmWmr());

            }
            if (getxSpeed()==0&&getySpeed()>0&&getMovement()==1){
                setCanvas(getMarineSprites().getmSml());

            }
            if (getxSpeed()==0&&getySpeed()>0&&getMovement()==6){
                setCanvas(getMarineSprites().getmSmr());

            }
            if (getxSpeed()<0&&getySpeed()<0&&getMovement()==1){
                setCanvas(getMarineSprites().getmQml());

            }
            if (getxSpeed()<0&&getySpeed()<0&&getMovement()==6){
                setCanvas(getMarineSprites().getmQmr());

            }
            if (getxSpeed()<0&&getySpeed()==0&&getMovement()==1){
                setCanvas(getMarineSprites().getmAml());

            }
            if (getxSpeed()<0&&getySpeed()==0&&getMovement()==6){
                setCanvas(getMarineSprites().getmAmr());

            }

            if (getxSpeed()<0&&getySpeed()>0&&getMovement()==1){
                setCanvas(getMarineSprites().getmZml());

            }
            if (getxSpeed()<0&&getySpeed()>0&&getMovement()==6){
                setCanvas(getMarineSprites().getmZmr());

            }
        }

        changeSprite();

    }
    public void acctions() {

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
    public void searchEnemy(ArrayList<Zerg>enemies){
        for (int i = 0; i < enemies.size(); i++) {

            if (Math.abs(getX()-enemies.get(i).getX())<ATACK_RATIO && Math.abs(getY()-enemies.get(i).getY())<ATACK_RATIO){

            }
        }
    }
}
