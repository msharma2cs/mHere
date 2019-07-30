package msharma.net.mhere.models;

import java.util.Objects;

public class Event {

    private Integer id;

    private String title;

    private String description;

    private Double lat;

    private Double lon;

    private Integer geoRadius;

    private String eventDate;

    private String startTime;

    private String endTime;

    private String createdAt;

    private Long createdById;

    private String createdByLogin;

    public Event() {}

    public Event(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Event(Integer id, String title, String description, Double lat, Double lon, Integer geoRadius, String eventDate, String startTime, String endTime, String createdAt, Long createdById, String createdByLogin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.geoRadius = geoRadius;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
        this.createdById = createdById;
        this.createdByLogin = createdByLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getGeoRadius() {
        return geoRadius;
    }

    public void setGeoRadius(Integer geoRadius) {
        this.geoRadius = geoRadius;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByLogin() {
        return createdByLogin;
    }

    public void setCreatedByLogin(String createdByLogin) {
        this.createdByLogin = createdByLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(title, event.title) &&
                Objects.equals(description, event.description) &&
                Objects.equals(lat, event.lat) &&
                Objects.equals(lon, event.lon) &&
                Objects.equals(geoRadius, event.geoRadius) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(startTime, event.startTime) &&
                Objects.equals(endTime, event.endTime) &&
                Objects.equals(createdAt, event.createdAt) &&
                Objects.equals(createdById, event.createdById) &&
                Objects.equals(createdByLogin, event.createdByLogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, description, lat, lon, geoRadius, eventDate, startTime, endTime, createdAt, createdById, createdByLogin);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", geoRadius=" + geoRadius +
                ", eventDate=" + eventDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", createdAt=" + createdAt +
                ", createdById=" + createdById +
                ", createdByLogin='" + createdByLogin + '\'' +
                '}';
    }

}
