package me.lorenzo.forcevent;

import me.lorenzo.forcevent.chat.Messenger;
import me.lorenzo.forcevent.command.ForceventCommand;
import me.lorenzo.forcevent.handler.NetworkHandler;
import me.lorenzo.forcevent.handler.NetworkState;
import me.lorenzo.forcevent.handler.Settings;
import me.lorenzo.forcevent.listener.SwitchListener;
import me.lorenzo.forcevent.storage.impl.MessageStorage;
import me.lorenzo.forcevent.storage.impl.SettingStorage;
import me.lorenzo.forcevent.task.RedirectTask;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public final class ForcEvent extends Plugin {
    private static ForcEvent instance;

    public static ForcEvent getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        Messenger.init();
        Settings.getSettings();

        registerCommands();
        registerListeners();
    }

    private void registerCommands() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();

        pluginManager.registerCommand(this, new ForceventCommand("forcevent"));
    }

    private void registerListeners() {
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerListener(this, new SwitchListener());
    }
}

