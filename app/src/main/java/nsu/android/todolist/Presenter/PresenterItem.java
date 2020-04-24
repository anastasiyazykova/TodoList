package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.TextView;

import java.io.Serializable;

import nsu.android.todolist.Model.SQLiteStorage;
import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.DetailNote;
import nsu.android.todolist.View.ItemActivity;
import nsu.android.todolist.View.NotesList;

public class PresenterItem {
    private SQLiteStorage sqliteStorage;
    ItemActivity itemActivity;

    public PresenterItem(ItemActivity itemActivity) {
        this.itemActivity = itemActivity;
        setModel();
    }

    public void setModel() {
        sqliteStorage = new SQLiteStorage(itemActivity);
    }

    public void detailsEvent() {
        TextView t = itemActivity.findViewById(R.id.item_task_name);
        String s = t.getText().toString();
        Task task = sqliteStorage.getTask(s);

        Intent intent = new Intent(itemActivity, DetailNote.class);
        intent.putExtra("name", task.getName());
        intent.putExtra("shortText", task.getShortText());
        intent.putExtra("fullText", task.getFullText());
        intent.putExtra("createDate", task.getDateCreate());
        intent.putExtra("changeDate", task.getDateChange());
        intent.putExtra("isDone", task.getIsDone());
        itemActivity.startActivity(intent);
    }
}
