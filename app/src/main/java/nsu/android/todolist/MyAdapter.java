package nsu.android.todolist;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import nsu.android.todolist.Model.Task;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Task> tasks;

    public MyAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        CoordinatorLayout view = (CoordinatorLayout) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        Task task = tasks.get(i);
        holder.update(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_task_name;
        TextView item_short_text;
        TextView item_full_text;
        TextView item_create_data;
        TextView item_change_data;
        CheckBox is_done;

        MyViewHolder(CoordinatorLayout v) {
            super(v);
            item_task_name = v.findViewById(R.id.item_task_name);
            item_short_text = v.findViewById(R.id.item_short_text);
            item_full_text = v.findViewById(R.id.item_full_text);
            item_create_data = v.findViewById(R.id.item_create_data);
            item_change_data = v.findViewById(R.id.item_change_data);
            is_done = v.findViewById(R.id.is_done);
        }

        void update(Task task) {
            item_task_name.setText(task.getName());
            item_short_text.setText(task.getShortText());
            item_full_text.setText(task.getFullText());
            item_create_data.setText(task.getDateCreate());
            item_change_data.setText(task.getDateChange());
             if (task.getIsDone()) {
                 is_done.setChecked(true);
             } else {
                 is_done.setChecked(false);
             }
        }
    }
}
