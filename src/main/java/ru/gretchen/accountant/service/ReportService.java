package ru.gretchen.accountant.service;

import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.repository.ReportRepository;

import java.time.LocalDate;

public class ReportService {
    private final ReportRepository reportRepository;
    private final TaskService taskService;

    public ReportService(ReportRepository reportRepository, TaskService taskService) {
        this.reportRepository = reportRepository;
        this.taskService = taskService;
    }

    public Report create() {
        Report report = new Report();
        report.setDate(LocalDate.now());
        report.setTasks(taskService.getByDateNow());
        return reportRepository.save(report);
    }
}
