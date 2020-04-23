package nsu.android.todolist.Model;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

public final class Task {
    private final String uuid;
    private final String dateCreate;

    private String dateChange;
    private String name;
    private String shortText;
    private String fullText;
    private boolean isDone;


    public Task(final String dateCreate, String dateChange,
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

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDateChange() {
        return dateChange;
    }

    public String getShortText() {
        return shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getName() {
        return name;
    }

}
