package nsu.android.todolist;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<CoordinatorLayout> mDataset;

    public MyAdapter(List<CoordinatorLayout> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        CoordinatorLayout view = (CoordinatorLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_list_item, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int i) {
        holder.coordinatorLayout = mDataset.get(i);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public CoordinatorLayout coordinatorLayout;

        public MyViewHolder(CoordinatorLayout v) {
            super(v);
            coordinatorLayout = v;
        }
    }
}
