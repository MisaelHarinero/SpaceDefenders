package interfaz;

import java.awt.*;

/**
 * @author Misael Harinero
 * @version 1.0
 * Juego basado en Starcraft 1 con unas mecanicas mas sencillsa y faciles para el usuario,
 * pero mas caotico que el original.
 */
public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.invocar();
		});
		
	}

}
