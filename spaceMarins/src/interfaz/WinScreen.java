package interfaz;

import sprites.ResourcesGetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * @author  MisaelHarinero
 * Clase que nos muestra la pantalla de Victoria
 * Nos deja elegir entre dos opciones  que capturaremos con un evento de teclado:
 * Si pulamos la tecla :
 *          M : Volveremos al Menu
 *          E : Cerraremos el Juego
 */
public class WinScreen implements IGameScreen{
    private PantallaJuego pantalla;
    private BufferedImage background;
    public WinScreen(PantallaJuego pantalla){
        this.pantalla = pantalla;
        this.pantalla.setBackground(Color.BLACK);
    }

    @Override
    public void startComponents() {
       this.pantalla.setVisible(false);
        this.background = ResourcesGetter.getBackWin();
        this.pantalla.setLayout(new GridBagLayout());
        this.pantalla.repaint();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx= 0;
        constraints.gridy=0;
        JLabel letters = new JLabel();
        letters.setIcon(new ImageIcon(ResourcesGetter.getLettersWin()));
        this.pantalla.add(letters,constraints);
        this.pantalla.setVisible(true);

    }

    @Override
    public void paintElements(Graphics g) {
        g.drawImage(this.background,0,0,pantalla.getWidth(),pantalla.getHeight(),null);
    }

    @Override
    public void exeFrame() {
        this.pantalla.repaint();
    }

    @Override
    public void mouseMove(MouseEvent e) {

    }

    @Override
    public void mouseClick(MouseEvent e) {

    }

    @Override
    public void resizeScreen(ComponentEvent e) {

    }

    @Override
    public void onKeyPress(KeyEvent e) {
        switch (e.getKeyCode()){
            case  KeyEvent.VK_M:{
                InitialScreen initialScreen =  new InitialScreen(this.pantalla);
                initialScreen.startComponents();
                this.pantalla.setGameScreen(initialScreen);
                break;
            }
            case KeyEvent.VK_E:{
                this.pantalla.exit();
                System.exit(0);
                break;
            }
        }
    }
}
