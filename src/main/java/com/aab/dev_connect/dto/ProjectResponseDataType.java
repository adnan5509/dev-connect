package com.aab.dev_connect.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDataType extends ProjectForTaskDataType {


    private List<TaskForProjectDataType> tasks;
}
