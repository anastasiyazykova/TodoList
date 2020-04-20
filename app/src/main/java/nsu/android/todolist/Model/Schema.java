package nsu.android.todolist.Model;

public class Schema {

    public static final class TaskTable {

        public static final String NAME = "tasks";

        public static final class Columns {

            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DATE_CREATE = "dateCreate";
            public static final String DATE_CHANGE = "dateChange";
            public static final String SHORT_TEXT = "shortText";
            public static final String FULL_TEXT = "fullText";
            public static final String IS_DONE = "isDone";
        }
    }
}
