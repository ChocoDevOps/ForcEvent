package me.lorenzo.forcevent.storage.impl;

import me.lorenzo.forcevent.storage.StorageConfig;

import java.io.IOException;

public class MessageStorage extends StorageConfig {
    public MessageStorage() throws IOException {
        super("messages.yml", true);
    }

    public String getMessage(String path) {
        return this.getConfiguration().getString(path);
    }
}
