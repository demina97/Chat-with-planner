package kz.aupet.vt152.diplom.dao;


import kz.aupet.vt152.diplom.Models.Task;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PlannerDao {
    @Select("select idtask as taskId, texttask as taskText, " +
            " statustask as taskStatus, ownertask as taskOwner, " +
            " datetask as taskDate" +
            " from planner where ownertask=#{phone} and date_trunc('day'::VARCHAR, datetask::TIMESTAMP) =  date_trunc('day'::VARCHAR, #{taskDate}::TIMESTAMP)")
    public List<Task> getTasks(@Param("phone") String phone, @Param("taskDate") Date taskDate);

    @Insert("insert into planner (texttask, statustask, ownertask, datetask)\n" +
            "values (#{task.taskText}, #{task.taskStatus}, #{task.taskOwner}, #{task.taskDate})")
    public void saveTasks(@Param("task") Task task);

    @Delete("delete from planner" +
            " where idtask = #{taskId}")
    public void deleteTask(long taskId);
}
