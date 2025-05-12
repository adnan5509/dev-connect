package com.aab.dev_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDataResponse {

    private int totalProjects;
    private int totalTasks;
    private int completedTasks;


}
