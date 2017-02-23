package com.example.rent.sdacourse.ToDoList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rent.sdacourse.R;

import java.util.ArrayList;
import java.util.List;


public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {


    List<TodoListItem> items = new ArrayList<>();

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final TodoListItem listItem = items.get(position);
        holder.textView.setText(items.get(position).getText());
        holder.checkBox.setChecked(listItem.isChecked());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listItem.setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(String item) {
        items.add(new TodoListItem(item));
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }

    }

    public  void deleteAllCheckedItems() {
        List<TodoListItem> newListItems = new ArrayList<>(items);

        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isChecked()) {
                newListItems.add(items.get(i));
            }
        }
        items = newListItems;
        notifyDataSetChanged();
    }

}
