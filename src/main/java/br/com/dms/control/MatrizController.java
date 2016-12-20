package br.com.dms.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.model.operation.Operacao;
import br.com.dms.util.AlertAdapter;
import br.com.dms.util.MatrizUtil;
import br.com.dms.util.view.TextFieldValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
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
	private TableView<String[]> tbResultado;

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
		try {
			if (isMatrizesConfiguradas() && hasOperacao()) {

				if (valoresValidosParaOperacao()) {
					if (!this.container.isVisible()) {
						this.container.setVisible(true);
						this.containerResultado.setVisible(false);
					}

					MatrizUtil.criaMatriz(this.gridMatriz1, Integer.parseInt(txtLinhaM1.getText()),
							Integer.parseInt(txtColunaM1.getText()));
					MatrizUtil.criaMatriz(this.gridMatriz2, Integer.parseInt(txtLinhaM2.getText()),
							Integer.parseInt(txtColunaM2.getText()));

					this.btnCalcular.setVisible(true);
				}

			} else {
				AlertAdapter.faltaOperacaoDimensao();
//				AlertAdapter.warning("Dados necessários",
//						"Informe o tipo de operação e as dimensões das duas matrizes para prosseguir");

				this.container.setVisible(false);
				this.btnCalcular.setVisible(false);
			}
		} catch (RuntimeException e) {
			AlertAdapter.error("Falha inesperada", e);
		}
	}

	@FXML
	private void handleBtnCalcula(ActionEvent event) {
		try {
			this.containerResultado.setVisible(true);

			double[][] matriz1 = MatrizUtil.extractMatrix(gridMatriz1);
			double[][] matriz2 = MatrizUtil.extractMatrix(gridMatriz2);

			if (valoresValidosParaOperacao()) {
				Operacao operacao = new Operacao();
				double[][] resultado = new double[0][0];
				if (grupoOperacoes.getSelectedToggle() == rbOperacaoSoma) {
					resultado = operacao.soma(matriz1, matriz2);
				} else if (grupoOperacoes.getSelectedToggle() == rbOperacaoSubtracao) {
					resultado = operacao.subtracao(matriz1, matriz2);
				} else if (grupoOperacoes.getSelectedToggle() == rbOperacaoMultiplicacao) {
					resultado = operacao.multiplicacao(matriz1, matriz2);
				}
				MatrizUtil.criaMatriz(this.tbResultado, resultado);
			}
		} catch (RuntimeException e) {
			AlertAdapter.error("Erro inesperado", e);
		}

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
			AlertAdapter.ordemInvalidaMultiplicacao();
			// AlertAdapter.error("Valores inválidos",
			// "Para realizar uma multiplicação, o número de colunas da Matriz 1
			// deve ser "
			// + "igual ao número de linhas da Matriz 2.\n\nExemplo:\n\t3x2 2x3
			// -> VÁLIDO"
			// + "\n\t3x4 3x4 -> INVÁLIDO");
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
			AlertAdapter.ordemInvalidaSomaSubtracao();
			// AlertAdapter.error("Valores inválidos",
			// "Para realizar uma soma ou subtração, as matrizes devem possuir a
			// mesma ordem");
			return false;
		}
	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		initContainers();
		this.btnCalcular.setVisible(false);
		initGrupoOperacoes();
		initButtonMontar();
		configTextFields();
	}

	private void configTextFields() {
		this.txtColunaM1.textProperty().addListener(TextFieldValidation.getValidatorForNaturalNumbers(txtColunaM1));
		this.txtColunaM2.textProperty().addListener(TextFieldValidation.getValidatorForNaturalNumbers(txtColunaM2));
		this.txtLinhaM1.textProperty().addListener(TextFieldValidation.getValidatorForNaturalNumbers(txtLinhaM1));
		this.txtLinhaM2.textProperty().addListener(TextFieldValidation.getValidatorForNaturalNumbers(txtLinhaM2));
		
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
