package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author  MisaelHarinero
 * Clase que hereda de JPanel, que va ha a unificar las pantallas de juego y en ella se van a dibujar todo los componentes de
 * nuestra interfaz
 */
public class PantallaJuego extends JPanel implements MouseListener, MouseMotionListener, Runnable, ComponentListener, KeyListener {

    private IGameScreen gameScreen;
    private JFrame ventana;


    public PantallaJuego(JFrame ventana) {

        this.gameScreen = new InitialScreen(this);
        this.gameScreen.startComponents();
        this.setFocusable(true);
        this.ventana = ventana;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        Thread th  = new Thread(this);
        th.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.gameScreen.paintElements(g);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.gameScreen.resizeScreen(e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.gameScreen.mouseClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void run() {
        while (true){
            this.gameScreen.exeFrame();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.gameScreen.onKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public IGameScreen getGameScreen() {
        return gameScreen;
    }

    public void setGameScreen(IGameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }
    public void exit(){
        this.ventana.dispose();
    }
}
