CREATE TABLE task (
                      id UNIQUEIDENTIFIER NOT NULL,
                      title NVARCHAR(255) NOT NULL,
                      description NVARCHAR(MAX) NULL,
                      status NVARCHAR(30) NOT NULL,
                      created_at DATETIME2 NOT NULL,
                      CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE INDEX ix_task_status ON task(status);
