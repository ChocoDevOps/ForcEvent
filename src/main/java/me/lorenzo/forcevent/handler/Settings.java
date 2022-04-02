package me.lorenzo.forcevent.handler;

import me.lorenzo.forcevent.storage.impl.SettingStorage;

import java.io.IOException;
import java.util.List;

public class Settings {
    private static Settings settings;

    private SettingStorage settingStorage;

    public static Settings getSettings() {
        if(settings == null)
            settings = new Settings();
        return settings;
    }

    private Settings() {
        try {
            settingStorage = new SettingStorage();
        } catch (IOException e) {
            System.out.println("Failed to initialize settings");
        }
    }

    public String getLobbyServer() {
        return settingStorage.getLobbyServer();
    }

    public String getEventServer() {
        return settingStorage.getEventServer();
    }

    public int getDelay() {
        return settingStorage.getDelay();
    }

    public List<String> getEligibleServers() {
        return settingStorage.getEligibleServers();
    }

    public List<Integer> getEligibleRemaining() {
        return settingStorage.getEligibleRemaining();
    }
}
