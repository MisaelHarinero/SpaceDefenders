package sprites;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Misael Harinero
 * Clase en la cual cargamos todas las imagenes necesarias para las pantallas de inicio y fin de juego
 */
public class ResourcesGetter {
    private static BufferedImage backInit;
    private static BufferedImage backWin;
    private static BufferedImage backFinish;
    private static BufferedImage lettersInit;
    private static BufferedImage lettersWin;
    private static BufferedImage lettersLoose;

    public final static String START_RUTE = "resource/screens/";

    public static void chargeBackgrounds() {
      backInit = chargeImages(START_RUTE+"start.jpg");
      backWin =  chargeImages(START_RUTE+"win.jpg");
      backFinish = chargeImages(START_RUTE+"loose.jpg");
      lettersInit = chargeImages(START_RUTE+"initLetters.png");
      lettersWin = chargeImages(START_RUTE+"winLetters.png");
      lettersLoose = chargeImages(START_RUTE+"looseLetters.png");
    }


    public static BufferedImage getBackInit() {
        return backInit;
    }

    public static void setBackInit(BufferedImage backInit) {
        ResourcesGetter.backInit = backInit;
    }

    public static BufferedImage getBackWin() {
        return backWin;
    }

    public static void setBackWin(BufferedImage backWin) {
        ResourcesGetter.backWin = backWin;
    }

    public static BufferedImage getBackFinish() {
        return backFinish;
    }

    public static void setBackFinish(BufferedImage backFinish) {
        ResourcesGetter.backFinish = backFinish;
    }

    public static BufferedImage chargeImages(String rute) {
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(rute));
        } catch (IOException e) {
            System.out.println(rute);
            e.printStackTrace();
        }

        return bf;
    }

    public static BufferedImage getLettersInit() {
        return lettersInit;
    }

    public static void setLettersInit(BufferedImage lettersInit) {
        ResourcesGetter.lettersInit = lettersInit;
    }

    public static BufferedImage getLettersWin() {
        return lettersWin;
    }

    public static void setLettersWin(BufferedImage lettersWin) {
        ResourcesGetter.lettersWin = lettersWin;
    }

    public static BufferedImage getLettersLoose() {
        return lettersLoose;
    }

    public static void setLettersLoose(BufferedImage lettersLoose) {
        ResourcesGetter.lettersLoose = lettersLoose;
    }
}
