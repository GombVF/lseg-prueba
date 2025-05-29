use lsegtest;

create table tasks(
    id uuid primary key,
    title varchar(50) not null,
    description varchar(255),
    due_date datetime(3),
    status enum('pending', 'in-progress', 'completed')
);
