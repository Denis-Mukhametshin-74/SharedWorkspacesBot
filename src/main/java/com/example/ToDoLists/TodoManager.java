package com.example.ToDoLists;

import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    private List<String> todoList;
    private List<Boolean> taskStatus;

    public TodoManager() {
        this.todoList = new ArrayList<>();
        this.taskStatus = new ArrayList<>();
    }

    public void addTask(String task) {
        todoList.add(task);
        taskStatus.add(false);
    }

    public String viewTasks() {
        if (todoList.isEmpty()) {
            return "Ваш To-Do список пуст.";
        } else {
            StringBuilder todos = new StringBuilder("Ваш To-Do список:\n");
            for (int i = 0; i < todoList.size(); i++) {
                String taskDisplay = taskStatus.get(i) ? "<s>" + todoList.get(i) + "</s>" : todoList.get(i);
                todos.append(i + 1).append(": ").append(taskDisplay).append("\n");
            }
            return todos.toString();
        }
    }

    public String deleteTask(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < todoList.size()) {
            taskStatus.remove(taskNumber);
            return "Задача удалена: " + todoList.remove(taskNumber);
        } else {
            return "Ошибка: неверный номер задачи.";
        }
    }

    public String changeTaskStatus(int taskNumber) {
        if (taskNumber >= 0 && taskNumber < taskStatus.size()) {
            boolean currentStatus = taskStatus.get(taskNumber);
            taskStatus.set(taskNumber, !currentStatus);
            return "Статус задачи " + (currentStatus ? "установлен на <b>не выполнено</b>." : "установлен на <b>выполнено</b>.");
        } else {
            return "Ошибка: неверный номер задачи.";
        }
    }    
}