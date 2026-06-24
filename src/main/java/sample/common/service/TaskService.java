package sample.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample.common.dao.entity.Task;
import sample.common.dao.mapper.TaskMapper;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    public List<Task> findAll() {
        return taskMapper.findAll();
    }

    public List<Task> findByUsername(String username) {
        return taskMapper.findByUsername(username);
    }

    public List<Task> search(Task task) {
        return taskMapper.search(task);
    }

    public List<Task> findByUsernameWithPaging(String username, int page, int size) {
        int offset = (page - 1) * size;
        return taskMapper.findByUsernameWithPaging(username, size, offset);
    }

    public int countByUsername(String username) {
        return taskMapper.countByUsername(username);
    }

    public void insert(Task task) {
        taskMapper.insert(task);
    }

    public Task findById(Long id) {
        return taskMapper.findById(id);
    }

    public Task findByIdAndUsername(Long id, String username) {
        return taskMapper.findByIdAndUsername(id, username);
    }

    public void update(Task task) {
        taskMapper.update(task);
    }

    public void delete(Long id) {
        taskMapper.delete(id);
    }

    public void deleteByIdAndUsername(Long id, String username) {
        taskMapper.deleteByIdAndUsername(id, username);
    }
}