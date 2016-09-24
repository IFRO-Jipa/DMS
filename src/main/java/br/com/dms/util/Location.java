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
			return "Operações com Matrizes";
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
			return "Resolução de Sistemas Lineares";
		}

	},
	DETERMINANTE {
		@Override
		public String get() {
			return "DeterminanteView";
		}

		@Override
		public String getTitulo() {
			return "Cálculo de Determinantes";
		}
	},
	MENU {

		@Override
		public String get() {
			return "MenuView";
		}

		@Override
		public String getTitulo() {
			// essa página não vai ser aberta na aba.
			return null;
		}

	};

}
