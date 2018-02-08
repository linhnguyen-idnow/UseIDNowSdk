package de.idnow.sampleproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.idnow.R;
import de.idnow.sampleproject.model.Task;

/**
 * Created by sean on 12/28/17.
 */

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.MyViewHolder> {

    private List<Task> todoList;

    private ClickListener clickListener;

    public TodoListAdapter(List<Task> todoList) {
        this.todoList = todoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = todoList.get(position);
        holder.name.setText(task.getName());
        holder.description.setText(task.getDescription());
    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.todo_name)
        TextView name;

        @BindView(R.id.todo_description)
        TextView description;

        @BindView((R.id.todo_remove_button))
        ImageView removeButton;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            removeButton.setOnClickListener(new View.OnClickListener() {4
                @Override
                public void onClick(View v) {
                    clickListener.onClick(v, getAdapterPosition());
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    clickListener.onLongClick(v, getAdapterPosition());
                    return true;
                }
            });
        }

    }


    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }
}
