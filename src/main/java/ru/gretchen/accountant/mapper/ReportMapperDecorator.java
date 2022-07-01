package ru.gretchen.accountant.mapper;

import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.Task;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.model.dto.ReportDTO;
import ru.gretchen.accountant.model.dto.TaskDTO;
import ru.gretchen.accountant.model.dto.TeamReportDTO;
import ru.gretchen.accountant.model.dto.UserReportDTO;
import ru.gretchen.accountant.model.enumeration.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ReportMapperDecorator implements ReportMapper {

    private final ReportMapper delegate;

    public ReportMapperDecorator(ReportMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ReportDTO fromEntity(Report entity) {
        ReportDTO reportDTO = delegate.fromEntity(entity);
        reportDTO.setTeamReports(new ArrayList<>());

        Map<Team, List<Task>> teamTaskMap = entity.getTasks().stream()
                .collect(Collectors.groupingBy(t -> t.getUser().getTeam()));

        Set<Team> teamsSet = teamTaskMap.keySet();

        teamsSet.forEach( team -> {
            TeamReportDTO teamReportDTO = new TeamReportDTO();
            teamReportDTO.setTeamName(team.toString());
            teamReportDTO.setUserReports(new ArrayList<>());

            List<Task> teamTasks = teamTaskMap.get(team);

            Map<User, List<Task>> userTaskMap = teamTasks.stream()
                    .collect(Collectors.groupingBy(Task::getUser));

            userTaskMap.keySet().forEach(u -> {
                UserReportDTO userReportDTO = new UserReportDTO();
                userReportDTO.setName(u.getName());
                userReportDTO.setLastName(u.getLastName());

                List<Task> userTasks = userTaskMap.get(u);
                List<TaskDTO> userTasksDto  = new ArrayList<>();

                userTasks.forEach(t -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setDescription(t.getDescription());
                    taskDTO.setTimeInMinutes(t.getTimeInMinutes());
                    userTasksDto.add(taskDTO);
                });

                userReportDTO.setTasks(userTasksDto);
            });

            reportDTO.getTeamReports().add(teamReportDTO);
        });

        return reportDTO;
    }
}
