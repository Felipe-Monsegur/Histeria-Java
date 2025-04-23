package logica;

public class Juego {
	private Grilla grilla;
	private int turnos;

	private static int record = Integer.MAX_VALUE; // Static para que no se reinicie
	private static String nombreRecord = ""; // Static para que no se reinicie

	public Juego(int tamaño) {
		this.grilla = new Grilla(tamaño);
		this.turnos = 0;
	}

	public void jugarTurno(int fila, int columna) {
		grilla.tocarCelda(fila, columna);
		turnos++;
	}

	public boolean seGanoJuego() {
		return grilla.estaCompleta();
	}

	public ColorCelda consultarColorCelda(int fila, int columna) {
		return grilla.getColorCelda(fila, columna);
	}

	public int[] sugerirCelda() {
		return grilla.sugerirMejorCelda();
	}

	public void actualizarRecord(String nombreJugador) {
		if (seGanoJuego() && turnos < record) {
			record = turnos;
			nombreRecord = nombreJugador;
		}
	}

	public int getTurnos() {
		return turnos;
	}

	public static int getRecord() {
		return record;
	}

	public static String getNombreRecord() {
		return nombreRecord;
	}

}