package dev.imb11.mru;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Stub interface to prevent crashes from older versions of Sounds.
 */
@Deprecated(since = "1.0.20+edge", forRemoval = true)
public class API {
	public API() {}

	private JsonElement get(String path) {
		return new JsonObject();
	}

	public String[] getKofiSupporters() {
		return new String[0];
	}
}