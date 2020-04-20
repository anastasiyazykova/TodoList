package nsu.android.todolist.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteStorage {
    private static final String LOG_TAG = SQLiteStorage.class.getCanonicalName();

    private final Context appContext;
    private final SQLiteDatabase database;

    public SQLiteStorage(final Context context) {
        this.appContext = context.getApplicationContext();
        this.database = new NotesContract(appContext).getWritableDatabase();
        // close
    }

    /*public void addUser(final Task task) {
        ContentValues contentValues = getContentValues(task);
        database.insert(AppDBSchema.TaskTable.NAME, null, contentValues);
    }*/

    /*public List<Task> getAllUsers() {
        final ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = database.query(AppDBSchema.TaskTable.NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            final int nameColumnIndex = cursor.getColumnIndex(AppDBSchema.TaskTable.Columns.NAME);
            final int ageColumnIndex = cursor.getColumnIndex(AppDBSchema.TaskTable.Columns.AGE);

            do {
                final String name = cursor.getString(nameColumnIndex);
                final int age = cursor.getInt(ageColumnIndex);
                final Task task = new Task(name, age);
                tasks.add(task);
            } while (cursor.moveToNext());
        } else {
            Log.d(LOG_TAG, "There are 0 rows in table.");
        }

        cursor.close();
        return tasks;
    }*/

    /*public void deleteAllUsers() {
        final int count = database.delete(AppDBSchema.TaskTable.NAME, null, null);
        Log.d(LOG_TAG, "" + count + "was successfully removed.");
    }*/

    /*private ContentValues getContentValues(final Task task) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AppDBSchema.TaskTable.Columns.UUID, task.getUuid());
        contentValues.put(AppDBSchema.TaskTable.Columns.NAME, task.getName());
        contentValues.put(AppDBSchema.TaskTable.Columns.AGE, task.getAge());

        return contentValues;
    }*/
}
