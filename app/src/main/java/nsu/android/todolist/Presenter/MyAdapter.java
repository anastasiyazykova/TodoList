package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.DetailNote;
import nsu.android.todolist.View.NotesList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> tasks;
    NotesList notesList;

    public MyAdapter(List<Task> tasks, NotesList notesList) {
        this.tasks = tasks;
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        CoordinatorLayout view = (CoordinatorLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int i) {
        final Task task = tasks.get(i);
        holder.update(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item_task_name;
        TextView item_short_text;
        LinearLayout linearLayout;
        //TextView item_full_text;
        //TextView item_create_data;
        //TextView item_change_data;
        CheckBox is_done;

        MyViewHolder(CoordinatorLayout v) {
            super(v);
            linearLayout = v.findViewById(R.id.linear);
            item_task_name = v.findViewById(R.id.item_task_name);
            item_short_text = v.findViewById(R.id.item_short_text);
            //item_full_text = v.findViewById(R.id.item_full_text);
            //item_create_data = v.findViewById(R.id.item_create_data);
            //item_change_data = v.findViewById(R.id.item_change_data);
            is_done = v.findViewById(R.id.is_done);
            linearLayout.setOnClickListener(this);
            is_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notesList.changedTask();
                }
            });
        }

        void update(Task task) {
            item_task_name.setText(task.getName());
            item_short_text.setText(task.getShortText());
            //item_full_text.setText(task.getFullText());
            //item_create_data.setText(task.getDateCreate());
            //item_change_data.setText(task.getDateChange());
             if (task.getIsDone()) {
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
    }
}
