package sample.thymeleaf.web;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sample.common.dao.entity.Task;
import sample.common.service.TaskService;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public String tasks(@ModelAttribute Task searchTask,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        Model model,
                        HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loginUser");
        searchTask.setUsername(username);

        model.addAttribute("tasks", taskService.search(searchTask));
        model.addAttribute("searchTask", searchTask);

        return "tasks";
    }

    @GetMapping("/tasks/new")
    public String newTask(Model model, HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        model.addAttribute("task", new Task());
        return "task_new";
    }

    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            model.addAttribute("errorMessage", "タイトルは必須です");
            model.addAttribute("task", task);
            return "task_new";
        }

        if (task.getTitle().length() > 100) {
            model.addAttribute("errorMessage", "タイトルは100文字以内で入力してください");
            model.addAttribute("task", task);
            return "task_new";
        }

        if (task.getContent() != null && task.getContent().length() > 1000) {
            model.addAttribute("errorMessage", "内容は1000文字以内で入力してください");
            model.addAttribute("task", task);
            return "task_new";
        }

        if (task.getStartDate() != null
                && task.getEndDate() != null
                && task.getStartDate().isAfter(task.getEndDate())) {

            model.addAttribute("errorMessage", "開始日は終了日以前にしてください");
            model.addAttribute("task", task);
            return "task_new";
        }

        String username = (String) session.getAttribute("loginUser");
        task.setUsername(username);

        taskService.insert(task);

        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable("id") Long id,
                           Model model,
                           HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loginUser");

        Task task = taskService.findByIdAndUsername(id, username);

        if (task == null) {
            return "redirect:/tasks";
        }

        model.addAttribute("task", task);

        return "task_edit";
    }

    @PostMapping("/tasks/edit/{id}")
    public String updateTask(@PathVariable("id") Long id,
                             @ModelAttribute Task task,
                             Model model,
                             HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        if (task.getTitle() == null || task.getTitle().isBlank()) {
            model.addAttribute("errorMessage", "タイトルは必須です");
            task.setId(id);
            model.addAttribute("task", task);
            return "task_edit";
        }

        if (task.getTitle().length() > 100) {
            model.addAttribute("errorMessage", "タイトルは100文字以内で入力してください");
            task.setId(id);
            model.addAttribute("task", task);
            return "task_edit";
        }

        if (task.getContent() != null && task.getContent().length() > 1000) {
            model.addAttribute("errorMessage", "内容は1000文字以内で入力してください");
            task.setId(id);
            model.addAttribute("task", task);
            return "task_edit";
        }

        if (task.getStartDate() != null
                && task.getEndDate() != null
                && task.getStartDate().isAfter(task.getEndDate())) {

            model.addAttribute("errorMessage", "開始日は終了日以前にしてください");
            task.setId(id);
            model.addAttribute("task", task);
            return "task_edit";
        }

        String username = (String) session.getAttribute("loginUser");

        task.setId(id);
        task.setUsername(username);

        taskService.update(task);

        return "redirect:/tasks";
    }

    @PostMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id,
                             HttpSession session) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("loginUser");

        taskService.deleteByIdAndUsername(id, username);

        return "redirect:/tasks";
    }
}