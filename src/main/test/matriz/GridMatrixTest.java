package matriz;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridMatrixTest extends Application {

	@Override
	public void start(Stage stage) {
		BorderPane pane = new BorderPane();

		GridPane grid = new GridPane();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				TextField txt = new TextField(String.format("[%d,%d]", i, j));
				grid.add(txt, i, j);
			}
		}

		pane.setCenter(grid);

		Button btn = new Button("Imprimir");
		btn.setOnAction(ev -> {
			int rows = grid.impl_getRowCount();
			int columns = grid.impl_getColumnCount();

			String[][] valores = new String[rows][columns];
			ObservableList<Node> textFields = grid.getChildrenUnmodifiable();
			textFields.forEach(textField -> {
				int column = GridPane.getColumnIndex(textField);
				int row = GridPane.getRowIndex(textField);

				TextField obj = (TextField) textField;
				valores[row][column] = obj.getText();
			});

			for (int i = 0; i < valores.length; i++)
				for (int j = 0; j < valores[i].length; j++) {
					System.out.printf("[%d,%d]=%s\n", i, j, valores[i][j]);
				}

			grid.getChildren().clear();
		});

		pane.setBottom(btn);

		stage.setScene(new Scene(pane));
		stage.setTitle("Matrizes");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
