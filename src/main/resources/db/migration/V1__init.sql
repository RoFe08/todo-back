/* =========================================
   INIT - SQL Server (Flyway)
   - task + app_user
   - BaseJpaEntity: id, created_at, updated_at
   - DATETIMEOFFSET(6) para bater com Instant/UTC
   ========================================= */

-- ======================
-- TASK
-- ======================
CREATE TABLE task (
                      id UNIQUEIDENTIFIER NOT NULL,
                      title NVARCHAR(255) NOT NULL,
                      description NVARCHAR(MAX) NULL,
                      status NVARCHAR(30) NOT NULL,

                      created_at DATETIMEOFFSET(6) NOT NULL CONSTRAINT df_task_created_at DEFAULT SYSDATETIMEOFFSET(),
                      updated_at DATETIMEOFFSET(6) NOT NULL CONSTRAINT df_task_updated_at DEFAULT SYSDATETIMEOFFSET(),

                      CONSTRAINT pk_task PRIMARY KEY (id)
);

CREATE INDEX ix_task_status ON task(status);


-- ======================
-- USER
-- ======================
CREATE TABLE app_user (
                          id UNIQUEIDENTIFIER NOT NULL,
                          name NVARCHAR(200) NOT NULL,
                          email NVARCHAR(180) NOT NULL,
                          password_hash NVARCHAR(255) NOT NULL,

                          created_at DATETIMEOFFSET(6) NOT NULL CONSTRAINT df_app_user_created_at DEFAULT SYSDATETIMEOFFSET(),
                          updated_at DATETIMEOFFSET(6) NOT NULL CONSTRAINT df_app_user_updated_at DEFAULT SYSDATETIMEOFFSET(),

                          CONSTRAINT pk_app_user PRIMARY KEY (id),
                          CONSTRAINT uq_app_user_email UNIQUE (email)
);

CREATE INDEX ix_app_user_email ON app_user(email);
