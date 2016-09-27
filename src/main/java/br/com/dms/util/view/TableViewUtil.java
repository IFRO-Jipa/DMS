package br.com.dms.util.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewUtil {

	public static String[][] createArray(int lines, int columns) {
		return new String[lines][columns];
	}

	public static String[][] createAndPopulateArray(int lines, int columns, String defaultValue) {
		String[][] array = createArray(lines, columns);

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = defaultValue;
			}
		}

		return array;

	}

	public static ObservableList<String[]> getObservableForArray(String[][] array) {
		return FXCollections.observableArrayList(array);
	}

	public static TableView<String[]> getTableViewForArray(String[][] array, boolean editable) {

		return prepareTableView(new TableView<String[]>(), array, editable);
	}

	public static TableView<String[]> prepareTableView(TableView<String[]> table, String[][] array, boolean editable) {

		table.setEditable(editable);
		table.getColumns().clear();

		for (int i = 0; i < array[0].length; i++) {
			TableColumn<String[], String> tc = new TableColumn<>(array[0][i]);
			final int colNo = i;
			tc.setCellFactory(new TextFieldCellFactory());
			tc.setCellValueFactory(p -> {
				String[] x = p.getValue();
				return new SimpleStringProperty(x != null && x.length > 0 ? x[colNo] : "0");
			});

			tc.setPrefWidth(90);
			table.getColumns().add(tc);
		}

		table.setItems(getObservableForArray(array));

		return table;
	}

	public static TableView<String[]> prepareTableView(TableView<String[]> table, double[][] array, boolean editable) {

		String[][] result = new String[array.length][array[0].length];

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				result[i][j] = String.valueOf(array[i][j]);
			}
		}

		return prepareTableView(table, result, editable);
	}

}
