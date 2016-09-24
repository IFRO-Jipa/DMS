package br.com.dms.app;

import br.com.dms.util.Location;
import br.com.dms.util.ViewLocation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewLocation.getLocation(Location.MENU));
		BorderPane pane = (BorderPane) loader.load();

		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("Menu Principal");
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
