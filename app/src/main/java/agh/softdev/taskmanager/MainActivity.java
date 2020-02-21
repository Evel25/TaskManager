package agh.softdev.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import agh.softdev.taskmanager.adapter.TasksAdapter;
import agh.softdev.taskmanager.database.entities.Task;
import agh.softdev.taskmanager.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private RecyclerView tasksRecycler;
    private TasksAdapter tasksAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksRecycler = findViewById(R.id.taskItems);
        tasksAdapter = new TasksAdapter();
        tasksRecycler.setLayoutManager(new LinearLayoutManager(this));
        tasksRecycler.setAdapter(tasksAdapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        // getting data from the livedata object
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                tasksAdapter.setTasks(tasks);
            }
        });

    }
}
