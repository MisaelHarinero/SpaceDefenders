package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PantallaJuego extends JPanel implements MouseListener, MouseMotionListener, Runnable, ComponentListener {

    private IGameScreen gameScreen;


    public PantallaJuego() {

        this.gameScreen = new PanelJuego(this);
        this.gameScreen.startComponents();
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
}
