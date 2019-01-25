package model;

import sprites.SpriteLife;

import java.util.ArrayList;

/**
 * @author Misael Harinero
 * Clase que hereda de de Sprite, y que modifica sus atributos para actuar como un Marine Espacial
 * En la que tenemos una tabla de estados dependiendo de los estados realizara una accion :
 *     TABLA DE ACCION :
 *   -----------------------
 *      W    |    Cuando se encuentra en estado W nuestro personaje se mueve
 *      S    |    Cuando se encuentra en estado S nuestro personaje se queda parado en el sitio
 *      A    |    Cuadno se encuentra en estado A nuestro personaje esta atacando
 *      M    |    Cuando se encuentra en estado M nuestro personaje esta muerto
 *      R    |    Cuando se encuentra en estad  R nuestro personaje esta recargando
 *
 */
public class Marine extends  Sprite {
    public final static int MAX_LIFE = 400;
    private int timeRecharge;
    private final static int  TIME_RECHARGE = 5;
    private final static int ATACK_RATIO = 100;

    public Marine() {
        super();
        setLife(MAX_LIFE);
        setLifeBar(new SpriteLife(getLife()/20,getX(),getY()));
    }

    @Override
    public void walk() {
        super.walk();
        setSpritesForMovement();
        this.getLifeBar().actualizarCordenadas(getX(),getY());
    }

    /**
     * Metodo en el sprite de movimiento de nuestro zerg dependiendo de su velocidad y de su paso
     */
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

    /**
     * Metodo en elque dependiendo de la direccion de nuestro enemigo modificamos el sprite de ataque de un sitio a otro
     */
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

    /**
     * MEtodo en el que comprobamos si nuestro zerg esta en rango de atacar a un enemigo o no
     */
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

    /**
     * Metodo en el que dependiendo en que estado se encuentre nuestro personaje realizara una accion u otra
     * @param enemies
     */
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
                    getLifeBar().recibirDano(this.getLife()/20);
                    atackEnemy();
                }else{
                    setState("R");
                    timeRecharge = 0;
                }
                break;
            }
            case "R":{
                if (timeRecharge>=0 &&timeRecharge<TIME_RECHARGE){
                        timeRecharge++;
                    getLifeBar().recibirDano(this.getLife()/20);

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
