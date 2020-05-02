package nsu.android.todolist.Model;

class Schema {

    static final class TaskTable {

        static final String NAME = "tasks";

        static final class Columns {

            static final String UUID = "uuid";
            static String NAME = "name";
            static String DATE_CREATE = "dateCreate";
            static String DATE_CHANGE = "dateChange";
            static String SHORT_TEXT = "shortText";
            static String FULL_TEXT = "fullText";
            static String IS_DONE = "isDone";
        }
    }
}
