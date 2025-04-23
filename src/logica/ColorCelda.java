package logica;

import java.awt.Color;
import java.util.Random;

public enum ColorCelda {
	ROJO(Color.RED), AZUL(Color.BLUE), VERDE(Color.GREEN), AMARILLO(Color.YELLOW), NARANJA(Color.ORANGE),
	ROSA(Color.PINK), GRIS(Color.LIGHT_GRAY);

	private final Color color;

	private ColorCelda(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public static ColorCelda obtenerColorAleatorio() {
		Random random = new Random();
		ColorCelda[] colores = { ROJO, AZUL, VERDE, AMARILLO, NARANJA, ROSA };
		return colores[random.nextInt(colores.length)];
	}
}