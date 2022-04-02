package me.lorenzo.forcevent.storage;

import me.lorenzo.forcevent.ForcEvent;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class StorageConfig {
    private final String fileName;
    private final boolean saveDefaults;
    private final File dataFolder;

    private Configuration configuration;

    protected StorageConfig(String fileName, boolean saveDefaults) throws IOException {
        this.fileName = fileName;
        this.saveDefaults = saveDefaults;
        this.dataFolder = ForcEvent.getInstance().getDataFolder();

        init();
    }

    private void init() throws IOException {
        checkFile();
        this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(dataFolder, fileName));
    }

    private void checkFile() throws IOException {
        if (!dataFolder.exists())
            dataFolder.mkdir();

        File file = new File(dataFolder, fileName);

        if (!file.exists()) {
            if(saveDefaults) {
                try (InputStream in = ForcEvent.getInstance().getResourceAsStream(fileName)) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                file.createNewFile();
            }
        }
    }

    protected Configuration getConfiguration() {
        return configuration;
    }

    public void save() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(dataFolder, fileName));
    }
}
