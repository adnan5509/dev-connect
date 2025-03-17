package com.aab.dev_connect.dto;

import com.aab.dev_connect.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateRequestDataType extends TaskRequestDataType {

    private TaskStatus status;
}
