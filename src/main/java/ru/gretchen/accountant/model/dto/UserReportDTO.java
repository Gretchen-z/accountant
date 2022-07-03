package ru.gretchen.accountant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReportDTO {
    private String fullName;
    private List<TaskDTO> tasks;
}
