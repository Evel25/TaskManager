package agh.softdev.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    private EditText newTaskName,newTaskDesc;
    private Button btnAddTask,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        newTaskName = findViewById(R.id.newTaskName);
        newTaskDesc = findViewById(R.id.newTaskDesc);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnCancel = findViewById(R.id.btnCancelTask);

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newTaskDesc.getText().toString();
                String desc = newTaskDesc.getText().toString();
                if(name.isEmpty() || desc.isEmpty()){
                    Toast.makeText(AddTask.this, "Please Enter Both The Task Name And Description", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent();
                intent.putExtra("name",name);
                intent.putExtra("desc",desc);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
