package org.example.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private Boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        this.isEdited = false;
        this.dateTime = LocalDateTime.now();
    }

    public void updateEditeDatetime() {
        this.isEdited = true;
        this.dateTime = LocalDateTime.now();
    }

    public Boolean getIsEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
