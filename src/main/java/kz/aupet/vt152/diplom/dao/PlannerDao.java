package kz.aupet.vt152.diplom.dao;


import kz.aupet.vt152.diplom.Models.Task;
import kz.aupet.vt152.diplom.Models.TaskStatus;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PlannerDao { //интерфейс объекта доступа к данным планировщика
    @Select("select idtask as taskId, texttask as taskText, " +
            " statustask as taskStatus, ownertask as taskOwner, " +
            " datetask as taskDate" +
            " from planner where ownertask=#{phone} and date_trunc('day'::VARCHAR, datetask::TIMESTAMP) " +
            " =  date_trunc('day'::VARCHAR, #{taskDate}::TIMESTAMP)" +
            " order by idtask")
    public List<Task> getTasks(@Param("phone") String phone, @Param("taskDate") Date taskDate); //описание функции
                                                                                                // выборки задач
    @Insert("insert into planner (texttask, statustask, ownertask, datetask)\n" +
            "values (#{task.taskText}, #{task.taskStatus}, #{task.taskOwner}, #{task.taskDate})")
    public void saveTasks(@Param("task") Task task);  // описание функции сохранения задачи

    @Delete("delete from planner" +
            " where idtask = #{taskId}")
    public void deleteTask(long taskId);    // описание функции удаления задачи

    @Update("update planner set statustask=#{newStatus} where idtask=#{taskId}")
    void updateStatus(@Param("taskId") double id, @Param("newStatus") TaskStatus newStatus);   //описание функции
                                                                                            //обновления статуса задачи
}


