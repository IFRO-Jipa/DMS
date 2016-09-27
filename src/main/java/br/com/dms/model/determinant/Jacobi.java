package br.com.dms.model.determinant;

public class Jacobi {

	public Jacobi() {
	}

	public double[][] calcJacobi(double matriz[][]) {
		double resultMat[][] = matriz;
		int posicaoNaoZero = 0;
		int i = 0;
		do {
			if (i >= matriz.length)
				break;
			if (matriz[i][0] != 0.0F) {
				posicaoNaoZero = i;
				break;
			}
			i++;
		} while (true);
		for (i = 0; i < matriz.length; i++)
			resultMat[0][i] += resultMat[posicaoNaoZero][i];

		divisor = (int) resultMat[0][0];
		double refMat[][] = Chio.calcChio(resultMat, resultMat.length);
		return refMat;
	}

	public int divisor;
}