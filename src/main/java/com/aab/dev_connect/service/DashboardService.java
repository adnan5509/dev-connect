package com.aab.dev_connect.service;


import com.aab.dev_connect.dto.DashboardDataResponse;
import com.aab.dev_connect.enums.TaskStatus;
import com.aab.dev_connect.exception.ResourceNotFoundException;
import com.aab.dev_connect.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final UserRepository userRepository;

    public DashboardService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DashboardDataResponse getDashboardData(final String username) {
        int totalProjects = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")).getProjects().size();
        int totalTasks = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")).getTasks().size();
        int completedTasks =
                (int) userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found")).getTasks().stream()
                .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                .count();

        return new DashboardDataResponse(totalProjects, totalTasks, completedTasks);
    }
}
