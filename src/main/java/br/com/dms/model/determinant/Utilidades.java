package br.com.dms.model.determinant;

public class Utilidades {

	private static class Retorno {

		public Retorno(int posX, int posY, int retorno) {
			this.posX = posX;
			this.posY = posY;
			this.retorno = retorno;
		}

		@SuppressWarnings("unused")
		public Retorno(int retorno) {
			this.retorno = retorno;
		}

		@SuppressWarnings("unused")
		public int posX;
		@SuppressWarnings("unused")
		public int posY;
		@SuppressWarnings("unused")
		public static final int NUMERO_ENCONTRADO = 1;
		@SuppressWarnings("unused")
		public static final int NAO_ENCONTRADO = 0;
		@SuppressWarnings("unused")
		public int retorno;
	}

	public Utilidades() {
	}

	public static boolean isNumeroUmPrimeiro(double matriz[][]) {
		return matriz[0][0] == 1.0F;
	}

	public Retorno hasNumeroUm(float matriz[][], int tamanho) {
		Retorno n = null;
		label0: for (int i = 0; i < tamanho; i++) {
			int j = 0;
			do {
				if (j >= tamanho)
					continue label0;
				if (matriz[i][j] == 1.0F) {
					n = new Retorno(i, j, 1);
					System.out.println(
							(new StringBuilder()).append("\n posX: ").append(i).append(" posY: ").append(j).toString());
					continue label0;
				}
				j++;
			} while (true);
		}

		return n;
	}

	public static double detZero(double n) {
		if (n == 0.0d)
			n = 0.0d;
		return n;
	}

	public static double[][] modificaMatriz(double matriz[][], int tam) {
		double matrizAux[][] = matriz;
		double divisor = matriz[0][0];
		for (int i = 0; i < tam; i++)
			matrizAux[0][i] = matriz[0][i] / divisor;

		return matrizAux;
	}

	public static boolean isOrdemCerta(int tipo, int linM1, int colM1, int linM2, int colM2) {
		boolean resultado = false;
		switch (tipo) {
		default:
			break;

		case 0: // '\0'
			if (linM1 == linM2 && colM1 == colM2)
				resultado = true;
			else
				resultado = false;
			break;

		case 1: // '\001'
			if (colM1 == linM2)
				resultado = true;
			else
				resultado = false;
			break;
		}
		return resultado;
	}

	public static final int OPERACAO_Ss = 0;
	public static final int OPERACAO_M = 1;
}
