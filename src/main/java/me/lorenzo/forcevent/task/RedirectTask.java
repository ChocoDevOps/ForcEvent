package me.lorenzo.forcevent.task;

import me.lorenzo.forcevent.ForcEvent;
import me.lorenzo.forcevent.chat.Messenger;
import me.lorenzo.forcevent.chat.Placeholder;
import me.lorenzo.forcevent.handler.Settings;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.List;

public class RedirectTask implements Runnable {
    private final boolean withTimer;
    private final RedirectCallback redirectCallback;

    private int seconds;
    private final String eventServer;
    private final List<String> eligibleServers;
    private final List<Integer> eligibleRemaining;

    public RedirectTask(boolean withTimer, RedirectCallback redirectCallback) {
        this.withTimer = withTimer;
        this.redirectCallback = redirectCallback;

        this.seconds = Settings.getSettings().getDelay();
        this.eventServer = Settings.getSettings().getEventServer();
        this.eligibleServers = Settings.getSettings().getEligibleServers();
        this.eligibleRemaining = Settings.getSettings().getEligibleRemaining();
    }

    @Override
    public void run() {
        for(ProxiedPlayer proxiedPlayer : ForcEvent.getInstance().getProxy().getPlayers()) {
            if(!eligibleServers.contains(proxiedPlayer.getServer().getInfo().getName())) {
                continue;
            }

            if(!withTimer) {
                redirect(proxiedPlayer);
                Messenger.sendMessage(proxiedPlayer, "sent-message", new Placeholder("server", eventServer));
                redirectCallback.onDone();
            }

            if(eligibleRemaining.contains(seconds)) {
                Messenger.sendMessage(proxiedPlayer, "delay-message",
                        new Placeholder("server", eventServer),
                        new Placeholder("seconds", String.valueOf(seconds)));
            }

            if(seconds <= 0) {
                redirect(proxiedPlayer);
                Messenger.sendMessage(proxiedPlayer, "sent-message", new Placeholder("server", eventServer));
                redirectCallback.onDone();
            }

        }
        seconds--;
    }

    private void redirect(ProxiedPlayer proxiedPlayer) {
        proxiedPlayer.connect(ForcEvent.getInstance().getProxy().getServerInfo(eventServer));
    }
}
