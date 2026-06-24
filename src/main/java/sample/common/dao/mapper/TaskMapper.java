package sample.common.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sample.common.dao.entity.Task;

@Mapper
public interface TaskMapper {

    List<Task> findAll();

    List<Task> findByUsername(String username);

    List<Task> search(Task task);

    List<Task> findByUsernameWithPaging(
            @Param("username") String username,
            @Param("limit") int limit,
            @Param("offset") int offset);

    int countByUsername(String username);

    void insert(Task task);

    Task findById(Long id);

    Task findByIdAndUsername(
            @Param("id") Long id,
            @Param("username") String username);

    void update(Task task);

    void delete(Long id);

    void deleteByIdAndUsername(
            @Param("id") Long id,
            @Param("username") String username);
}