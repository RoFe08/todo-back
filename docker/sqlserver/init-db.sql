IF NOT EXISTS (
    SELECT name
    FROM sys.databases
    WHERE name = N'todo_db'
)
BEGIN
    CREATE DATABASE todo_db;
END
GO
