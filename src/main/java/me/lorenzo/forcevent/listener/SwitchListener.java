package me.lorenzo.forcevent.listener;

import me.lorenzo.forcevent.ForcEvent;
import me.lorenzo.forcevent.handler.NetworkHandler;
import me.lorenzo.forcevent.handler.NetworkState;
import me.lorenzo.forcevent.handler.Settings;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class SwitchListener implements Listener {
    private final String lobbyName;
    private final String eventName;

    public SwitchListener() {
        this.lobbyName = Settings.getSettings().getLobbyServer();
        this.eventName = Settings.getSettings().getEventServer();
    }

    @EventHandler
    public void on(ServerSwitchEvent event) {
        if(NetworkHandler.getInstance().getNetworkState() == NetworkState.EVENT) {
            if(event.getFrom() == null) {
                return;
            }

            if(event.getFrom().getName().equals(lobbyName)) {
                if(NetworkHandler.getInstance().isStarted()) {
                    event.getPlayer().connect(ForcEvent.getInstance().getProxy().getServerInfo(eventName));
                }
            }
        }
    }

}
