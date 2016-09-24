package br.com.dms.util;

interface Identificadora {
	String get();

	String getTitulo();
}

public enum Location implements Identificadora {

	MATRIZ {
		@Override
		public String get() {
			return "MatrizView";
		}

		@Override
		public String getTitulo() {
			// TODO Auto-generated method stub
			return "Opera��es com Matrizes";
		}

	},
	SISTEMA_LINEAR {
		@Override
		public String get() {
			return "SistemaLinearView";
		}

		@Override
		public String getTitulo() {
			// TODO Auto-generated method stub
			return "Resolu��o de Sistemas Lineares";
		}

	},
	DETERMINANTE {
		@Override
		public String get() {
			return "DeterminanteView";
		}

		@Override
		public String getTitulo() {
			return "C�lculo de Determinantes";
		}
	},
	MENU {

		@Override
		public String get() {
			return "MenuView";
		}

		@Override
		public String getTitulo() {
			// essa p�gina n�o vai ser aberta na aba.
			return null;
		}

	};

}
