package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author David Hsia
 */
@Entity
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;

	@Column(nullable = false)
	private String studentName;

	@Column(nullable = false)
	private String studentBranch;

	private String studentEmail;
}
