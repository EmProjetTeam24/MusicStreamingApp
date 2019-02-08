package project.pierremarclaforest.musicstreamingapp;

//  number = 1 -> Currently playing notification
// 2 can be for example when finished downloading ...
public class Notification {

    enum Type{
        CURRENTLY_PLAYING_NOTIF, UNKNOWN;
    }

    public Type type = Type.UNKNOWN;
    public String artist = null;
    public String track  = null;
    public String album  = null;
}
