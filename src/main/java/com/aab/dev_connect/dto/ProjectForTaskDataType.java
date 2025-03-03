package com.aab.dev_connect.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForTaskDataType {

    private Long id;

    private String name;

    private String description;

    private Long ownerId;

}
