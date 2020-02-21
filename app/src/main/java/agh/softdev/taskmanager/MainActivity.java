package agh.softdev.taskmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import agh.softdev.taskmanager.adapter.TasksAdapter;
import agh.softdev.taskmanager.database.entities.Task;
import agh.softdev.taskmanager.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private RecyclerView tasksRecycler;
    private TasksAdapter tasksAdapter;
    private FloatingActionButton addTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTask = findViewById(R.id.addTask);
        tasksRecycler = findViewById(R.id.taskItems);
        tasksAdapter = new TasksAdapter();
        // setting up the recyclerview
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

        // add the on click lister to open the add new item activity
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a new Intent to get the result from the  author activity (add new task activity)
                Intent getDate = new Intent(MainActivity.this,AddTask.class);
                startActivityForResult(getDate,100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            String taskName = data.getStringExtra("name");
            String taskDesc = data.getStringExtra("desc");

            Task task = new Task(taskName,taskDesc);
            taskViewModel.insert(task);
            Toast.makeText(this, "Your Task Is Added", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Failed To Add Your Task", Toast.LENGTH_SHORT).show();
        }
    }
}
