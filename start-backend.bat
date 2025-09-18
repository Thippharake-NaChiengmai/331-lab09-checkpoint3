@echo off
echo Starting Spring Boot Backend...
cd /d "%~dp0"
mvnw.cmd spring-boot:run
pause
