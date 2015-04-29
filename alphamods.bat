

if "%1"=="delete" (goto delete) else (goto copy)

:delete
del C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\alphamods.bat
exit /B
:copy

copy /B alphamods.bat "C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\alphamods.bat"
pause