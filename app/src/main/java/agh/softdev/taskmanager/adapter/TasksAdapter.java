package agh.softdev.taskmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import agh.softdev.taskmanager.R;
import agh.softdev.taskmanager.database.entities.Task;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskHolder> {

    private List<Task> tasks = new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item =  LayoutInflater.from(parent.getContext()).inflate(R.layout.task_recycler_item,parent,false);
        return new TaskHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.taskName.setText(tasks.get(position).getTitle());
        holder.taskDesc.setText(tasks.get(position).getDesctiprion());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public Task getTask(int position){
        return tasks.get(position);
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
        this.notifyDataSetChanged();
    }

    class TaskHolder extends RecyclerView.ViewHolder{

        private TextView taskName,taskDesc;

        private TaskHolder(@NonNull View itemView) {
            super(itemView);
            this.taskName = itemView.findViewById(R.id.taskName);
            this.taskDesc = itemView.findViewById(R.id.taskDesc);
            // add the on click listener on the recycler item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.onIemClick(tasks.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        public void onIemClick(Task task);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }
}
