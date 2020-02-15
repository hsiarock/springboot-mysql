package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "lookupstatecode")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StateCode {

	@Id
	private String name;

	@Column(nullable = false)
	private String status;

}
