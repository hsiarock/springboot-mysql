package com.example.demo.dao;

import com.example.demo.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
*
* @author David Hsia
*/
public class DemoResultExtractor implements ResultSetExtractor<Optional<Student>> {

	@Override
	public Optional<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {

		return rs.next() ? Optional.of(Student.builder()
						.studentId(rs.getInt("studentId"))
						.studentBranch(rs.getString("studentBranch"))
						.studentEmail(rs.getString("studentEmail"))
						.studentName(rs.getString("studentName"))
						.build()) : Optional.empty();
	}
}
