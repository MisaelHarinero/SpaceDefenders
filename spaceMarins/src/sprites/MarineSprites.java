package sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MarineSprites {
    private BufferedImage mWs;
    private BufferedImage mWp;
    private BufferedImage mWml;
    private BufferedImage mWmr;
    private BufferedImage mDs;
    private BufferedImage mDp;
    private BufferedImage mDml;
    private BufferedImage mDmr;
    private BufferedImage mSs;
    private BufferedImage mSp;
    private BufferedImage mSml;
    private BufferedImage mSmr;
    private BufferedImage mXs;
    private BufferedImage mXp;
    private BufferedImage mXml;
    private BufferedImage mXmr;
    private BufferedImage mEs;
    private BufferedImage mEp;
    private BufferedImage mEml;
    private BufferedImage mEmr;
    public MarineSprites(int width, int height){
        chargeSprites(width,height);



    }
    public void chargeSprites(int width, int height){
        String letras [] = {"W","D","S","X","E"};
        String rutaFin[] = {"s.png","p.png","ml.png","mr.png"};
        String rutaIni = "resource/marine/";
        BufferedImage bf = null;
        for (int i = 0; i <letras.length ; i++) {
            for (int j = 0; j <rutaFin.length ; j++) {
                bf = cargarImgRescal(rutaIni+"m"+letras[i]+rutaFin[j],width,height);
                switch (letras[i]){
                    case "W":{
                        switch (j){
                            case 0:{
                                this.mWs = bf;
                                break;
                            }
                            case 1:{
                                this.mWp = bf;
                                break;
                            }
                            case 2:{
                                this.mWml = bf;
                                break;
                            }
                            case 3:{
                                this.mWmr = bf;
                                break;
                            }

                        }

                        break;
                    }
                    case "D":{
                        switch (j){
                            case 0:{
                                this.mDs = bf;
                                break;
                            }
                            case 1:{
                                this.mDp = bf;
                                break;
                            }
                            case 2:{
                                this.mDml = bf;
                                break;
                            }
                            case 3:{
                                this.mDmr = bf;
                                break;
                            }

                        }
                        break;
                    }
                    case "S":{
                        switch (j){
                            case 0:{
                                this.mSs = bf;
                                break;
                            }
                            case 1:{
                                this.mSp = bf;
                                break;
                            }
                            case 2:{
                                this.mSml = bf;
                                break;
                            }
                            case 3:{
                                this.mSmr = bf;
                                break;
                            }

                        }
                        break;
                    }
                    case "X":{
                        switch (j){
                            case 0:{
                                this.mXs = bf;
                                break;
                            }
                            case 1:{
                                this.mXp = bf;
                                break;
                            }
                            case 2:{
                                this.mXml = bf;
                                break;
                            }
                            case 3:{
                                this.mXmr = bf;
                                break;
                            }

                        }
                        break;
                    }
                    case "E":{
                        switch (j){
                            case 0:{
                                this.mEs = bf;
                                break;
                            }
                            case 1:{
                                this.mEp = bf;
                                break;
                            }
                            case 2:{
                                this.mEml = bf;
                                break;
                            }
                            case 3:{
                                this.mEmr = bf;
                                break;
                            }

                        }
                        break;
                    }
                }
            }

        }
    }
    public BufferedImage cargarImgRescal(String rute, int width, int height){
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(rute));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bf != null){
            BufferedImage l = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = l.createGraphics();
            g2d.drawImage(bf.getScaledInstance(width,height,BufferedImage.SCALE_SMOOTH),0,0,null);
            g2d.dispose();
            bf = l;
        }else{
            bf = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            Graphics g = bf.getGraphics();
            g.setColor(Color.GREEN);
            g.fillRect(0,0,width,height);
            g.dispose();
        }


        return  bf;
    }

    public BufferedImage getmWs() {
        return mWs;
    }

    public void setmWs(BufferedImage mWs) {
        this.mWs = mWs;
    }

    public BufferedImage getmWp() {
        return mWp;
    }

    public void setmWp(BufferedImage mWp) {
        this.mWp = mWp;
    }

    public BufferedImage getmWml() {
        return mWml;
    }

    public void setmWml(BufferedImage mWml) {
        this.mWml = mWml;
    }

    public BufferedImage getmWmr() {
        return mWmr;
    }

    public void setmWmr(BufferedImage mWmr) {
        this.mWmr = mWmr;
    }

    public BufferedImage getmDs() {
        return mDs;
    }

    public void setmDs(BufferedImage mDs) {
        this.mDs = mDs;
    }

    public BufferedImage getmDp() {
        return mDp;
    }

    public void setmDp(BufferedImage mDp) {
        this.mDp = mDp;
    }

    public BufferedImage getmDml() {
        return mDml;
    }

    public void setmDml(BufferedImage mDml) {
        this.mDml = mDml;
    }

    public BufferedImage getmDmr() {
        return mDmr;
    }

    public void setmDmr(BufferedImage mDmr) {
        this.mDmr = mDmr;
    }

    public BufferedImage getmSs() {
        return mSs;
    }

    public void setmSs(BufferedImage mSs) {
        this.mSs = mSs;
    }

    public BufferedImage getmSp() {
        return mSp;
    }

    public void setmSp(BufferedImage mSp) {
        this.mSp = mSp;
    }

    public BufferedImage getmSml() {
        return mSml;
    }

    public void setmSml(BufferedImage mSml) {
        this.mSml = mSml;
    }

    public BufferedImage getmSmr() {
        return mSmr;
    }

    public void setmSmr(BufferedImage mSmr) {
        this.mSmr = mSmr;
    }

    public BufferedImage getmXs() {
        return mXs;
    }

    public void setmXs(BufferedImage mXs) {
        this.mXs = mXs;
    }

    public BufferedImage getmXp() {
        return mXp;
    }

    public void setmXp(BufferedImage mXp) {
        this.mXp = mXp;
    }

    public BufferedImage getmXml() {
        return mXml;
    }

    public void setmXml(BufferedImage mXml) {
        this.mXml = mXml;
    }

    public BufferedImage getmXmr() {
        return mXmr;
    }

    public void setmXmr(BufferedImage mXmr) {
        this.mXmr = mXmr;
    }

    public BufferedImage getmEs() {
        return mEs;
    }

    public void setmEs(BufferedImage mEs) {
        this.mEs = mEs;
    }

    public BufferedImage getmEp() {
        return mEp;
    }

    public void setmEp(BufferedImage mEp) {
        this.mEp = mEp;
    }

    public BufferedImage getmEml() {
        return mEml;
    }

    public void setmEml(BufferedImage mEml) {
        this.mEml = mEml;
    }

    public BufferedImage getmEmr() {
        return mEmr;
    }

    public void setmEmr(BufferedImage mEmr) {
        this.mEmr = mEmr;
    }
}
