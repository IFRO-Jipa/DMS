package br.com.dms.util;

import java.net.URL;

public class ViewLocation {

	public static URL getLocation(Location location) {
		return ViewLocation.class.getClassLoader()
				.getResource(String.format("fxml/%s.fxml", location.get().trim()));
	}

}
