package com.aab.dev_connect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskForProjectDataType {

    private Long id;

    private String title;
    private String description;
    private String status;

}
