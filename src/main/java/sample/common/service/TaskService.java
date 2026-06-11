package sample.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public void insert(Task task) {
        taskMapper.insert(task);
    }

    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    public void update(Task task) {
        taskMapper.update(task);
    }

    public void delete(Long id) {
        taskMapper.delete(id);
    }
}