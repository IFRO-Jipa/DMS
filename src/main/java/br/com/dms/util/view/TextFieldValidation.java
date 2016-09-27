package br.com.dms.util.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextFieldValidation {
	public static ChangeListener<String> getValidatorForNumbers(TextField text) {
		return new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					text.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		};
	}
}
