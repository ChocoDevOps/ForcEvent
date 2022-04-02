package me.lorenzo.forcevent.chat;

import me.lorenzo.forcevent.storage.impl.MessageStorage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.IOException;

public class Messenger {
    private static MessageStorage messageStorage;

    public static void init() {
        try {
            messageStorage = new MessageStorage();
        } catch (IOException e) {
            System.out.println("Failed to initialize messenger");
        }
    }

    public static void sendMessage(CommandSender sender, String path, Placeholder... placeholders) {
        String message = ChatColor.translateAlternateColorCodes('&', messageStorage.getMessage(path));

        for (Placeholder placeholder : placeholders) {
            String key = placeholder.getKey().startsWith("{") ? placeholder.getKey() : "{" + placeholder.getKey() + "}";
            message = message.replace(key, placeholder.getValue());
        }

        ComponentBuilder componentBuilder = new ComponentBuilder(message);
        BaseComponent[] baseComponent = componentBuilder.create();

        sender.sendMessage(baseComponent);
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', messageStorage.getMessage(path));
    }
}
