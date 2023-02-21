set JAVA_HOME=C:\Program Files\Java\jdk-11.0.12

"%JAVA_HOME%\bin\java" -jar target\appliSpring-0.0.1-SNAPSHOT.jar
REM option possible:    -Dspring.profiles.active=reInit,dev,mysql
REM "%JAVA_HOME%\bin\java"  -Dspring.profiles.active=reInit,dev,mysql -jar target\appliSpring-0.0.1-SNAPSHOT.jar 
pause