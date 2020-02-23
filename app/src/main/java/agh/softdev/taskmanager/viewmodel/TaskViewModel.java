package agh.softdev.taskmanager.viewmodel;

import android.app.Application;
import android.text.method.MultiTapKeyListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import agh.softdev.taskmanager.database.entities.Task;
import agh.softdev.taskmanager.repository.TaskRepo;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepo repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepo(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void update(Task task){
        repository.update(task);
    }

    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }
}
