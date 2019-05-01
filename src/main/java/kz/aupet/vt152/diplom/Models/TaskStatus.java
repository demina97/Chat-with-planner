package kz.aupet.vt152.diplom.Models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TaskStatus  {
    NEW("Новая"),
    WORK("В работе"),
    STOP("Приостановлена"),
    DONE("Выполнена");

    private String title;

    TaskStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static List<TaskStatusModel> getAllStatus(){
        return Arrays.stream(TaskStatus.values())
                .map(taskStatus -> new TaskStatusModel(taskStatus.name(), taskStatus.getTitle()))
                .collect(Collectors.toList());
    }
}
