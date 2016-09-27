package matriz;

import javafx.application.Application;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class VerifyIntegrity extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		TableView<String> table = new TableView<>();
		table.setEditable(false);
		System.out.println(table.isEditable());
		method(table);
		System.out.println(table.isEditable());
	}

	private TableView<String> method(TableView<String> table) {
		table.setEditable(true);
		return table;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
