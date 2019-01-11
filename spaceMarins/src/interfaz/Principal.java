package interfaz;

import java.awt.*;

public class Principal {

	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.invocar();
		});
		
	}

}
