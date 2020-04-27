package nsu.android.todolist.Presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.CreateNoteActivity;
import nsu.android.todolist.View.DetailNote;

public class PresenterDetail {
    private SQLiteStorage sqliteStorage;
    DetailNote detailNote;

    public PresenterDetail(DetailNote detailNote) {
        this.detailNote = detailNote;
        detailNote.name = detailNote.findViewById(R.id.detail_task_name);
        detailNote.shortText = detailNote.findViewById(R.id.detail_short_text);
        detailNote.fullText = detailNote.findViewById(R.id.detail_full_text);
        detailNote.createDate = detailNote.findViewById(R.id.detail_data_create);
        detailNote.changeDate = detailNote.findViewById(R.id.detail_data_change);
        detailNote.checkBox = detailNote.findViewById(R.id.detail_is_done);
        setModel();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(detailNote);
    }

    public void setData(Bundle b) {
        detailNote.name.setText(b.getString("name"));
        detailNote.shortText.setText(b.getString("shortText"));
        detailNote.fullText.setText(b.getString("fullText"));
        detailNote.createDate.setText(b.getString("createDate"));
        detailNote.changeDate.setText(b.getString("changeDate"));

        boolean isDone = Boolean.parseBoolean(b.getString("isDone"));
        if (isDone) {
            detailNote.checkBox.setChecked(true);
        } else {
            detailNote.checkBox.setChecked(false);
        }
        fixUuid();
    }

    public void deleteEvent(String name) {
        sqliteStorage.deleteTask(name);
    }

    public void fixUuid() {
        detailNote.oldName = detailNote.name.getText().toString();

        List<Task> tasks = sqliteStorage.getAllTasks();
        Task myTask = null;
        for (Task t : tasks) {
            if (t.getName().equals(detailNote.oldName)) {
                myTask = t;
            }
        }

        detailNote.uuid = myTask.getUuid();
    }

    public void onChangeEvent() {
        Intent intent = new Intent(detailNote, CreateNoteActivity.class);
        intent.putExtra("name", detailNote.name.getText().toString());
        intent.putExtra("shortText", detailNote.shortText.getText().toString());
        intent.putExtra("fullText", detailNote.fullText.getText().toString());
        intent.putExtra("oldDate", detailNote.createDate.getText().toString());
        fixUuid();
        detailNote.startActivity(intent);
    }

    public void updateEvent() {
        Task task = sqliteStorage.getTaskByUuid(detailNote.uuid);

        detailNote.name.setText(task.getName());
        detailNote.shortText.setText(task.getShortText());
        detailNote.fullText.setText(task.getFullText());
        detailNote.createDate.setText(task.getDateCreate());
        detailNote.changeDate.setText(task.getDateChange());

        boolean isDone = task.getIsDone();
        if (isDone) {
            detailNote.checkBox.setChecked(true);
        } else {
            detailNote.checkBox.setChecked(false);
        }
    }
}
