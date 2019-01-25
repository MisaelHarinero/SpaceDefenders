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
 * Clase que nos muestra la pantalla Inicial
 * Nos deja elegir entre dos opciones  que capturaremos con un evento de teclado:
 * Si pulamos la tecla :
 *          P : Iniciar la Partida
 *          E : Cerraremos el Juego
 */
public class InitialScreen implements IGameScreen {
    private PantallaJuego pantalla;
    private BufferedImage background;
    public InitialScreen(PantallaJuego pantalla){
        this.pantalla = pantalla;

    }
    @Override
    public void startComponents() {
        this.pantalla.removeAll();
        ResourcesGetter.chargeBackgrounds();
        this.pantalla.setBackground(Color.BLACK);
        this.background = ResourcesGetter.getBackInit();
        this.pantalla.setLayout(new GridBagLayout());
        JLabel letters = new JLabel();
        letters.setIcon(new ImageIcon(ResourcesGetter.getLettersInit()));
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
            case  KeyEvent.VK_P:{
                this.pantalla.removeAll();
                PanelJuego panelJuego = new PanelJuego(pantalla);
                panelJuego.startComponents();
                this.pantalla.setGameScreen(panelJuego);
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
