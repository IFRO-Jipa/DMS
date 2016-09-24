package br.com.dms.control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.dms.util.Location;
import br.com.dms.util.ViewLocation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

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