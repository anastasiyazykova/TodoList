package nsu.android.todolist.Model;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

public final class Task {
    private final String uuid;
    private final Date dateCreate;
    private final Date dateChange;

    private String name;
    private String shortText;
    private String fullText;
    private boolean isDone;


    public Task(final Date dateCreate, final Date dateChange,
                String name, String shortText, String fullText, boolean isDone) {
        this.uuid = UUID.randomUUID().toString();
        this.dateCreate = dateCreate;
        this.dateChange = dateChange;

        this.name = name;
        this.shortText = shortText;
        this.fullText = fullText;
        this.isDone = isDone;

    }

    public String getUuid() {
        return uuid;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public String shortText() {
        return shortText;
    }

    public String fullText() {
        return fullText;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getName() {
        return name;
    }

}
