package interfaz;

import javax.swing.*;
import java.awt.*;
import logica.Juego;
import logica.Sonido;

public class PantallaFinal {
	private JFrame frame;
	private JLabel labelTurnos;
	private JLabel labelRecord;

	public PantallaFinal(String jugador, int turnos) { // Le paso el jugador y la cantidad de turnos
		frame = new JFrame("Locura Cromática - ¡GANASTE!");
		frame.getContentPane().setBackground(new Color(45, 45, 47));
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		// Ganaste
		JLabel labelVictoria = new JLabel("¡Felicidades " + jugador + " Ganaste!");
		labelVictoria.setForeground(new Color(255, 255, 255));
		labelVictoria.setFont(new Font("Arial", Font.BOLD, 24));
		labelVictoria.setBounds(43, 91, 404, 43);
		frame.getContentPane().add(labelVictoria);

		// Turnos
		labelTurnos = new JLabel("Cantidad de Turnos: " + turnos);
		labelTurnos.setFont(new Font("Arial", Font.PLAIN, 18));
		labelTurnos.setForeground(new Color(255, 255, 255));
		labelTurnos.setBounds(43, 163, 250, 25);
		frame.getContentPane().add(labelTurnos);

		// Record
		labelRecord = new JLabel(
				"El Record es de " + Juego.getNombreRecord() + " con " + Juego.getRecord() + " turnos");
		labelRecord.setFont(new Font("Arial", Font.PLAIN, 18));
		labelRecord.setForeground(new Color(255, 255, 255));
		labelRecord.setBounds(44, 214, 384, 25);
		frame.getContentPane().add(labelRecord);

		// Boton para cerrar
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setForeground(new Color(0, 0, 0));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCerrar.setBackground(new Color(255, 66, 66));
		btnCerrar.setBounds(179, 314, 100, 30);
		btnCerrar.addActionListener(e -> frame.dispose()); // Cerrar la ventana actual
		frame.getContentPane().add(btnCerrar);

		// Boton para reiniciar
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReiniciar.setForeground(new Color(0, 0, 0));
		btnReiniciar.setBackground(new Color(46, 239, 24));
		btnReiniciar.setBounds(156, 260, 152, 43);
		btnReiniciar.addActionListener(e -> reiniciarJuego()); // Llamo al metodo reiniciarJuego
		frame.getContentPane().add(btnReiniciar);

		frame.setVisible(true);
	}

	private void reiniciarJuego() {
		Sonido.setVolumenDecibeles(-20.0f);
		Sonido.reproducirSonido("recursos/Iniciar-reiniciar.wav");
		frame.dispose(); // Cierra la ventana actual
		new PantallaInicial();
	}
}