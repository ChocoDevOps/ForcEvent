package me.lorenzo.forcevent.handler;

public enum NetworkState {
    ROUTINE("state.routine"),
    EVENT("state.event");

    private String path;
    NetworkState(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
