package com.aab.dev_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDashboardDto {
    private int totalProjects;
    private int totalTasks;
    private String username;
}
