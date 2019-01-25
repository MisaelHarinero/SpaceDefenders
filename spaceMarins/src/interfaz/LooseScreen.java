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
 * Clase que nos muestra la pantalla de Derrota
 * Nos deja elegir entre dos opciones  que capturaremos con un evento de teclado:
 * Si pulamos la tecla :
 *          M : Volveremos al Menu
 *          E : Cerraremos el Juego
 */
public class LooseScreen implements IGameScreen{
    private PantallaJuego pantalla;
    private BufferedImage background;
    public LooseScreen(PantallaJuego pantalla){
        this.pantalla = pantalla;
    }

    @Override
    public void startComponents() {
        this.pantalla.removeAll();
        this.pantalla.setBackground(Color.BLACK);
        this.background = ResourcesGetter.getBackFinish();
        this.pantalla.setLayout(new GridBagLayout());
        JLabel letters = new JLabel();
        letters.setIcon(new ImageIcon(ResourcesGetter.getLettersLoose()));
        this.pantalla.add(letters);
        this.pantalla.revalidate();
        this.pantalla.repaint();


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
