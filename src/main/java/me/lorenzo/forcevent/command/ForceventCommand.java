package me.lorenzo.forcevent.command;

import com.google.common.graph.Network;
import me.lorenzo.forcevent.ForcEvent;
import me.lorenzo.forcevent.chat.Messenger;
import me.lorenzo.forcevent.chat.Placeholder;
import me.lorenzo.forcevent.handler.NetworkHandler;
import me.lorenzo.forcevent.handler.NetworkState;
import me.lorenzo.forcevent.task.RedirectCallback;
import me.lorenzo.forcevent.task.RedirectTask;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ForceventCommand extends Command {
    public ForceventCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission("server.forcetp")) {
            NetworkHandler networkHandler = NetworkHandler.getInstance();

            NetworkState networkState = networkHandler.toggleNetworkState();
            Placeholder routine = new Placeholder("state", Messenger.getMessage(networkState.getPath()));

            Messenger.sendMessage(sender, "state-switched", routine);

            if(networkState == NetworkState.EVENT) {
                AtomicInteger taskId = new AtomicInteger(0);
                taskId.set(ForcEvent.getInstance().getProxy().getScheduler().schedule(ForcEvent.getInstance(),
                        new RedirectTask(true, new RedirectCallback() {
                            @Override
                            public void onDone() {
                                ForcEvent.getInstance().getProxy().getScheduler().cancel(taskId.get());
                                NetworkHandler.getInstance().setStarted(true);
                            }
                        }), 1, 1, TimeUnit.SECONDS).getId());
            } else {
                NetworkHandler.getInstance().setStarted(false);
            }
        } else {
            sender.sendMessage("No permissions");
        }
    }
}
