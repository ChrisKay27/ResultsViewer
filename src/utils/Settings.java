package utils;

import ui.ExperimentParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Created by Chris on 1/2/2017.
 *
 */
public class Settings {

    public static void savePrefs(String preferenceName, Map<String, String> settings) {
        Preferences prefs = Preferences.userRoot().node(preferenceName);

        for (String key : settings.keySet())
            prefs.put(key, settings.get(key));

        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getSettings(String preferenceName, List<String> keys) {
        Map<String, String> settings = new HashMap<>();

        Preferences prefs = Preferences.userRoot().node(preferenceName);

        for (String key : keys)
            settings.put(key, prefs.get(key,""));

        return settings;
    }
}
