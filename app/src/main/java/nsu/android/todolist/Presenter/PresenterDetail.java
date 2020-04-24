package nsu.android.todolist.Presenter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.TextView;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.R;
import nsu.android.todolist.View.CreateNoteActivity;
import nsu.android.todolist.View.DetailNote;
import nsu.android.todolist.View.ItemActivity;

public class PresenterDetail {
    private SQLiteStorage sqliteStorage;
    DetailNote detailNote;

    public PresenterDetail(DetailNote detailNote) {
        this.detailNote = detailNote;
        setModel();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(detailNote);
    }

    public void setData(Intent intent) {
        detailNote.name = detailNote.findViewById(R.id.detail_task_name);
        detailNote.shortText = detailNote.findViewById(R.id.detail_short_text);
        detailNote.fullText = detailNote.findViewById(R.id.detail_full_text);
        detailNote.createDate = detailNote.findViewById(R.id.detail_data_create);
        detailNote.changeDate = detailNote.findViewById(R.id.detail_data_change);
        detailNote.checkBox = detailNote.findViewById(R.id.detail_is_done);

        detailNote.name.setText(intent.getStringExtra("name"));
        detailNote.shortText.setText(intent.getStringExtra("shortText"));
        detailNote.fullText.setText(intent.getStringExtra("fullText"));
        detailNote.createDate.setText(intent.getStringExtra("createDate"));
        detailNote.changeDate.setText(intent.getStringExtra("changeDate"));

        boolean isDone = Boolean.parseBoolean(intent.getStringExtra("isDone"));
        if (isDone) {
            detailNote.checkBox.setChecked(true);
        } else {
            detailNote.checkBox.setChecked(false);
        }

    }

    public void deleteEvent(String name) {
        sqliteStorage.deleteTask(name);

    }
}
