@echo off
set "PROJECT_DIR=%~dp0"
cd /d "%PROJECT_DIR%"
call "%PROJECT_DIR%gradlew.bat" bootRun
pause