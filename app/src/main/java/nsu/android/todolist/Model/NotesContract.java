package nsu.android.todolist.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public final class NotesContract extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "notes.db";

    public NotesContract(final Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + Schema.TaskTable.NAME + "(" +
                " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Schema.TaskTable.Columns.UUID + ", " +
                Schema.TaskTable.Columns.NAME + ", " +
                Schema.TaskTable.Columns.DATE_CREATE + ", " +
                Schema.TaskTable.Columns.DATE_CHANGE + ", " +
                Schema.TaskTable.Columns.SHORT_TEXT + ", " +
                Schema.TaskTable.Columns.FULL_TEXT + ", " +
                Schema.TaskTable.Columns.IS_DONE +
                ")"
        );
    }

    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int i, final int i1) {
        //
    }
}
