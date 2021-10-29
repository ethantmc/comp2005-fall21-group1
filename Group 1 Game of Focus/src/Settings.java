import java.util.ArrayList;

public class Settings {
	private ArrayList<String> settingsBuffer; //note that other than invalid setting combinations being entered, our use cases do not actually require a buffer.
	private ArrayList<String> customNames;

	public void addBuffer(String setting) {
		settingsBuffer.add(setting);
	}
	public void addName(String name) {
		customNames.add(name);
	}
	public void applySettings() {
		//TODO: Method Stub
		settingsBuffer.stream()
		.forEach( this::doAnySetting );
	}
	public void declineSettings() {
		//TODO: Finish method - May be complete depending on SettingsUI Implementation.
		settingsBuffer.clear();
		customNames.clear();
	}

	public void doAnySetting(String setting) {
		//large collection of ifs
		if (setting == "toggleFullscreen")
		{
			; //do nothing, right now. We'll need to change this when we have other Jframes, I think. 
		}
	}
}