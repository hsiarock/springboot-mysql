package com.example.demo.dao;

import com.example.demo.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
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
	public boolean saveStudent(Student student) {
		return false;
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
	public boolean deleteStudent(Student student) {
		return false;
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

/*
		        new RowMapper<Student>() {
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
*/

		return result.orElse(null);
	}

	@Override
	public boolean updateStudent(Student student) {
		return false;
	}

}
