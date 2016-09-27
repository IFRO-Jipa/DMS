package br.com.dms.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.model.MatrizUtil;
import br.com.dms.model.determinant.Chio;
import br.com.dms.model.determinant.Determinante;
import br.com.dms.model.determinant.Jacobi;
import br.com.dms.util.AlertAdapter;
import br.com.dms.util.view.TextFieldValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DeterminanteController implements Initializable {

	@FXML
	private SplitPane container;

	@FXML
	private TextField txtOrdemMatriz;

	@FXML
	private Button btnCalcular;

	@FXML
	private Label labelDeterminante;

	@FXML
	private Label valorDeterminante;

	@FXML
	private Button btnMontar;

	@FXML
	private GridPane gridValores;

	@FXML
	void handleButtonMontar(ActionEvent event) {
		if (!txtOrdemMatriz.getText().isEmpty()) {
			int ordem = Integer.parseInt(txtOrdemMatriz.getText());
			gridValores.getChildren().clear();
			MatrizUtil.criaMatriz(gridValores, ordem, ordem);
			this.container.setVisible(true);
			txtOrdemMatriz.setVisible(false);
			labelDeterminante.setVisible(false);
			this.btnCalcular.setVisible(true);
		} else {
			AlertAdapter.error("Dados obrigatórios", "Defina a ordem da matriz quadrada para calcular o determinante");
		}
	}

	@FXML
	void handleButtonCalcular(ActionEvent event) {
		double[][] refM = MatrizUtil.extractMatrix(gridValores);

		int tam = MatrizUtil.getRowCount(gridValores);

		if (refM.length == 2) {
			double result = Determinante.calc2x2(refM);
			mostraDeterminante(result);
		} else if (refM.length == 3) {
			double result = Determinante.calcSarrus(refM, 1.0F);
			mostraDeterminante(result);
		} else if (refM.length == 4) {
			Jacobi jc = new Jacobi();
			double[][] aux = (double[][]) null;
			double divisor;
			if (refM[0][0] == 0.0F) {
				aux = jc.calcJacobi(refM);
				divisor = jc.divisor;
			} else {
				divisor = refM[0][0];
				aux = Chio.calcChio(refM, tam);
			}
			double det = Determinante.calcSarrus(aux, divisor);
			double detFinal = det * divisor;
			mostraDeterminante(detFinal);
		} else if (refM.length > 4) {
			Jacobi jc = new Jacobi();
			int numReducoes = refM.length - 3;
			double divisor[] = new double[numReducoes];
			int numeroDeIguais = 0;
			double aux[][] = refM;
			for (int i = 0; i < refM.length; i++) {
				for (int j = 0; j < refM.length; j++)
					if (!aux[i].equals(aux[j]))
						numeroDeIguais++;
			}

			if (numeroDeIguais != tam * tam) {
				for (int i = 0; i < numReducoes; i++)
					if (aux[0][0] == 0.0F) {
						aux = jc.calcJacobi(aux);
						divisor[i] = jc.divisor;
					} else {
						divisor[i] = aux[0][0];
						aux = Chio.calcChio(aux, aux.length);
					}

				double det = Determinante.calcSarrus(aux, 0.0F);
				double detFinal = det;
				for (int i = 0; i < divisor.length; i++)
					detFinal *= divisor[i];
				mostraDeterminante(detFinal);
			} else if (numeroDeIguais == tam * tam) {
				float detFinal = 0.0F;
				mostraDeterminante(detFinal);
			}
		}
	}

	private void mostraDeterminante(double result) {
		this.labelDeterminante.setVisible(true);
		this.valorDeterminante.setVisible(true);
		this.valorDeterminante.setText(String.valueOf(result));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.container.setVisible(false);
		this.btnCalcular.setVisible(false);
		this.txtOrdemMatriz.textProperty().addListener(TextFieldValidation.getValidatorForNumbers(this.txtOrdemMatriz));
	}

}
