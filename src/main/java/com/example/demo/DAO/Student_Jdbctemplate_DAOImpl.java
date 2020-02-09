package com.example.demo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public class Student_Jdbctemplate_DAOImpl implements Student_Jdbctemplate_DAO {

	// some JDBC-backed DAO class...
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate myJdbcTemplate ;
    
	@Autowired
	public void setDataSource(DataSource dataSource) {
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    this.myJdbcTemplate = new JdbcTemplate();
	}


	@Override
	public boolean saveStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Student> getStudents() {

		String sql = "select student_id, student_branch, student_email, student_name from student ";

		List<Student> result =  this.myJdbcTemplate.query(sql, 
		        new RowMapper<Student>() {
		            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Student student = new Student();
		                student.setStudent_id(rs.getInt("student_id"));
		                student.setStudent_branch(rs.getString("student_branch"));
		                student.setStudent_email(rs.getString("student_email"));
		                student.setStudent_name(rs.getString("student_name"));
		                
		                return student;
		            }
		});
				
		return result;

	}

	@Override
	public boolean deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Student getStudentByID(int student_id) {

		String sql = "select student_id, student_branch, student_email, student_name from student where student_id = :id";
		SqlParameterSource namedParameters = new MapSqlParameterSource("id", student_id);

		if (this.namedParameterJdbcTemplate == null) {
			System.out.println("namedParameterJdbcTemplate is null");
			return null;
		}

		Student result = this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, 
		        new RowMapper<Student>() {
		            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		                Student student = new Student();
		                student.setStudent_id(rs.getInt("student_id"));
		                student.setStudent_branch(rs.getString("student_branch"));
		                student.setStudent_email(rs.getString("student_email"));
		                student.setStudent_name(rs.getString("student_name"));
		                
		                return student;
		            }
		});
                
		return result;
	}

	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		return false;
	}


}
