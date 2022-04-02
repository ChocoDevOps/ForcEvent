package me.lorenzo.forcevent.storage.impl;

import me.lorenzo.forcevent.storage.StorageConfig;

import java.io.IOException;
import java.util.List;

public class SettingStorage extends StorageConfig {

    public SettingStorage() throws IOException {
        super("settings.yml", true);
    }

    public String getEventServer() {
        return getConfiguration().getString("target-server");
    }

    public int getDelay() {
        return getConfiguration().getInt("command-delay");
    }

    public List<String> getEligibleServers() {
        return getConfiguration().getStringList("eligible-servers");
    }

    public List<Integer> getEligibleRemaining() { return getConfiguration().getIntList("messages-on-remaining"); }

    public String getLobbyServer() { return getConfiguration().getString("lobby-server"); }
}
