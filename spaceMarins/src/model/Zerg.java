package model;

import sprites.SpriteLife;

import java.util.ArrayList;

/**
 * @author Misael Harinero
 * Clase que hereda de de Sprite, y que modifica sus atributos para actuar como un Zerg
 * En la que tenemos una tabla de estados dependiendo de los estados realizara una accion :
 *     TABLA DE ACCION :
 *   -----------------------
 *      W    |    Cuando se encuentra en estado W nuestro personaje se mueve
 *      S    |    Cuando se encuentra en estado S nuestro personaje se queda parado en el sitio
 *      A    |    Cuadno se encuentra en estado A nuestro personaje esta atacando
 *      M    |    Cuando se encuentra en estado M nuestro personaje esta muerto
 *      B    |    Cuando se encuentra en estad  B nuestro personaje estara realizando su habilidad especial
 *
 */
public class Zerg extends Sprite {
    private int turnAbility;
    private final static int ATACK_RATIO = 100;
    private Base base;

    public Zerg(int maxWith, int maxHeight,Base base) {
        super(base.getX()+((int)Math.floor(Math.random()*base.getWidth())),base.getY()+(+((int)Math.floor(Math.random()*base.getHeight()))),maxWith,maxHeight,0);
        this.turnAbility = 0;
        setLife(100);
        this.base = base;
        setLifeBar(new SpriteLife(getLife() / 5, getX(), getY()));

    }


    /**
     * Metodo en el que dependiendo en que estado se encuentre nuestro personaje realizara una accion u otra
     * @param screenWeight
     * @param screenHeight
     * @param enemies
     */
    public void doAction(int screenWeight, int screenHeight, ArrayList<Marine> enemies []) {
        switch (getState()) {
            case "S": {
                setState("W");
                break;
            }

            case "W": {
                move(screenWeight, screenHeight);
                setSpritesForMovement();
                sEnemy(enemies);
                sBase();
                break;
            }
            case "A": {
                getLifeBar().recibirDaño(getLife() / 5);
                if (getLife() < 50 && turnAbility != -1) {
                    setState("B");
                } else {
                    atackEnemy();
                }
                break;
            }
            case "B": {
                getLifeBar().recibirDaño(getLife() / 5);
                doAbility();

                break;
            }
            case "M": {

                break;
            }


        }
    }

    /**
     * Metodo en el que el zerg realiza su habilidad de invulnerabilidad y sanacion, comprobamos que se esta entre los limites, y si no lo
     * ponemos en modo ataque
     */
    public void doAbility() {
        if (turnAbility >= 0 && turnAbility < 30) {
            changeAbilityImage();
            turnAbility++;
            setLife(getLife() + 1);
        } else {
            turnAbility = -1;
            setState("A");
        }


    }

    /**
     * MEtodo en el que comprobamos si nuestro zerg esta en rango de atacar a la base o no
     */
    public void sBase(){
        if (getEnemy() == null){
            if (Math.abs(getX() - (base.getX()+base.getWidth()/2)) < (ATACK_RATIO-80)+base.getWidth() && Math.abs(getY() -(base.getY()+base.getWidth()/2)) < (ATACK_RATIO-80)+base.getHeight()){
                setState("A");
                setEnemy(base);
            }
        }

    }
    /**
     * MEtodo en el que comprobamos si nuestro zerg esta en rango de atacar a un enemigo o no
     */
    public void sEnemy(ArrayList<Marine> enemies []) {
        for (int i = 0; i < enemies.length; i++) {
            for (int j = 0; j < enemies[i].size(); j++) {

                if (Math.abs(getX() - enemies[i].get(j).getX()) < ATACK_RATIO && Math.abs(getY() - enemies[i].get(j).getY()) < ATACK_RATIO) {
                    if (getEnemy() == null && !enemies[i].get(j).getState().equals("M")) {
                        setState("A");
                        setEnemy(enemies[i].get(j));
                        getEnemy().setState("A");
                    }
                }
            }
        }
    }


    /**
     * Metodo en el que modificamos los sprites de el periodo de habilidad para que de el efecto como que se protege en un huevo
     */
    public void changeAbilityImage() {
        switch (this.turnAbility) {
            case 1: {
                setCanvas(getZergSprites().getAbilities()[0]);
                break;
            }
            case 7: {
                setCanvas(getZergSprites().getAbilities()[1]);

                break;
            }

            case 14: {
                setCanvas(getZergSprites().getAbilities()[2]);

                break;
            }

            case 21: {
                setCanvas(getZergSprites().getAbilities()[3]);

                break;
            }

            case 25: {
                setCanvas(getZergSprites().getAbilities()[4]);

                break;
            }

            case 28: {
                setCanvas(getZergSprites().getAbilities()[8]);

                break;
            }
        }
    }

    /**
     * MEtodo en el que modificamos los sprites de nuestro zerg dependiendo su velocidad
     */
    public void setSpritesForMovement() {

        if (getxSpeed() > 0 && getySpeed() > 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[7][2]);
        }
        if (getxSpeed() > 0 && getySpeed() > 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[7][3]);
        }
        if (getxSpeed() > 0 && getySpeed() > 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[7][5]);
        }
        if (getxSpeed() > 0 && getySpeed() == 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[4][2]);

        }
        if (getxSpeed() > 0 && getySpeed() == 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[4][3]);

        }
        if (getxSpeed() > 0 && getySpeed() == 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[4][5]);
        }
        if (getxSpeed() > 0 && getySpeed() < 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[2][2]);
        }
        if (getxSpeed() > 0 && getySpeed() < 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[2][3]);
        }
        if (getxSpeed() > 0 && getySpeed() < 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[2][5]);
        }
        if (getxSpeed() == 0 && getySpeed() < 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[0][2]);
        }
        if (getxSpeed() == 0 && getySpeed() < 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[0][3]);
        }
        if (getxSpeed() == 0 && getySpeed() < 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[0][5]);
        }
        if (getxSpeed() == 0 && getySpeed() > 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[8][2]);
        }
        if (getxSpeed() == 0 && getySpeed() > 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[8][3]);
        }
        if (getxSpeed() == 0 && getySpeed() > 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[8][5]);
        }
        if (getxSpeed() < 0 && getySpeed() < 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[2][2]);
        }
        if (getxSpeed() < 0 && getySpeed() < 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[2][3]);
        }
        if (getxSpeed() < 0 && getySpeed() < 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[2][5]);
        }
        if (getxSpeed() < 0 && getySpeed() == 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[0][2]);
        }
        if (getxSpeed() < 0 && getySpeed() == 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[0][3]);
        }
        if (getxSpeed() < 0 && getySpeed() == 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[0][5]);
        }

        if (getxSpeed() < 0 && getySpeed() > 0 && getMovement() == 1) {
            setCanvas(getZergSprites().getImages()[7][2]);
        }
        if (getxSpeed() < 0 && getySpeed() > 0 && getMovement() == 3) {
            setCanvas(getZergSprites().getImages()[7][3]);
        }
        if (getxSpeed() < 0 && getySpeed() > 0 && getMovement() == 6) {
            setCanvas(getZergSprites().getImages()[7][5]);

        }

        changeSprite();

    }

}
