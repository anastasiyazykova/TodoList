package nsu.android.todolist.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SQLiteStorage {
    private static final String LOG_TAG = SQLiteStorage.class.getCanonicalName();

    private final SQLiteDatabase database;

    public SQLiteStorage(final Context context) {
        Context appContext = context.getApplicationContext();
        this.database = new NotesContract(appContext).getWritableDatabase();
        // close
    }

    public void addTask(Task task) {
        ContentValues contentValues = getContentValues(task);
        database.insert(Schema.TaskTable.NAME, null, contentValues);
    }

    public List<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = database.query(Schema.TaskTable.NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.NAME);
            final int dateCreateColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.DATE_CREATE);
            int dateChangeColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.DATE_CHANGE);
            int shortTextColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.SHORT_TEXT);
            int fullTextColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.FULL_TEXT);
            int isDoneColumnIndex = cursor.getColumnIndex(Schema.TaskTable.Columns.IS_DONE);
            do {
                String name = cursor.getString(nameColumnIndex);
                String dateCreate = cursor.getString(dateCreateColumnIndex);
                String dateChange = cursor.getString(dateChangeColumnIndex);
                String shortText = cursor.getString(shortTextColumnIndex);
                String fullText = cursor.getString(fullTextColumnIndex);
                String isDone = cursor.getString(isDoneColumnIndex);

                Task task = new Task(dateCreate, dateChange, name, shortText, fullText, isDone);

                tasks.add(task);
            } while (cursor.moveToNext());
        } else {
            Log.d(LOG_TAG, "There are 0 rows in table.");
        }

        cursor.close();
        return tasks;
    }

    /*public void deleteAllUsers() {
        final int count = database.delete(AppDBSchema.TaskTable.NAME, null, null);
        Log.d(LOG_TAG, "" + count + "was successfully removed.");
    }*/

    private ContentValues getContentValues(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TaskTable.Columns.NAME, task.getName());
        contentValues.put(Schema.TaskTable.Columns.DATE_CREATE, task.getDateCreate());
        contentValues.put(Schema.TaskTable.Columns.DATE_CHANGE, task.getDateChange());
        contentValues.put(Schema.TaskTable.Columns.SHORT_TEXT, task.getShortText());
        contentValues.put(Schema.TaskTable.Columns.FULL_TEXT, task.getFullText());
        contentValues.put(Schema.TaskTable.Columns.IS_DONE, task.getIsDone());
        return contentValues;
    }

    public void deleteTask(String name) {
        database.delete(Schema.TaskTable.NAME, Schema.TaskTable.Columns.NAME + " = ?", new String[]{name});
    }

    public void updateTask(String name, Task myTask) {
        deleteTask(name);
        addTask(myTask);
    }
}
