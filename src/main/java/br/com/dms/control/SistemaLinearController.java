package br.com.dms.control;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.model.MatrizUtil;
import br.com.dms.model.linear.ClassificadorDeSistemas;
import br.com.dms.model.linear.Cramer;
import br.com.dms.util.AlertAdapter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SistemaLinearController implements Initializable {

	@FXML
	private VBox paneResultados;

	@FXML
	private HBox paneClassificacao;

	@FXML
	private HBox paneX;

	@FXML
	private HBox paneY;

	@FXML
	private HBox paneZ;

	@FXML
	private Label txtValorX;

	@FXML
	private Label txtValorZ;

	@FXML
	private Label txtClassificacao;

	@FXML
	private Label txtValorY;

	@FXML
	private GridPane gridValores;

	@FXML
	private void handleButtonCalcula() {
		double[][] valores = MatrizUtil.extractMatrix(gridValores);

		if (valores.length == 2) {
			if (ClassificadorDeSistemas.classifica2x2(valores) == 1) {
				double result[] = Cramer.calc2x2(valores);

				this.paneResultados.setVisible(true);
				this.paneClassificacao.setVisible(true);
				this.txtClassificacao.setText("Sistema possível e determinado (SPD)");
				this.paneX.setVisible(true);
				this.txtValorX.setText(String.valueOf(result[0]));
				this.paneY.setVisible(true);
				this.txtValorY.setText(String.valueOf(result[1]));
			} else if (ClassificadorDeSistemas.classifica2x2(valores) == 2) {
				this.paneResultados.setVisible(true);
				this.paneClassificacao.setVisible(true);
				this.txtClassificacao.setText("Sistema possível e indeterminado (SPI)");
			} else if (ClassificadorDeSistemas.classifica2x2(valores) == 0) {
				this.paneResultados.setVisible(true);
				this.paneClassificacao.setVisible(true);
				this.txtClassificacao.setText("Sistema impossível (SI)");
			} else {
				this.paneResultados.setVisible(true);
				this.paneClassificacao.setVisible(true);
				this.txtClassificacao.setText("Falha crítica em classificação.");
				AlertAdapter.error("Classificação mal-sucedida",
						"Não foi possível classificar o sistema.\n\n\tVocê tem certeza de que os valores informados estão corretos?Verifique.");
			}
		} else if (valores.length == 3) {
			double result[] = Cramer.calc3x3(valores);

			this.paneResultados.setVisible(true);
			this.paneClassificacao.setVisible(true);
			this.txtClassificacao.setText("Sistema possível e determinado (SPD)");
			this.paneX.setVisible(true);
			this.txtValorX.setText(String.valueOf(result[0]));
			this.paneY.setVisible(true);
			this.txtValorY.setText(String.valueOf(result[1]));
			this.paneZ.setVisible(true);
			this.txtValorZ.setText(String.valueOf(result[2]));
		} else {
			AlertAdapter.warning("Ordem não suportada",
					"Lamentamos o ocorrido, mas só é feita a classificação de sistemas 2x2 nesta versão.\n\n\tEstamos trabalhando para o suporte completo.");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.paneResultados.setVisible(false);
		this.paneClassificacao.setVisible(false);
		this.paneX.setVisible(false);
		this.paneY.setVisible(false);
		this.paneZ.setVisible(false);
		MatrizUtil.criaMatriz(gridValores, 2, 3);
	}
}
