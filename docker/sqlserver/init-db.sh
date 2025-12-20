#!/bin/bash
set -e

echo "⏳ Waiting for SQL Server to be available..."

until /opt/mssql-tools18/bin/sqlcmd \
    -S localhost \
    -U sa \
    -P "$MSSQL_SA_PASSWORD" \
    -Q "SELECT 1" \
    -C > /dev/null 2>&1
do
  sleep 2
done

echo "✅ SQL Server is up. Creating database if not exists..."

/opt/mssql-tools18/bin/sqlcmd \
    -S localhost \
    -U sa \
    -P "$MSSQL_SA_PASSWORD" \
    -i /docker-entrypoint-initdb.d/init-db.sql \
    -C

echo "🎉 Database todo_db is ready."
