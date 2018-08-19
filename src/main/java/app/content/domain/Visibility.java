package app.content.domain;

public class Visibility {
    private String code;
    private String name;
    private boolean visible;
    private boolean friendshipRequests;

    public Visibility(String code, String name, boolean visible, boolean friendshipRequests) {
        this.code = code;
        this.name = name;
        this.visible = visible;
        this.friendshipRequests = friendshipRequests;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFriendshipRequests() {
        return friendshipRequests;
    }

    public void setFriendshipRequests(boolean friendshipRequests) {
        this.friendshipRequests = friendshipRequests;
    }
}
