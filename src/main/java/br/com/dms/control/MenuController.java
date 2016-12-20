package br.com.dms.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.util.Location;
import br.com.dms.util.ViewLocation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Natanael Sirqueira
 */

public class MenuController implements Initializable {

	@FXML
	private TabPane tabPane;

	@FXML
	public void abreMatrizes() throws IOException {
		criaAba(Location.MATRIZ);

	}

	@FXML
	public void abreDeterminante() throws IOException {
		criaAba(Location.DETERMINANTE);

	}
	
	@FXML
	
	public void abreSistemas() throws IOException {
		criaAba(Location.SISTEMA_LINEAR);
	}
	
	@FXML
	private void abreCreditos() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewLocation.getLocation(Location.CREDITOS));
		BorderPane pane = loader.load();

		Stage stage = new Stage();
		stage.setScene(new Scene(pane));
		stage.setMaximized(false);
		stage.setResizable(false);
		stage.setTitle(Location.CREDITOS.getTitulo());
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.centerOnScreen();
		stage.showAndWait();
	}
	

	private void criaAba(Location tipo) throws IOException {
		Tab tab = new Tab(tipo.getTitulo());
		tab.setClosable(true);
		tab.setContent(getPane(tipo));

		if (tabPane.getTabs().add(tab)) {
			tabPane.getSelectionModel().select(tab);
		}
	}

	private AnchorPane getPane(Location tipo) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL resource = ViewLocation.getLocation(tipo);
		loader.setLocation(resource);
		return loader.load();
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}