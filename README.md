# Service Oriented Architecture Implementation - RESTful Vehicle Database Web Service #
======================================================================================

At first I would like to apologise for using the older platform of the IDE and database servers. I was following some tutorials online and kinda carried aways using the similar platform as the tutorial was about 5 years old. By the time I finished with the assignment I realised that converting / adjusting the entire project to adjust with the newer version of the platforms will require doing the project from the beginning. I will keep this in mind and will use the latest and and more compact IDE for my future projects. This version will not work on OSx as I used the Oracle Database Server for my database and Oracle does not provide any database server support for latest OSx. 

## Requirements: (more like softwares I used) ##
* Operating Sytem: ___Windows 7___
* IDE: ___Eclipse___
* Server: ___Oracle WebLogic Server 12c (12.1.2)___
* Database: ___Oracle Database Server 11g___
* Developer: ___Oracle SQL Developer Version 4.0.3.16___
* Web Browser: ___Firefox (for testing with console used Chrome)___

### Please follow the setup requirements in order to run the web application: ###

1. Download the project zip from the ___[Github Repository](https://github.com/samiulsaki/unik4730.git)___

2. Unzip the zip file

3. ___I have used Eclipse as IDE. The application can be imported to any version of Eclipse.___

4. Select Java EE as perspective.

5. Select the server tab on the bottom of the IDE and right click and choose New>Server. From the menu choose Oracle Oracle WebLogic Server 12c (12.1.2 not the latest one). Again I like apologise for using such older platform. Press next and choose C:/Middleware/wlserver/ as the WebLogic Home and create a new server on the next window (Domain directory> Create Domain). Hit finish.

6. Install Oracle Middleware Fusion with Oracle database server 11g (or higher) and setup a new database (while installing) with orcl as database name (can be something else you like), and system (again up to you) as user name and password.

7. Now start the weblogic server from the Eclipse and got to any web browser and enter http://localhost:7001/console . This will open the console and enter the username and password (if you have not change anything on step 5 then its ___weblogic:welcome1___ by default). Select import data sources from services menu and choose new>generic data source. Give data source any name but please choose ___vehicle.Oracle___ as JNDI Name (thats what I used in my program). Hit next and choose the ___Oracle driver (THIN) for Instant Connections;Versions:9.0.1 and later___ (avoid selecting THIN XA one) as driver name. Hit next and uncheck Supports Global Transactions (we don’t need it). Hit next and choose orcl as database name (or something else you chose). Enter localhost and host name and 1521 as the port. Enter system as database user name and password for it. Hit next. You can test the connection on the next page. If all connection is ok (green light) hit next (not finish). Check AdminServer on the next page and hit finish. You are now connected to the database.
Right click on the weblogic server on Eclipse and select add/remove and add the com.vehicle.rest project to it.

8. Import the zip file from Eclipse(File>Import>General>Existing projects int workspace). Import the project(I recommend to create the project step by step by understanding the codes). Finally press finish to import the project. 

9. Download SQL developer or similar software to connect to the orcl database using the system as username and password (default is always oracle) and import the ___vehicleTable.sql___ to the orcl database from the /Database folder in the repository. Easy way to do this is right click the orcl database connection and open the select open SQL worksheet. Inside the worksheet just copy and paste the content of the vehicleTable.sql file and  and hit Run Script or hit F5. That will import the VEHICLE_REGS table to the database (important for the web application). Check the link for more info: https://docs.oracle.com/cd/E17781_01/server.112/e18804/impexp.htm#BABJIIIE

10. Finally start (or restart) the weblogic server from Eclipse. Open a web browser and enter http://localhost:7001/com.vehicle.rest/ and take it from there. Readme.html file will give you some more informations.

# Happy Browsing #
