package br.com.dms.model;

import java.util.ArrayList;
import java.util.List;

import br.com.dms.util.view.TableViewUtil;
import br.com.dms.util.view.TextFieldValidation;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MatrizUtil {

	public static void criaMatriz(GridPane gridPane, int rows, int columns) {
		gridPane.getChildren().clear();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				TextField text = new TextField("0");
				text.setPrefColumnCount(5);
				text.textProperty().addListener(TextFieldValidation.getValidatorForNumbers(text));

				text.setId(String.format("%d,%d", row, column));

				gridPane.add(text, column, row);
				GridPane.setMargin(text, new Insets(3, 3, 3, 3));
			}
		}
	}

	public static void criaMatriz(TableView<String[]> grid, double[][] matriz) {
		TableViewUtil.prepareTableView(grid, matriz, false);
	}

	public static synchronized double[][] extractMatrix(GridPane grid) {

		List<Posicao> posicoes = getPosicoes(grid);

		Integer linha = getRowCount(posicoes);
		Integer coluna = getColumnCount(posicoes);
		final double[][] valores = new double[linha][coluna];

		posicoes.forEach(
				posicao -> valores[posicao.getLinha()][posicao.getColuna()] = Double.parseDouble(posicao.getValor()));

		System.out.printf("Matriz %d,%d\n", linha, coluna);

		for (int i = 0; i < valores.length; i++) {
			for (int j = 0; j < valores[i].length; j++)
				System.out.printf("[%d,%d]=%.2f", i, j, valores[i][j]);
		}

		return valores;
	}

	private static int getColumnCount(List<Posicao> posicoes) {
		return posicoes.stream().mapToInt(p -> p.getColuna()).sorted().max().getAsInt() + 1;
	}

	private static int getRowCount(List<Posicao> posicoes) {
		return posicoes.stream().mapToInt(posicao -> posicao.getLinha()).sorted().max().getAsInt() + 1;
	}

	public static int getRowCount(GridPane gridValores) {
		return getRowCount(getPosicoes(gridValores));
	}

	public static int getColumnCount(GridPane gridValores) {
		return getColumnCount(getPosicoes(gridValores));
	}

	private static List<Posicao> getPosicoes(GridPane grid) {
		List<Posicao> posicoes = new ArrayList<>();

		// adiciona todos os valores e depois ordena eles por ordem alfabética,
		// vai ficar alinhados
		ObservableList<Node> textFields = grid.getChildren();
		textFields.forEach(textField -> {
			String[] dimensoes = textField.getId().split(",");
			int linha = Integer.parseInt(dimensoes[0]);
			int coluna = Integer.parseInt(dimensoes[1]);

			String valor = ((TextField) textField).getText();

			posicoes.add(new Posicao(linha, coluna, valor));
		});
		return posicoes;
	}

	private static class Posicao {
		private Integer linha;
		private Integer coluna;
		private String valor;

		public Posicao(int linha, int coluna, String valor) {
			super();
			this.linha = linha;
			this.coluna = coluna;
			this.valor = valor;
		}

		public Integer getLinha() {
			return linha;
		}

		public Integer getColuna() {
			return coluna;
		}

		public String getValor() {
			return valor;
		}

		@Override
		public String toString() {
			return "Posicao [linha=" + linha + ", coluna=" + coluna + ", valor=" + valor + "]";
		}

	}

}
