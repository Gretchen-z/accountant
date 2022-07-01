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
    report_id bigint
);

CREATE TABLE "user"
(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username varchar(50),
    name varchar(50),
    last_name varchar(50),
    team varchar(15),
    task_id bigint
);

ALTER TABLE task ADD CONSTRAINT fk_task_report FOREIGN KEY (report_id)
    REFERENCES report;

ALTER TABLE "user" ADD CONSTRAINT fk_user_task FOREIGN KEY (task_id)
    REFERENCES task;
