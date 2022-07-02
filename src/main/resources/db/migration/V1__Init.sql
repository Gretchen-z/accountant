CREATE SCHEMA IF NOT EXISTS postgres;

ALTER DATABASE
    "tgbot_task-report" SET search_path = postgres;

CREATE TABLE "report"
(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    date date
);

CREATE TABLE "task"
(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    description varchar(200),
    time_in_minutes int,
    date date,
    chat_id varchar(15),
    report_id bigint
);

ALTER TABLE task ADD CONSTRAINT fk_task_report FOREIGN KEY (report_id)
    REFERENCES report;
