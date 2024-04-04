package com.example.streaking.fortaskrecycler;

public class TaskModel {
    String taskName;
    int streakCount;
    boolean isStreakIcon;
    String lastLogin;

    public TaskModel(String taskName, int streakCount, boolean isStreakIcon, String lastLogin) {
        this.taskName = taskName;
        this.streakCount = streakCount;
        this.isStreakIcon = isStreakIcon;
        this.lastLogin = lastLogin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getStreakCount() {
        return streakCount;
    }

    public void setStreakCount(int streakCount) {
        this.streakCount = streakCount;
    }

    public TaskModel(){}

    public boolean isStreakIcon() {
        return isStreakIcon;
    }

    public void setStreakIcon(boolean streakIcon) {
        isStreakIcon = streakIcon;
    }
}
