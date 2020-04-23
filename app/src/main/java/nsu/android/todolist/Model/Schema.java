package nsu.android.todolist.Model;

public class Schema {

    public static final class TaskTable {

        public static final String NAME = "tasks";

        public static final class Columns {

            public static final String UUID = "uuid";
            public static String NAME = "name";
            public static final String DATE_CREATE = "dateCreate";
            public static String DATE_CHANGE = "dateChange";
            public static String SHORT_TEXT = "shortText";
            public static String FULL_TEXT = "fullText";
            public static String IS_DONE = "isDone";
        }
    }
}
