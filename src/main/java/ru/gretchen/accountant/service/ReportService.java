package ru.gretchen.accountant.service;

import ru.gretchen.accountant.exception.ReportNotCreateException;
import ru.gretchen.accountant.model.Report;
import ru.gretchen.accountant.repository.ReportRepository;

import java.time.LocalDate;

/**
 * Сервис для работы с Report repository
 */
public class ReportService {
    private final ReportRepository reportRepository;
    private final TaskService taskService;

    public ReportService(ReportRepository reportRepository, TaskService taskService) {
        this.reportRepository = reportRepository;
        this.taskService = taskService;
    }

    /**
     * Метод для формирования и сохранения Report в БД
     * @return Report
     */
    public Report create() {
        try {
            Report report = new Report();
            report.setDate(LocalDate.now());
            report.setTasks(taskService.getByDateNow());
            return reportRepository.save(report);
        } catch (Exception e) {
            throw new ReportNotCreateException(e.getMessage());
        }
    }
}
