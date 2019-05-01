package kz.aupet.vt152.diplom.Controllers;


import kz.aupet.vt152.diplom.Models.*;
import kz.aupet.vt152.diplom.dao.PlannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin
@RequestMapping("/api_planner")
public class PlannerController {

    @Autowired
    private PlannerDao plannerDao;

    @RequestMapping(value = "/saveTask", method = POST)
    public List<Task> saveTask(@RequestBody Task task){
        plannerDao.saveTasks(task);
        return plannerDao.getTasks(task.taskOwner, task.taskDate);
    }

    @RequestMapping(value = "/getTasks", method = POST)
    public List<Task> getTasks(@RequestBody GetTasksRequest getTasksRequest){
        return plannerDao.getTasks(getTasksRequest.phone, getTasksRequest.date);
    }

    @RequestMapping(value = "/deleteTask", method = POST)
    public void  deleteTask(@RequestParam("id") long taskId){
        plannerDao.deleteTask(taskId);
    }

    @RequestMapping(value = "/allStatus", method = GET)
    public List<TaskStatusModel> allStatus(){
        return TaskStatus.getAllStatus();
    }

    @RequestMapping(value = "/changeStatus", method = POST)
    public void changeStatus(@RequestBody ChangeTaskStatusRequest request){
        plannerDao.updateStatus(request.id, request.newStatus);
    }

}
