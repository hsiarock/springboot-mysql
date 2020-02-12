
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
