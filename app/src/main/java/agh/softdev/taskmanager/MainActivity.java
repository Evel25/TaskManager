package agh.softdev.taskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import agh.softdev.taskmanager.database.entities.Task;
import agh.softdev.taskmanager.viewmodel.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        // getting data from the livedata object
        taskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                Toast.makeText(MainActivity.this, "The Database Is Created And Data Has Been Changer", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
