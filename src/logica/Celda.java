package logica;

public class Celda {
	private ColorCelda color;

	public Celda() {
		// Se inicializa en gris (sin color)
		this.color = ColorCelda.GRIS;
	}

	public void cambiarColor() {
		this.color = ColorCelda.obtenerColorAleatorio();
	}

	public void apagar() {
		this.color = ColorCelda.GRIS;
	}

	public ColorCelda getColor() {
		return color;
	}

}