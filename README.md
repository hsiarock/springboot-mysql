
A project to demo Spring boot and MySql using JdbcTemplate for CRUD


springboot-mysql - github project.
	git clone https://github.com/hsiarock/springboot-mysql.git

Then, import into Eclipse

im mysql, create 'student' table

CREATE TABLE student (
     studentId MEDIUMINT NOT NULL AUTO_INCREMENT,
     studentName VARCHAR(255) NOT NULL,
	 studentBranch VARCHAR(255) NOT NULL,
	 studentEmail VARCHAR(255),
     PRIMARY KEY (studentId)
);

INSERT Into student (studentName, studentBranch,studentEmail)
   VALUES ( "abc", "BCA", "abc@abc.com");

INSERT Into student (studentName, studentBranch,studentEmail)
   VALUES ( "efg", "MCA", "efg@efg.com");


Add postgre branch

3/8/2020 -fix com.puppycrawl.tools's checkStype 8.29 version 
          in maven checkstyle.plug.versioin 3.1 
   1. comment out the deprecate module, such as 
            <!-- property name="allowMissingThrowsTags" value="true"/ -->
            <!-- property name="allowThrowsTagsForSubclasses" value="true"/ -->
            <!-- property name="allowUndeclaredRTE" value="true"/ -->
            <!-- This check sometimes failed for with "Unable to get class information for @throws tag" for custom exceptions -->
            <!-- property name="suppressLoadErrors" value="true"/ -->
   2. move <module name="LineLength"> 
      move the LineLength element to be a direct descendent of <Checker> 

