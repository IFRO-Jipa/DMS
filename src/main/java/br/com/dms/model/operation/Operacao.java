package br.com.dms.model.operation;

public class Operacao {

	public double[][] soma(double[][] matriz1, double[][] matriz2) {
		if ((matriz1.length == matriz2.length) && (matriz2[0].length == matriz1[0].length)) {
			double[][] resultado = new double[matriz1.length][matriz1[0].length];

			for (int i = 0; i < matriz1.length; i++) {
				for (int j = 0; j < matriz1[i].length; j++) {
					resultado[i][j] = matriz1[i][j] + matriz2[i][j];
				}
			}

			return resultado;
		} else
			throw new IllegalArgumentException("As matrizes precisam ser iguais para realizar a soma");

	}

	public double[][] subtracao(double[][] matriz1, double[][] matriz2) {
		if ((matriz1.length == matriz2.length) && (matriz2[0].length == matriz1[0].length)) {
			double[][] resultado = new double[matriz1.length][matriz1[0].length];

			for (int i = 0; i < matriz1.length; i++) {
				for (int j = 0; j < matriz1[i].length; j++) {
					resultado[i][j] = matriz1[i][j] - matriz2[i][j];
				}
			}

			return resultado;
		} else
			throw new IllegalArgumentException("As matrizes precisam ser iguais para realizar a subtração");

	}

	public double[][] multiplicacao(double[][] matriz1, double[][] matriz2) {
		if (matriz2.length == matriz1[0].length) {
			double[][] resultado = new double[matriz1.length][matriz2[0].length];

			double[] valoresColunas = new double[matriz1[0].length];
			double[] valoresLinhas = new double[matriz2.length];

			for (int b = 0; b < matriz2[0].length; b++) { // percorre a partir das colunas da m2
				for (int i = 0; i < matriz1.length; i++) { // percorre a partir das linhas da m1
					for (int k = 0; k < matriz1[0].length; k++) // salva os valores das colunas da m1
						valoresColunas[k] = matriz1[i][k];

					for (int x = 0; x < matriz1[0].length; x++) // salva os valores das linhas da m2
						valoresLinhas[x] = matriz2[x][b];

					for (int j = 0; j < matriz2.length; j++) // 
						resultado[i][b] += valoresColunas[j] * valoresLinhas[j];
				}
			}

			return resultado;
		} else {
			throw new IllegalArgumentException(
					"A dimensão de coluna da Matriz1 precisa ser igual a dimensão de linhas da Matriz2");
		}
	}

	public static void main(String[] args) {
		double[][] m1 = new double[][] { { 1, 2, 3 }, { 4, 5, 6 } };
		double[][] m2 = new double[][] { { 1, 2 }, { 3, 4 }, { 5, 6 } };

		double[][] resultado = new Operacao().multiplicacao(m1, m2);

		for (int i = 0; i < resultado.length; i++) {
			for (int j = 0; j < resultado[i].length; j++) {
				System.out.printf("[%d,%d]=%.2f\n", i, j, resultado[i][j]);
			}
		}
	}
}
