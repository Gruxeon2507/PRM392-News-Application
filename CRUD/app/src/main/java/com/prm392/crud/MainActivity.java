package com.prm392.crud;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StudentAdapter.OnDeleteClickListener {

    private ArrayList<Student> students;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        students = new ArrayList<>();
        adapter = new StudentAdapter(this, students, this);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddStudentDialog();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showEditStudentDialog(position);
            }
        });
    }

    private void showAddStudentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Student");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_student, null);
        builder.setView(view);

        EditText nameEditText = view.findViewById(R.id.nameEditText);
        EditText ageEditText = view.findViewById(R.id.ageEditText);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                students.add(new Student(name, age));
                adapter.notifyDataSetChanged();
                showNotification("Student Added", "A new student has been added.");
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    private void showEditStudentDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Student");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_student, null);
        builder.setView(view);

        EditText nameEditText = view.findViewById(R.id.nameEditText);
        EditText ageEditText = view.findViewById(R.id.ageEditText);

        Student student = students.get(position);
        nameEditText.setText(student.getName());
        ageEditText.setText(String.valueOf(student.getAge()));

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                student.setName(nameEditText.getText().toString());
                student.setAge(Integer.parseInt(ageEditText.getText().toString()));
                adapter.notifyDataSetChanged();
                showNotification("Student Updated", "The student details have been updated.");
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default", "Default", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "default")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notification)
                .setAutoCancel(true);

        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    public void onDeleteClick(int position) {
        showDeleteStudentDialog(position);
    }

    private void showDeleteStudentDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Student");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                students.remove(position);
                adapter.notifyDataSetChanged();
                showNotification("Student Deleted", "The student has been deleted.");
            }
        });

        builder.setNegativeButton("Cancel", null);

        builder.show();
    }
}
