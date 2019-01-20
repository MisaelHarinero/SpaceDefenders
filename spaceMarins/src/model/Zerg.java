package model;

import sprites.SpriteLife;

import java.util.ArrayList;

public class Zerg extends  Sprite {
    private int turnAbility;
    private final  static int ATACK_RATIO = 100;

    public Zerg(int maxWith, int maxHeight){
        super(0);
        this.turnAbility = 0;
        setLife(100);
        setLifeBar(new SpriteLife(getLife()/5,getX(),getY()));
        pointStart(maxWith,maxHeight);
    }


    public void doAction(int screenWeight, int screenHeight, ArrayList<Marine>enemies) {
        switch (getState()){
            case "S":{
                setState("W");
                break;
            }

            case "W":{
                move(screenWeight,screenHeight);
                sEnemy(enemies);
                break;
            }
            case "A":{
                getLifeBar().recibirDaño(getLife()/5);
                if (getLife()<50&&turnAbility!=-1){
                    setState("B");
                }else{
                    atackEnemy();
                }
                break;
            }
            case "B":{
                getLifeBar().recibirDaño(getLife()/5);
                doAbility();

                break;
            }
            case "M":{

                break;
            }


        }
    }
    public void doAbility(){
        if (turnAbility >= 0 &&  turnAbility <30){
            //CAMBIAMOS EL SPRITE
            turnAbility++;
            setLife(getLife()+1);
        }else{
            turnAbility = -1;
            setState("A");
        }



    }
    public void sEnemy(ArrayList<Marine>enemies){
        for (int i = 0; i < enemies.size(); i++) {

            if (Math.abs(getX()-enemies.get(i).getX())<ATACK_RATIO && Math.abs(getY()-enemies.get(i).getY())<ATACK_RATIO){
                if (getEnemy() == null&&!enemies.get(i).getState().equals("M")){
                    setState("A");
                    setEnemy(enemies.get(i));
                    getEnemy().setState("A");
                }
            }
        }
    }
    public void pointStart(int maxWith, int maxHeight){
        switch ((int)Math.floor(Math.random()*4)){
            case 0:{
                setX((int)Math.floor(Math.random()*maxWith));
                setY(0);
                break;
            }
            case 1:{
                setX((int)Math.floor(Math.random()*maxWith));
                setY(maxHeight);
                break;
            }
            case 2:{
                setX(0);
                setY((int)Math.floor(Math.random()*maxWith));

                break;
            }
            case 3:{
                setX(maxWith);
                setY((int)Math.floor(Math.random()*maxWith));


                break;
            }
        }
    }

}
