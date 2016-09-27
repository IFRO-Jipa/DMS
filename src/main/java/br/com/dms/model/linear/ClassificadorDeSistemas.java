package br.com.dms.model.linear;

public class ClassificadorDeSistemas {

	public static int classifica2x2(double matriz[][]) {
		int resultado = -1;
		double refMatriz[] = new double[3];
		refMatriz[0] = matriz[0][0] / matriz[1][0];
		refMatriz[1] = matriz[0][1] / matriz[1][1];
		refMatriz[2] = matriz[0][2] / matriz[1][2];
		if (refMatriz[0] == refMatriz[1]) {
			if (refMatriz[1] == refMatriz[2])
				resultado = 2;
			else if (refMatriz[1] != refMatriz[2])
				resultado = 0;
		} else if (refMatriz[0] != refMatriz[1])
			resultado = 1;
		return resultado;
	}

	public static final int SISTEMA_SI = 0;
	public static final int SISTEMA_SPD = 1;
	public static final int SISTEMA_SPI = 2;
	public static final int ERRO = -1;

}
