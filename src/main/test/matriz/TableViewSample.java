package matriz;

import br.com.dms.util.view.TableViewUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TableViewSample extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();

		String[][] array = TableViewUtil.createAndPopulateArray(3, 3, "0");

		TableView<String[]> table = TableViewUtil.getTableViewForArray(array, true);

		root.getChildren().add(table);
		Button btn = new Button("Mostrar");
		btn.setOnAction(ev -> {
			table.getItems().forEach(items -> {
				for (String item : items) {
					System.out.println(item);

				}
			});
		});
		root.getChildren().add(btn);
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
	}

}