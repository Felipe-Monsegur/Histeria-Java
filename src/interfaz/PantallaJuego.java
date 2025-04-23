package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logica.ColorCelda;
import logica.Juego;
import logica.Sonido;

public class PantallaJuego {
	private JFrame frame;
	private JButton[][] botones;
	private JLabel labelTurnos;
	private JLabel labelRecord;

	private int tamaño;
	private Juego juego;
	private String jugador;

	public PantallaJuego(String jugador, int tamaño) {
		if (tamaño <= 0)
			throw new IllegalArgumentException("El tamaño de la grilla debe ser mayor que 0");

		this.tamaño = tamaño;
		this.jugador = jugador;
		this.juego = new Juego(tamaño);
		inicializar();
	}

	// Inicializa los contenidos del frame
	private void inicializar() {
		frame = new JFrame("Locura Cromática - Jugador: " + jugador);
		frame.getContentPane().setBackground(new Color(45, 45, 47));
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BorderLayout());

		// Grilla de botones
		frame.getContentPane().add(crearPanelGrilla(), BorderLayout.CENTER);

		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(45, 45, 47));

		// Turnos
		labelTurnos = new JLabel("Turnos: 0");
		labelTurnos.setFont(new Font("Arial", Font.BOLD, 13));
		labelTurnos.setForeground(new Color(255, 255, 255));
		panelInfo.add(labelTurnos);

		// Record
		labelRecord = new JLabel("Récord: " + (Juego.getRecord() == Integer.MAX_VALUE ? "-" : Juego.getRecord()));
		labelRecord.setFont(new Font("Arial", Font.BOLD, 13));
		labelRecord.setForeground(new Color(255, 255, 255));
		panelInfo.add(labelRecord);

		// Sugerir celda
		JButton btnSugerir = new JButton("Sugerir");
		btnSugerir.setFont(new Font("Arial", Font.BOLD, 13));
		btnSugerir.setBackground(new Color(255, 200, 0));
		btnSugerir.addActionListener(e -> sugerirJugada());
		panelInfo.add(btnSugerir);

		frame.getContentPane().add(panelInfo, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	private JPanel crearPanelGrilla() {
		JPanel panelGrilla = new JPanel(new GridLayout(tamaño, tamaño));
		botones = new JButton[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				final int fila = i;
				final int columna = j;

				botones[i][j] = new JButton();
				botones[i][j].setBackground(Color.LIGHT_GRAY);
				botones[i][j].addActionListener(e -> manejarTurno(fila, columna));

				panelGrilla.add(botones[i][j]);
			}
		}
		return panelGrilla;
	}

	// Accion al tocar una celda
	private void manejarTurno(int fila, int columna) {
		Sonido.setVolumenDecibeles(-20.0f);
		Sonido.reproducirSonido("recursos/Click.wav");

		juego.jugarTurno(fila, columna);
		actualizarGrilla();
		actualizarLabels();

		if (juego.seGanoJuego()) {
			Sonido.setVolumenDecibeles(-20.0f);
			Sonido.reproducirSonido("recursos/Victoria2.wav");

			juego.actualizarRecord(jugador);

			new PantallaFinal(jugador, juego.getTurnos());
			frame.dispose();
		}
	}

	// Accion al tocar botón sugerirCelda
	private void sugerirJugada() {
		int[] sugerencia = juego.sugerirCelda();
		if (sugerencia != null) {
			int fila = sugerencia[0];
			int columna = sugerencia[1];
			botones[fila][columna].setBackground(Color.DARK_GRAY); // Color de la celda sugerida
		} else {
			JOptionPane.showMessageDialog(frame, "No hay jugadas recomendadas.");
		}
	}

	// Actualizar la interfaz
	private void actualizarGrilla() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				ColorCelda color = juego.consultarColorCelda(i, j);
				botones[i][j].setBackground(obtenerColor(color));
			}
		}
	}

	// Actualizar los labels
	private void actualizarLabels() {
		labelTurnos.setText("Turnos: " + juego.getTurnos());
		labelRecord.setText("Récord: " + (Juego.getRecord() == Integer.MAX_VALUE ? "-" : Juego.getRecord()));
	}

	private Color obtenerColor(ColorCelda colorCelda) {
		return colorCelda != null ? colorCelda.getColor() : Color.LIGHT_GRAY;
	}

}