package com.prm392.crud;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {
    private ArrayList<Student> students;
    private OnDeleteClickListener onDeleteClickListener;

    public StudentAdapter(Context context, ArrayList<Student> students, OnDeleteClickListener onDeleteClickListener) {
        super(context, 0, students);
        this.students = students;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.studentName);
        TextView ageTextView = convertView.findViewById(R.id.studentAge);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        nameTextView.setText(student.getName());
        ageTextView.setText(String.valueOf(student.getAge()));

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onDeleteClick(position);
            }
        });

        return convertView;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
