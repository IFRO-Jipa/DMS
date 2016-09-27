package br.com.dms.model.determinant;

public class Chio {

	public Chio() {
	}

	public static double[][] calcChio(double matriz[][], int tam) {
		matriz = Utilidades.modificaMatriz(matriz, tam);
		if (Utilidades.isNumeroUmPrimeiro(matriz)) {
			double auxM[][] = new double[tam - 1][tam - 1];
			double auxl[] = new double[tam - 1];
			double auxc[] = new double[tam - 1];
			for (int i = 0; i < tam - 1; i++) {
				for (int j = 0; j < tam - 1; j++)
					auxM[i][j] = matriz[i + 1][j + 1];

			}

			for (int i = 0; i < tam - 1; i++)
				auxl[i] = matriz[0][i + 1];

			for (int i = 0; i < tam - 1; i++)
				auxc[i] = matriz[i + 1][0];

			double multiplicadores[][] = new double[tam - 1][tam - 1];
			for (int i = 0; i < tam - 1; i++) {
				for (int j = 0; j < tam - 1; j++)
					multiplicadores[i][j] = auxc[i] * auxl[j];

			}

			for (int i = 0; i < tam - 1; i++) {
				for (int j = 0; j < tam - 1; j++)
					auxM[i][j] -= multiplicadores[i][j];

			}

			return auxM;
		} else {
			return (double[][]) null;
		}
	}
}