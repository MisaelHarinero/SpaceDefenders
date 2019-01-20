package model;

import sprites.SpriteLife;

import java.util.ArrayList;

public class Marine extends  Sprite {
    public final static int MAX_LIFE = 20;
    private int timeRecharge;
    private final static int  TIME_RECHARGE = 5;
    private final static int ATACK_RATIO = 100;

    public Marine() {
        super();
        setLife(400);
        setLifeBar(new SpriteLife(getLife()/20,getX(),getY()));
    }

    @Override
    public void walk() {
        super.walk();
        setSpritesForMovement();
        this.getLifeBar().actualizarCordenadas(getX(),getY());
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
    public void changeCombatSprite(){
        if (getX()>getEnemy().getX() && getY()> getEnemy().getY()){
            setCanvas(getMarineSprites().getmQp());
        }
        if (getX()>getEnemy().getX() && getY()== getEnemy().getY()){
            setCanvas(getMarineSprites().getmAp());
        }
        if (getX()>getEnemy().getX() && getY()< getEnemy().getY()){
            setCanvas(getMarineSprites().getmZp());
        }
        if (getX()==getEnemy().getX() && getY()<getEnemy().getY()){
            setCanvas(getMarineSprites().getmWp());
        }
        if (getX()==getEnemy().getX() && getY()>getEnemy().getY()){
            setCanvas(getMarineSprites().getmSp());
        }
        if (getX()<getEnemy().getX() && getY()==getEnemy().getY()){
            setCanvas(getMarineSprites().getmDp());
        }
        if (getX()<getEnemy().getX() && getY()>getEnemy().getY()){
            setCanvas(getMarineSprites().getmEp());
        }
        if (getX()<getEnemy().getX() && getY()<getEnemy().getY()){
            setCanvas(getMarineSprites().getmXp());
        }

    }
    public void searchEnemy(ArrayList<Zerg>enemies){
        for (int i = 0; i < enemies.size(); i++) {

            if (Math.abs(getX()-enemies.get(i).getX())<ATACK_RATIO && Math.abs(getY()-enemies.get(i).getY())<ATACK_RATIO){
                if (getEnemy() == null&&!enemies.get(i).getState().equals("M")){
                    setState("A");
                    setEnemy(enemies.get(i));
                    getEnemy().setState("A");
                    changeCombatSprite();
                }
            }
        }
    }
    public void doAction(ArrayList<Zerg> enemies){
        switch (getState()){
            case "S":{
                setEnemy(null);
                setCanvas(getMarineSprites().getmSs());
                searchEnemy(enemies);
                break;
            }
            case "W":{
                walk();
                searchEnemy(enemies);
                break;
            }
            case "A":{
                if (timeRecharge == -1){
                    getLifeBar().recibirDaño(this.getLife()/20);
                    atackEnemy();
                }else{
                    setState("R");
                    timeRecharge = 0;
                }
                break;
            }
            case "R":{
                if (timeRecharge>=0 &&timeRecharge<5){
                        timeRecharge++;
                    getLifeBar().recibirDaño(this.getLife()/20);

                }else{
                    timeRecharge = -1;
                    setState("A");
                }
            }
            case "M":{
                break;
            }
        }


    }



}
