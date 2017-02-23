package com.example.rent.sdacourse.ToDoList;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rent.sdacourse.R;


public class ToDoListActivity extends AppCompatActivity {

    private ToDoListAdapter todoListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoListAdapter = new ToDoListAdapter();
        recyclerView.setAdapter(todoListAdapter);

        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoListAdapter.addItem(editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todolist_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_item) {
            todoListAdapter.deleteAllCheckedItems();
        }
        return super.onOptionsItemSelected(item);
    }
}
