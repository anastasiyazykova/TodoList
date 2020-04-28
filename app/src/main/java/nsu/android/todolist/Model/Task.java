package nsu.android.todolist.Model;

import java.util.UUID;

public final class Task {
    private String uuid;
    private final String dateCreate;

    private String dateChange;
    private String name;
    private String shortText;
    private String fullText;
    private String isDone;


    public Task(final String dateCreate, String dateChange,
                String name, String shortText, String fullText, String isDone) {
        this.uuid = UUID.randomUUID().toString();
        this.dateCreate = dateCreate;
        this.dateChange = dateChange;

        this.name = name;
        this.shortText = shortText;
        this.fullText = fullText;
        this.isDone = isDone;

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

    public String getIsDone() {
        return isDone;
    }

    public String getName() {
        return name;
    }

    public void setIsDone(String b) {
        isDone = b;
    }

    public void setName(String s) {
        name = s;
    }

    public void setShortText(String s) {
        shortText = s;
    }

    public void setFullText(String s) {
        fullText = s;
    }

    public void setChangeDate(String s) {
        dateChange = s;
    }

}
