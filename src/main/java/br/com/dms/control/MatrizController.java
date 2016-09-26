package br.com.dms.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.util.AlertAdapter;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MatrizController implements Initializable {

	@FXML
	private SplitPane container;

	@FXML
	private SplitPane containerResultado;

	@FXML
	private RadioButton rbOperacaoSubtracao;

	@FXML
	private TextField txtLinhaM1;

	@FXML
	private TextField txtLinhaM2;

	@FXML
	private Button btnMontar;

	@FXML
	private GridPane gridMatriz1;

	@FXML
	private GridPane gridMatriz2;

	@FXML
	private Button btnCalcular;

	@FXML
	private AnchorPane header;

	@FXML
	private RadioButton rbOperacaoSoma;

	@FXML
	private RadioButton rbOperacaoMultiplicacao;

	@FXML
	private TextField txtColunaM1;

	@FXML
	private TextField txtColunaM2;

	private ToggleGroup grupoOperacoes;

	@FXML
	private void handleMontaMatriz(ActionEvent event) {
		if (isMatrizesConfiguradas() && hasOperacao()) {

			if (valoresValidosParaOperacao()) {
				if (!this.container.isVisible()) {
					this.container.setVisible(true);
					this.container.getItems().remove(containerResultado);
				}

				this.criaMatriz(this.gridMatriz1, Integer.parseInt(txtLinhaM1.getText()),
						Integer.parseInt(txtColunaM1.getText()));
				this.criaMatriz(this.gridMatriz2, Integer.parseInt(txtLinhaM2.getText()),
						Integer.parseInt(txtColunaM2.getText()));

				this.btnCalcular.setVisible(true);
			}

		} else {
			AlertAdapter.warning("Dados necessários",
					"Informe o tipo de operação e as dimensões das duas matrizes para prosseguir");

			this.container.setVisible(false);
			this.btnCalcular.setVisible(false);
		}
	}

	private void criaMatriz(GridPane gridPane, int rows, int columns) {
		gridPane.getChildren().clear();
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				TextField text = new TextField("0");
				text.setPrefColumnCount(5);
				text.textProperty().addListener((ChangeListener<String>) (observable, oldValue, newValue) -> {
					if (!newValue.matches("\\d*")) {
						text.setText(newValue.replaceAll("[^\\d]", ""));
					}
				});
				text.setId(String.format("%d,%d", row, column));

				gridPane.add(text, column, row);
				GridPane.setMargin(text, new Insets(5, 5, 5, 5));

			}
		}
	}

	@FXML
	private void handleBtnCalcula(ActionEvent event) {

	}

	private boolean valoresValidosParaOperacao() {
		Toggle selecao = grupoOperacoes.getSelectedToggle();

		if (selecao == this.rbOperacaoSoma) {
			return validaOperacaoSomaSubtracao();
		} else if (selecao == this.rbOperacaoMultiplicacao) {
			return validaOperacaoMultiplicacao();
		} else if (selecao == this.rbOperacaoSubtracao) {
			return validaOperacaoSomaSubtracao();
		}

		return false;
	}

	private boolean validaOperacaoMultiplicacao() {
		int linhaM2 = Integer.parseInt(this.txtLinhaM2.getText());
		int colunaM1 = Integer.parseInt(this.txtColunaM1.getText());

		if (linhaM2 == colunaM1) {
			return true;
		} else {
			AlertAdapter.error("Valores inválidos",
					"Para realizar uma multiplicação, o número de colunas da Matriz 1 deve ser "
							+ "igual ao número de linhas da Matriz 2.\n\nExemplo:\n\t3x2 2x3 -> VÁLIDO"
							+ "\n\t3x4 3x4 -> INVÁLIDO");
			return false;
		}
	}

	private boolean validaOperacaoSomaSubtracao() {
		int colunaM1 = Integer.parseInt(this.txtColunaM1.getText());
		int linhaM1 = Integer.parseInt(this.txtLinhaM1.getText());

		int linhaM2 = Integer.parseInt(this.txtLinhaM2.getText());
		int colunaM2 = Integer.parseInt(this.txtColunaM2.getText());

		if ((colunaM1 == colunaM2) && (linhaM1 == linhaM2)) {
			return true;
		} else {
			AlertAdapter.error("Valores inválidos",
					"Para realizar uma soma ou subtração, as matrizes devem possuir a mesma ordem");
			return false;
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		initContainers();
		this.btnCalcular.setVisible(false);
		initGrupoOperacoes();
		initButtonMontar();
	}

	private void initContainers() {
		this.container.setVisible(false);
		this.containerResultado.setVisible(false);
	}

	private void initGrupoOperacoes() {
		grupoOperacoes = new ToggleGroup();
		grupoOperacoes.getToggles().addAll(rbOperacaoSoma, rbOperacaoSubtracao, rbOperacaoMultiplicacao);
	}

	private void initButtonMontar() {

		btnMontar
				.setTooltip(new Tooltip("Prepara as matrizes para inserção dos valores a partir dos dados definidos."));
	}

	private boolean isMatrizesConfiguradas() {
		boolean matriz1 = !txtLinhaM1.getText().isEmpty() && !txtColunaM1.getText().isEmpty();
		boolean matriz2 = !txtLinhaM2.getText().isEmpty() && !txtColunaM2.getText().isEmpty();

		return matriz1 == true && matriz2 == true;
	}

	private boolean hasOperacao() {
		return grupoOperacoes.getSelectedToggle() != null;
	}

}
