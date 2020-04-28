package nsu.android.todolist.Presenter;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import nsu.android.todolist.Model.Task;
import nsu.android.todolist.R;
import nsu.android.todolist.View.DetailNote;
import nsu.android.todolist.View.NotesList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> tasks;
    NotesList notesList;

    PresenterList presenter;

    boolean[] checked;

    public MyAdapter(List<Task> tasks, NotesList notesList) {
        this.tasks = tasks;
        this.notesList = notesList;
        checked = new boolean[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            checked[i] = tasks.get(i).getIsDone().equals("true");
        }
    }

    void setPresenter(PresenterList presenter) {
        this.presenter = presenter;
    }

    /*public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        CoordinatorLayout view = (CoordinatorLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int i) {
        final Task task = tasks.get(i);

        holder.is_done.setChecked(checked[i]);
        holder.is_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checked[i] = !checked[i];
                presenter.changeBox(checked[i], task.getName());
            }
        });

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
        CheckBox is_done;

        MyViewHolder(CoordinatorLayout v) {
            super(v);
            linearLayout = v.findViewById(R.id.linear);
            item_task_name = v.findViewById(R.id.item_task_name);
            item_short_text = v.findViewById(R.id.item_short_text);
            is_done = v.findViewById(R.id.is_done);
            linearLayout.setOnClickListener(this);
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
    }
}
