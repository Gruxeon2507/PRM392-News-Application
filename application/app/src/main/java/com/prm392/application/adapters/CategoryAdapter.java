package com.prm392.application.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prm392.application.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<String> categoryList;
    private OnCategoryClickListener onCategoryClickListener;

    public interface OnCategoryClickListener {
        void onCategoryClick(String category);
    }

    public CategoryAdapter(List<String> categoryList, OnCategoryClickListener onCategoryClickListener) {
        this.categoryList = categoryList;
        this.onCategoryClickListener = onCategoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categoryList.get(position);
        holder.categoryTextView.setText(category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCategoryClickListener.onCategoryClick(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
