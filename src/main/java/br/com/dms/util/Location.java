package br.com.dms.util;

interface Identificadora {
	String getNome();

	String getTitulo();
}

public enum Location implements Identificadora {

	MATRIZ {
		@Override
		public String getNome() {
			return "MatrizView";
		}

		@Override
		public String getTitulo() {
			// TODO Auto-generated method stub
			return "Operações com Matrizes";
		}

	},
	SISTEMA_LINEAR {
		@Override
		public String getNome() {
			return "Sistema Linear";
		}

		@Override
		public String getTitulo() {
			// TODO Auto-generated method stub
			return "Resolução de Sistemas Lineares";
		}

	},
	DETERMINANTE {
		@Override
		public String getNome() {
			return "Determinantes";
		}

		@Override
		public String getTitulo() {
			return "Cálculo de Determinantes";
		}
	};

}
