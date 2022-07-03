package ru.gretchen.accountant.mapper;

import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.model.User;
import ru.gretchen.accountant.model.dto.ReportDTO;
import ru.gretchen.accountant.model.dto.TaskDTO;
import ru.gretchen.accountant.model.dto.TeamReportDTO;
import ru.gretchen.accountant.model.dto.UserReportDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ReportMapperDecorator implements ReportMapper {

    private final ReportMapper delegate;

    public ReportMapperDecorator(ReportMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public ReportDTO fromEntity(Report report, List<User> users) {
        ReportDTO reportDTO = delegate.fromEntity(report);

        List<String> teams = users.stream().map(User::getGroup).distinct().collect(Collectors.toList());

        Map<String, List<User>> userTeamMap = users.stream().collect(Collectors.groupingBy(User::getGroup));

        reportDTO.setTeamReports(getTeamReports(report, teams, userTeamMap));
        return reportDTO;
    }

    private List<TeamReportDTO> getTeamReports(Report report, List<String> teams, Map<String, List<User>> userTeamMap){
        List<TeamReportDTO> result = new ArrayList<>();

        teams.forEach(team -> {
            TeamReportDTO teamReportDTO = new TeamReportDTO();
            teamReportDTO.setTeamName(team);

            teamReportDTO.setUserReports(getUserReports(report, userTeamMap.get(team)));
            result.add(teamReportDTO);
        });

        return result;
    }

    private List<UserReportDTO> getUserReports(Report report, List<User> teamUsers) {
        return teamUsers.stream().map(user -> {
            UserReportDTO userReportDTO = new UserReportDTO();
            userReportDTO.setFullName(user.getFullName());

            List<TaskDTO> tasksByUser = report.getTasks().stream()
                    .filter(t -> t.getChatId().equals(user.getChatId()))
                    .map(delegate::fromEntityToTask)
                    .collect(Collectors.toList());

            userReportDTO.setTasks(tasksByUser);
            return userReportDTO;
        }).collect(Collectors.toList());
    }
}
