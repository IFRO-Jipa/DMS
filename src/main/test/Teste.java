
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Teste extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Teste.class.getClassLoader().getResource("fxml/messages/matriz/OrdemInvalidaSoma.fxml"));
			AnchorPane pane = loader.load();
			Stage dialog = new Stage();
			dialog.initStyle(StageStyle.UTILITY);
			Scene scene = new Scene(pane);
			dialog.setScene(scene);
			dialog.showAndWait();
		} catch (IOException e) {

		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
