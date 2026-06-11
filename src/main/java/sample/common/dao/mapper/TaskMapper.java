package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    void insert(Task task);

    Task findById(Long id);

    void update(Task task);

    void delete(Long id);
}