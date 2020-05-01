package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.DetailNote;
import nsu.android.todolist.View.NotesList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> tasks;
    NotesList notesList;

    PresenterList presenter;
    //boolean isDone;

    int tasksDone;
    int tasksNotDone;

    private static int TYPE_TEXT = 1;
    private static int TYPE_TASK = 2;

    public MyAdapter(List<Task> tasks, NotesList notesList) {
        //List<Task> wrong = new ArrayList<>();
        /*if (isDone) {
            for (Task t : tasks) {
                if (!t.getIsDone().equals("true")) {
                    wrong.add(t);
                }
            }
        } else {
            for (Task t : tasks) {
                if (!t.getIsDone().equals("false")) {
                    wrong.add(t);
                }
            }
        }

        List<Task> res = new ArrayList<>(tasks);
        for (Task t : wrong) {
            res.remove(t);
        }*/


        this.tasks = tasks;
        this.notesList = notesList;
        //this.isDone = isDone;
    }

    void setPresenter(PresenterList presenter) {
        this.presenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TASK) {
            CoordinatorLayout view = (CoordinatorLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.task_list_item, parent, false);
            return new MyViewHolder(view);
        } else {
            TextView view = (TextView) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.text_item, parent, false);
            return new TextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int i) {
        //final Task task = tasks.get(i);

        int type = getItemViewType(i);
        switch (type) {
            case TYPE_TEXT:
                ((TextViewHolder) holder).setTextDetails(tasks.get(i));
                break;
            case TYPE_TASK:
                ((MyViewHolder) holder).setTaskDetails(tasks.get(i));
                break;
        }

        /*holder.is_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.changeBox(holder.is_done.isChecked(), task.getName());
            }
        });*/

        //holder.update(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == tasksNotDone) {
            return TYPE_TEXT;
        } else {
            return TYPE_TASK;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView item_task_name;
        TextView item_short_text;
        LinearLayout linearLayout;
        CheckBox is_done;

        MyViewHolder(CoordinatorLayout v) {
            super(v);
            linearLayout = v.findViewById(R.id.linear);
            item_task_name = v.findViewById(R.id.item_task_name);
            item_short_text = v.findViewById(R.id.item_short_text);
            is_done = v.findViewById(R.id.is_done);
            linearLayout.setOnClickListener(this);

            /*if (isDone) {
                item_short_text.setPaintFlags(item_short_text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                item_short_text.setTextColor(notesList.getResources().getColor(R.color.gray));
                item_task_name.setTextColor(notesList.getResources().getColor(R.color.gray));
            }*/
        }

        void update(Task task) {
            item_task_name.setText(task.getName());
            item_short_text.setText(task.getShortText());

            if (task.getIsDone().equals("true")) {
                is_done.setChecked(true);
            } else {
                is_done.setChecked(false);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Task task = tasks.get(position);
                Intent intent = new Intent(notesList, DetailNote.class);
                intent.putExtra("name", task.getName());
                intent.putExtra("shortText", task.getShortText());
                intent.putExtra("fullText", task.getFullText());
                intent.putExtra("createDate", task.getDateCreate());
                intent.putExtra("changeDate", task.getDateChange());
                intent.putExtra("isDone", task.getIsDone());
                notesList.startActivity(intent);
            }
        }

        public void setTaskDetails(Task task) {
                
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView text;

        TextViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_item);
        }
    }

}
