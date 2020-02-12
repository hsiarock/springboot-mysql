package com.example.demo.dao;

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

		//Add one student, ignore student_id from input object
		String sql = "INSERT INTO student (studentName, studentBranch, studentEmail) values (?, ?, ?)";
		AtomicInteger counter = new AtomicInteger(0);

		log.info("Bedore insert there are students to be added: " + students.size());

		// insert each student from list
		students.stream().forEach(student -> {

			String sName = student.getStudentName();
			String sBranch = student.getStudentBranch();
			String sEmail = student.getStudentEmail();

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

		String sql = "select studentId, studentBranch, studentEmail, studentName from student ";

		if (this.myJdbcTemplate == null) {
			System.out.println("myJdbcTemplate is null");
			return null;
		}

		List<Student> result =  this.myJdbcTemplate.query(sql,
		        new RowMapper<Student>(){
		            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Student student = Student.builder()
		                	.studentId(rs.getInt("studentId"))
		                	.studentBranch(rs.getString("studentBranch"))
		                	.studentEmail(rs.getString("studentEmail"))
		                	.studentName(rs.getString("studentName"))
		                	.build();

		                return student;
		            }
		});

		return result;

	}

	@Override
	public Student getStudentById(int studentId) {

		String sql = "select studentId, studentBranch, studentEmail, studentName "
				    + "from student where studentId = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", studentId);

		if (this.namedParameterJdbcTemplate == null) {
			System.out.println("namedParameterJdbcTemplate is null");
			return null;
		}

		Optional<Student> result = this.namedParameterJdbcTemplate.query(sql, namedParameters,
				new DemoResultExtractor());

		return result.orElse(null);
	}

	@Override
	public int getStudentBynameBranch(String name, String branch) {

		String sql = "select studentId, studentBranch, studentName, studentEmail from student " +
					     "where studentName = :name and studentBranch = :branch";
		SqlParameterSource namedParameters =
				new MapSqlParameterSource("branch", branch).addValue("name", name);

		// watch !!! because DemoResultExtractor() is working on all 4 fields....
		// so, your sql to use DemoResultExtractor() must select all 4 fields too
		Optional<Student> result = this.namedParameterJdbcTemplate.query(sql, namedParameters,
				new DemoResultExtractor());

		return result.isEmpty() ? -99 : result.get().getStudentId(); // failed return -99
	}

	@Override
	public boolean deleteStudent(Student student) {
		//for now only use studentId
		String sql = "DELETE FROM student WHERE studentId = ?";
		return myJdbcTemplate.update(sql, student.getStudentId()) > 0;
	}

	@Override
	public boolean updateStudent(Student student) {
		//for now only use studentId
		String sql =
		"UPDATE student SET studentBranch = ?, studentName = ?, studentEmail = ? WHERE studentId = ?";
		return myJdbcTemplate.update(sql,
				                     student.getStudentBranch(),
				                     student.getStudentName(),
				                     student.getStudentEmail(),
				                     student.getStudentId()) > 0;
	}

}
