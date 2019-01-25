package interfaz;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface IGameScreen {

    public  void  startComponents();
    public void paintElements(Graphics g);
    public void exeFrame();
    public void mouseMove(MouseEvent e);
    public void mouseClick(MouseEvent e);
    public void resizeScreen(ComponentEvent e);
    public void onKeyPress(KeyEvent e);


}
