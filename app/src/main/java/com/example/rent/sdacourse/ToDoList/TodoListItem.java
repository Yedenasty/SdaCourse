package com.example.rent.sdacourse.ToDoList;

/**
 * Created by RENT on 2017-02-23.
 */

public class TodoListItem {

    private String text;
    private boolean isChecked;

    public TodoListItem(String text, boolean isChecked) {
        this.text = text;

    }

    public TodoListItem(String text) {
        this.text = text;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


}
