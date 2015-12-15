@ECHO OFF

IF "%~1"=="" GOTO HELP
IF NOT "%~2"=="" GOTO HELP

SET JAVA_PATH=%~1\bin
ECHO %JAVA_PATH%

"%JAVA_PATH%\javac.exe" -cp "lib/*" "-Acom.google.java.contract.classpath=lib/cofoja.asm-1.2-20140817.jar;lib/commons-lang3-3.4.jar" "-Acom.google.java.contract.classoutput=bin" -g -d bin src/Bug.java src/Bugzilla.java src/ProgramTest.java src/BugzillaException.java src/BugStateException.java src/TestRunner.java

"%JAVA_PATH%\java.exe" "-javaagent:lib/cofoja.asm-1.2-20140817.jar" -cp "bin;lib/*" TestRunner

GOTO END	

:HELP
ECHO "USAGE: .\testrun.bat <path to JDK>"

:END