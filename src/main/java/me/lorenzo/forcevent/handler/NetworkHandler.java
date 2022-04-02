package me.lorenzo.forcevent.handler;

public class NetworkHandler {
    private static NetworkHandler instance;

    private boolean started;

    private NetworkState networkState;

    public static NetworkHandler getInstance() {
        if(instance == null)
            instance = new NetworkHandler();
        return instance;
    }

    private NetworkHandler() {
        started = false;
        this.networkState = NetworkState.ROUTINE;
    }

    public NetworkState getNetworkState() {
        return networkState;
    }

    public NetworkState toggleNetworkState() {
        if(this.networkState == NetworkState.EVENT) {
            this.networkState = NetworkState.ROUTINE;
        } else {
            this.networkState = NetworkState.EVENT;
        }
        return this.networkState;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
