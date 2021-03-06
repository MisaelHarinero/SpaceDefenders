package model;

import sprites.BaseSprites;
import sprites.SpriteLife;

/**
 * @author Misael Harinero
 * Clase que hereda de de Sprite, que actuara como base zeg que nuestros marines espaciales deberan
 * proteger con su vida
 */
public class Base extends Sprite {
    private  BaseSprites spriteBase;
    private  final static int DIMENSION = 200;
    private int secondsConstruction;
    public Base(int x, int y) {
        super(DIMENSION,DIMENSION,x-(DIMENSION/2),y-(DIMENSION/2));
        this.spriteBase = new BaseSprites();
        setCanvas(this.spriteBase.getSecuencesImages()[0]);
        this.secondsConstruction = 10;
        setState("S");
        setLife(4000);
        setLifeBar(new SpriteLife(getLife()/1000,getX(),getY()));
    }

    /**
     * Metodo en el que cargamos los sprites de construccion de la base para simular que esta se esta construyendo
     */
    public void changeSpriteBase(){
        switch (secondsConstruction){
            case 10:{
                setCanvas(this.spriteBase.getSecuencesImages()[0]);
                break;
            }

            case 7:{
                setCanvas(this.spriteBase.getSecuencesImages()[1]);
                setLife(8000);
                getLifeBar().setLife(8000/1000);
                break;
            }

            case 5:{
                setCanvas(this.spriteBase.getSecuencesImages()[2]);
                setLife(12000);
                getLifeBar().setLife(12000/1000);
                break;
            }

            case 3:{
                setCanvas(this.spriteBase.getSecuencesImages()[3]);
                setLife(16000);
                getLifeBar().setLife(16000/1000);
                break;
            }

            case 1:{
                setCanvas(this.spriteBase.getSecuencesImages()[4]);
                setLife(20000);
                getLifeBar().setLife(20000/1000);
                break;
            }

        }
    }

    /**
     * Comprobamos si nos estamos construyendo y actualizamos la barra de vida
     * @return : boolean Retorna true si nos han destruido nuestra base
     */
    public boolean doACtion(){
        if (secondsConstruction >=0){
            changeSpriteBase();
            getLifeBar().genBuffer();
        }else{
            getLifeBar().recibirDano(getLife()/1000);
        }



        return getLife()<=0;
    }

    public int getSecondsConstruction() {
        return secondsConstruction;
    }

    public void setSecondsConstruction(int secondsConstruction) {
        this.secondsConstruction = secondsConstruction;
    }
}
