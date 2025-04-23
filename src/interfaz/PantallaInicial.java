package interfaz;

import javax.swing.*;

import logica.Sonido;

import java.awt.*;

public class PantallaInicial {
	private JFrame frame;
	private JTextField nombreField;
	private JComboBox<String> dificultadComboBox;
	private JButton btnConfirmar;

	public PantallaInicial() {
		frame = new JFrame("Locura Cromática - Pantalla Inicial");
		frame.getContentPane().setBackground(new Color(45, 45, 47));
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		// Texto bienvenida
		JLabel labelBienvenida = new JLabel("LOCURA CROMATICA");
		labelBienvenida.setForeground(new Color(255, 255, 255));
		labelBienvenida.setFont(new Font("Arial", Font.BOLD, 26));
		labelBienvenida.setBounds(89, 88, 299, 41);
		frame.getContentPane().add(labelBienvenida);

		// Label y campo de nombre
		JLabel labelNombre = new JLabel("Ingrese un nombre:");
		labelNombre.setForeground(new Color(255, 255, 255));
		labelNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		labelNombre.setBounds(30, 154, 192, 25);
		frame.getContentPane().add(labelNombre);

		nombreField = new JTextField();
		nombreField.setFont(new Font("Arial", Font.PLAIN, 18));
		nombreField.setBounds(206, 154, 200, 25);
		frame.getContentPane().add(nombreField);

		// Label y ComboBox para dificultad
		JLabel dificultadLabel = new JLabel("Elija la dificultad: ");
		dificultadLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		dificultadLabel.setForeground(new Color(255, 255, 255));
		dificultadLabel.setBounds(30, 207, 144, 25);
		frame.getContentPane().add(dificultadLabel);

		String[] opciones = { "5x5", "3x3", "7x7" };
		dificultadComboBox = new JComboBox<>(opciones);
		dificultadComboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		dificultadComboBox.setBounds(206, 207, 100, 25);
		frame.getContentPane().add(dificultadComboBox);

		// Botón "iniciar juego"
		btnConfirmar = new JButton("Iniciar Juego");
		btnConfirmar.setForeground(new Color(0, 0, 0));
		btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnConfirmar.setBackground(new Color(46, 239, 24));
		btnConfirmar.setBounds(142, 314, 200, 77);
		btnConfirmar.addActionListener(e -> iniciarJuego());
		frame.getContentPane().add(btnConfirmar);

		frame.setVisible(true);
	}

	private void iniciarJuego() {
		Sonido.setVolumenDecibeles(-20.0f);
		Sonido.reproducirSonido("recursos/Iniciar-reiniciar.wav");
		
		// Obtengo el nombre del jugador, si esta vacio le pido que ingrese un nombre
		String jugador = nombreField.getText().trim();
		if (jugador.isEmpty()) {
			JOptionPane.showMessageDialog(frame, "Por favor, ingrese un nombre");
			return;
		}
		// Obtengo la dificultad seleccionada
		int tamaño = obtenerTamañoSeleccionado();

		frame.dispose(); 
		new PantallaJuego(jugador, tamaño); // Creo la pantalla del juego
	}

	private int obtenerTamañoSeleccionado() {
		String seleccion = (String) dificultadComboBox.getSelectedItem();
		if (seleccion.equals("5x5")) {
			return 5;
		}else if (seleccion.equals("3x3")) {
			return 3;
		}else if (seleccion.equals("7x7")) {
			return 7;
		}
		return 5; // por defecto
	}
}