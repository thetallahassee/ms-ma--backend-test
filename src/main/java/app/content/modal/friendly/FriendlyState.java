package app.content.modal.friendly;

import app.content.modal.user.User;

public class FriendlyState {
    private User requestingUser;//solicitador
    private User requestedUser;//solicitado
    private int friendState;
    private int rejections = 0;

    public FriendlyState(User requestingUser, User requestedUser, int friendState) {
        this.requestingUser = requestingUser;
        this.requestedUser = requestedUser;
        this.friendState = friendState;
    }

    public User getRequestingUser() {
        return requestingUser;
    }

    public void setRequestingUser(User requestingUser) {
        this.requestingUser = requestingUser;
    }

    public User getRequestedUser() {
        return requestedUser;
    }

    public void setRequestedUser(User requestedUser) {
        this.requestedUser = requestedUser;
    }

    public int getFriendState() {
        return friendState;
    }

    public void setFriendState(int friendState) {
        this.friendState = friendState;
    }
}
