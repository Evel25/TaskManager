package agh.softdev.taskmanager.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    public Task(){ }
    public Task(String title, String desctiprion) {
        this.title = title;
        Desctiprion = desctiprion;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String Desctiprion;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDesctiprion() {
        return Desctiprion;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesctiprion(String desctiprion) {
        Desctiprion = desctiprion;
    }
}
