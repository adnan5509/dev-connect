package com.aab.dev_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDataType {

    private String title;
    private String description;
    // For creation, the project ID can be provided via the URL or here:
    private Long projectId;
}
