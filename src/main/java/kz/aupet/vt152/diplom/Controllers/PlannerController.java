package kz.aupet.vt152.diplom.Controllers;


import kz.aupet.vt152.diplom.Models.GetTasksRequest;
import kz.aupet.vt152.diplom.Models.Task;
import kz.aupet.vt152.diplom.dao.PlannerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

}
