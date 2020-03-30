package com.example.myactivity;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;


    TaskRepository(Application application) {
       MyActivityDB db = MyActivityDB.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        MyActivityDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }
    void update(Task task) {
        MyActivityDB.databaseWriteExecutor.execute(() -> {
            mTaskDao.update(task);
        });
    }
}
