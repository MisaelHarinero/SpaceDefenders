package model;

public class Marine extends  Sprite {
    public Marine() {
        super();
    }

    @Override
    public void walk() {
        super.walk();
        setSpritesForMovement();
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
        }


        changeSprite();




    }
    public void acctions() {

    }
}
