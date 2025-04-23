package logica;

public class Grilla {
	private Celda[][] celdas;
	private int tamaño;

	private static final int[][] direcciones = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public Grilla(int tamaño) {
		this.tamaño = tamaño;
		this.celdas = new Celda[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				celdas[i][j] = new Celda();
			}
		}
	}

	public void tocarCelda(int fila, int columna) {
		Celda celda = celdas[fila][columna];
		celda.cambiarColor();

		if (tieneVecinaDelMismoColor(fila, columna)) {
			apagarVecinas(fila, columna);
		}
	}

	private boolean tieneVecinaDelMismoColor(int fila, int columna) {
		ColorCelda color = celdas[fila][columna].getColor();

		for (int[] dir : direcciones) {
			int nuevaFila = fila + dir[0];
			int nuevaCol = columna + dir[1];

			if (esPosicionValida(nuevaFila, nuevaCol)) {
				if (celdas[nuevaFila][nuevaCol].getColor() == color) {
					return true;
				}
			}
		}
		return false;
	}

	private void apagarVecinas(int fila, int columna) {
		celdas[fila][columna].apagar();

		for (int[] dir : direcciones) {
			int nuevaFila = fila + dir[0];
			int nuevaCol = columna + dir[1];

			if (esPosicionValida(nuevaFila, nuevaCol)) {
				celdas[nuevaFila][nuevaCol].apagar();
			}
		}
	}

	public boolean estaCompleta() {
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				if (celdas[i][j].getColor() == ColorCelda.GRIS) {
					return false;
				}
			}
		}
		return true;
	}

	public int[] sugerirMejorCelda() {
		int minVecinosColoreados = Integer.MAX_VALUE;
		int[] mejorCelda = null;

		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				if (celdas[i][j].getColor() == ColorCelda.GRIS) {
					int vecinosColoreados = contarVecinosColoreados(i, j);
					if (vecinosColoreados < minVecinosColoreados) {
						minVecinosColoreados = vecinosColoreados;
						mejorCelda = new int[] { i, j };
					}
				}
			}
		}
		return mejorCelda;
	}

	private int contarVecinosColoreados(int fila, int columna) {
		int contador = 0;

		for (int[] dir : direcciones) {
			int nuevaFila = fila + dir[0];
			int nuevaCol = columna + dir[1];

			if (esPosicionValida(nuevaFila, nuevaCol) && celdas[nuevaFila][nuevaCol].getColor() != ColorCelda.GRIS) {
				contador++;
			}
		}
		return contador;
	}

	private boolean esPosicionValida(int fila, int columna) {
		return fila >= 0 && fila < tamaño && columna >= 0 && columna < tamaño;
	}

	public ColorCelda getColorCelda(int fila, int columna) {
		return celdas[fila][columna].getColor();
	}

}