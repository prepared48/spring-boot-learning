@echo off
color 0b
set "JAVA_HOME=C:\Program Files\Java\jdk1.8.0_141"
java -Dfile.encoding=utf-8 -Xmx1024M -Xms512M -classpath .;..\lib\* com.prepared.HotReloadApplication
echo. & pause
