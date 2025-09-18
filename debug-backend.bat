@echo off
echo Starting Spring Boot Backend in Debug Mode...
echo.
echo Profile: manual (no database required)
echo Port: 8080
echo.
cd /d "%~dp0"
mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--debug"
pause
