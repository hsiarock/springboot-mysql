package com.example.demo.dao;

import com.example.demo.model.StateCode;
import com.example.demo.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author David Hsia
 */
@Repository
@Slf4j
public class StudentJdbctemplateDAOImpl implements StudentJdbctemplateDAO {

	// some JDBC-backed DAO class...
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate myJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    this.myJdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveStudent(List<Student> students) {

		//Add one student, ignore studentId from input object
		String sql = "INSERT INTO student (studentname, studentbranch, studentemail) values (?, ?, ?)";
		AtomicInteger counter = new AtomicInteger(0);

		log.info("Bedore insert there are students to be added: " + students.size());

		// insert each student from list
		students.stream().forEach(student -> {

			String sName = student.getStudentname();
			String sBranch = student.getStudentbranch();
			String sEmail = student.getStudentemail();

			myJdbcTemplate.update(sql, sName, sBranch, sEmail);

			//Fetch student id (it's AUTO_INCREMENT) via new branch and name
			int studentId = getStudentBynameBranch(sName, sBranch);
			if (studentId < 0) {
				log.info("Failed to insert student name: " + sName + " branch: " + sBranch);
			} else {
				log.info("Succefully insert student id: " + studentId + " name: " + sName +
						" branch: " + sBranch);
				counter.getAndIncrement();
			}
		});

		log.info("after forEach, there are student been added: " + counter.get());

		if (students.size() != counter.get()) {
			return false;
		} else {
			return true;
		}

	}

	public List<Student> getStudents() {

		String sql = "select studentid, studentbranch, studentemail, studentname from student";

		if (this.myJdbcTemplate == null) {
			System.out.println("myJdbcTemplate is null");
			return null;
		}

		List<Student> result =  this.myJdbcTemplate.query(sql,
		        new RowMapper<Student>(){
		            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Student student = Student.builder()
		                	.studentid(rs.getInt("studentid"))
		                	.studentbranch(rs.getString("studentbranch"))
		                	.studentemail(rs.getString("studentemail"))
		                	.studentname(rs.getString("studentname"))
		                	.build();

		                return student;
		            }
		});

		return result;

	}

	@Override
	public Student getStudentById(int studentid) {

		String sql = "select studentid, studentbranch, studentemail, studentname "
				    + "from student where studentid = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", studentid);

		if (this.namedParameterJdbcTemplate == null) {
			System.out.println("namedParameterJdbcTemplate is null");
			return null;
		}

		Optional<Student> result = this.namedParameterJdbcTemplate.query(sql, namedParameters,
				new DemoResultExtractor());

		return result.orElse(new Student(0, "", "", ""));
	}

	@Override
	public int getStudentBynameBranch(String name, String branch) {

		String sql = "select studentid, studentbranch, studentemail, studentname from student " +
					     "where studentname = :name and studentbranch = :branch";
		SqlParameterSource namedParameters =
				new MapSqlParameterSource("branch", branch).addValue("name", name);

		// watch !!! because DemoResultExtractor() is working on all 4 fields....
		// so, your sql to use DemoResultExtractor() must select all 4 fields too
		Optional<Student> result = this.namedParameterJdbcTemplate.query(sql, namedParameters,
				new DemoResultExtractor());

		return result.orElseGet(() -> new Student(-1, "", "", "")).getStudentid();
	}

	@Override
	public boolean deleteStudent(Student student) {
		//for now only use studentId
		String sql = "DELETE FROM student WHERE studentid = ?";
		return myJdbcTemplate.update(sql, student.getStudentid()) > 0;
	}

	@Override
	public boolean updateStudent(Student student) {
		//for now only use studentId
		String sql =
		"UPDATE student SET studentbranch = ?, studentname = ?, studentemail = ? WHERE studentid = ?";
		return myJdbcTemplate.update(sql,
				                     student.getStudentbranch(),
				                     student.getStudentname(),
				                     student.getStudentemail(),
				                     student.getStudentid()) > 0;
	}

	@Override
	public List<StateCode> getStateCode() {
		String sql = "select name, status from lookupstatecode";

		if (myJdbcTemplate == null) {
			System.out.println("myJdbcTemplate is null");
			return null;
		}

		List<StateCode> result = myJdbcTemplate.query(sql, new RowMapper<StateCode>() {
			public StateCode mapRow(ResultSet rs, int rowNum) throws SQLException {
				// add into statecode cache
				StateCode stcode = StateCode.builder()
						.name(rs.getString("name"))
						.status(rs.getString("status"))
						.build();
				return stcode;
			}
		});

		return result;
	}

}
