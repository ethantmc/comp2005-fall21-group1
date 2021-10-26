import java.util.ArrayList;

public class Settings {
	private ArrayList<String> settingsBuffer;
	private ArrayList<String> customNames;

	public void addBuffer(String setting) {
		settingsBuffer.add(setting);
	}
	public void addName(String name) {
		customNames.add(name);
	}
	public void applySettings() {
		//TODO: Method Stub
	}
	public void declineSettings() {
		//TODO: Finish method - May be complete depending on SettingsUI Implementation.
		settingsBuffer.clear();
		customNames.clear();
	}
}