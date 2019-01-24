package interfaz;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {
	private JFrame ventana;
	private PantallaJuego panel;

	public VentanaPrincipal() {
		this.ventana = new JFrame("Ejercicio01T9");
		this.ventana.setBounds(100, 80, 1600, 900);
		this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void invocar() {
		this.ventana.setVisible(true);
		this.ventana.setLayout(new GridLayout());
		incializarComponentes();
	}

	public void incializarComponentes() {
		this.panel = new PantallaJuego(this.ventana);
		this.ventana.add(this.panel);
	}
}
