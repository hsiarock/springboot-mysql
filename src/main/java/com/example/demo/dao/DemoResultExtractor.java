package com.example.demo.dao;

import com.example.demo.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
*
* @author David Hsia
*/
@Slf4j
public class DemoResultExtractor implements ResultSetExtractor<Optional<Student>> {

	@Override
	public Optional<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
/*
		if (!rs.next()) {
			log.warn("ResultSet is null, cannot extract student data");
		} else {
			log.info("Got studnt: ");
			log.info("	studentid: " + rs.getInt("studentid"));
			log.info("	studentbranch: " + rs.getString("studentbranch"));
			log.info("	studentemail: " + rs.getString("studentemail"));
			log.info("	studentname: " + rs.getString("studentname"));
		}
*/
		return rs.next() ? Optional.of(Student.builder()
						.studentid(rs.getInt("studentid"))
						.studentbranch(rs.getString("studentbranch"))
						.studentemail(rs.getString("studentemail"))
						.studentname(rs.getString("studentname"))
						.build()) : Optional.empty();
	}
}
