set projectLocation=C:\Automation_POC\CucumberTestNG
 
cd %projectLocation%
 
set classpath=C:\Automation_POC\CucumberTestNG\lib\*
 

java org.testng.TestNG %projectLocation%\testng.xml
 
pause