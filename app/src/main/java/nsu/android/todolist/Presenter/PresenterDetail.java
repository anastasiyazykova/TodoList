package nsu.android.todolist.Presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
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

        if (b.getString("isDone").equals("true")) {
            detailNote.checkBox.setChecked(true);
        } else {
            detailNote.checkBox.setChecked(false);
        }

        detailNote.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBox(detailNote.checkBox.isChecked(), detailNote.name.getText().toString());
            }
        });
    }

    private void changeBox(boolean b, String name) {
        List<Task> tasks = sqliteStorage.getAllTasks();
        Task task = null;
        for (Task t : tasks) {
            if (name.equals(t.getName())) {
                task = t;
            }
        }

        if (b) {
            task.setIsDone("true");
        } else {
            task.setIsDone("false");
        }

        sqliteStorage.updateTask(name, task);
        //recreateEvent();
    }

    public void deleteEvent(String name) {
        sqliteStorage.deleteTask(name);
    }

    public void onChangeEvent() {
        Intent intent = new Intent(detailNote, CreateNoteActivity.class);
        intent.putExtra("name", detailNote.name.getText().toString());
        intent.putExtra("shortText", detailNote.shortText.getText().toString());
        intent.putExtra("fullText", detailNote.fullText.getText().toString());
        intent.putExtra("oldDate", detailNote.createDate.getText().toString());

        detailNote.startActivityForResult(intent, 1);
    }

    public void sendEvent() {
        Intent sendIntent = new Intent();
        //sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, detailNote.fullText.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, detailNote.name.getText().toString());
        sendIntent.setType("text/plain");
        detailNote.startActivity(Intent.createChooser(sendIntent,"Поделиться"));
    }
}
