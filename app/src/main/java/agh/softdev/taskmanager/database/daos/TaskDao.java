package agh.softdev.taskmanager.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import agh.softdev.taskmanager.database.entities.Task;

@Dao
public interface TaskDao {
    @Insert
    void insert(Task task);
    @Update
    void update(Task task);
    @Delete
    void delete(Task task);
    @Query("DELETE FROM TASKS")
    void deleteAll();
    @Query("SELECT * FROM TASKS")
    LiveData<List<Task>> getAll();
}
