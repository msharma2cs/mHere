package msharma.net.mhere.models;

import java.util.ArrayList;

public class AddUserEvent {

    private Integer id;

    private Integer eventId;

    private Integer userId;

    public AddUserEvent() {}

    public AddUserEvent(Integer eventId, Integer userId) {
        this.eventId = eventId;
        this.userId = userId;
    }

    public AddUserEvent(Integer id, Integer eventId, Integer userId) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer users) {
        this.userId = users;
    }

    @Override
    public String toString() {
        return "AddUserEvent{" +
                "eventId=" + eventId +
                ", userId=" + userId +
                '}';
    }

}
