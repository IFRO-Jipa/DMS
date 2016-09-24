package br.com.dms.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.util.AlertAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;

public class MatrizController implements Initializable {

	@FXML
	private SplitPane container;

	@FXML
	private RadioButton rbOperacaoSubtracao;

	@FXML
	private TextField txtLinhaM1;

	@FXML
	private TextField txtLinhaM2;

	@FXML
	private Button btnMontar;

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

	@FXML
	private TableView<String> tbMatriz1;

	@FXML
	private TableView<String> tbMatriz2;

	@FXML
	private TableView<String> tbResultado;

	private ToggleGroup grupoOperacoes;

	@FXML
	private void handleMontaMatriz(ActionEvent event) {
		if (isMatrizesConfiguradas() && hasOperacao()) {
			this.container.setVisible(true);

			this.criaMatriz(tbMatriz1, Integer.parseInt(txtLinhaM1.getText()), Integer.parseInt(txtColunaM1.getText()));
			this.criaMatriz(tbMatriz2, Integer.parseInt(txtLinhaM2.getText()), Integer.parseInt(txtColunaM2.getText()));
		} else {
			AlertAdapter.warning("Dados necessários",
					"Informe o tipo de operação e as dimensões das duas matrizes para prosseguir");

			this.container.setVisible(false);
		}
	}

	private void criaMatriz(TableView<String> tbMatriz, int linhas, int colunas) {
		tbMatriz.getColumns().clear();
		tbMatriz.getColumns().add(new TableColumn<>());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initGrupoOperacoes();
		initButtonMontar();
	}

	private void initGrupoOperacoes() {
		grupoOperacoes = new ToggleGroup();
		grupoOperacoes.getToggles().addAll(rbOperacaoSoma, rbOperacaoSubtracao, rbOperacaoMultiplicacao);
	}

	private void initButtonMontar() {
		btnMontar.disabledProperty().addListener((obs, oldV, newV) -> {
			btnMontar.setDisable(!(isMatrizesConfiguradas() && hasOperacao()));
		});

		btnMontar
				.setTooltip(new Tooltip("Prepara as matrizes para inserção dos valores a partir dos dados definidos."));
	}

	private boolean isMatrizesConfiguradas() {
		boolean matriz1 = txtLinhaM1.getText().isEmpty() && txtColunaM1.getText().isEmpty();
		boolean matriz2 = txtLinhaM2.getText().isEmpty() && txtColunaM2.getText().isEmpty();

		return matriz1 && matriz2;
	}

	private boolean hasOperacao() {
		return grupoOperacoes.getSelectedToggle() != null;
	}

}
