/*
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Data
@Entity
public class WikiLink implements Serializable {

	private static final long serialVersionUID = -4343297432737786124L;

	@Id
	@GeneratedValue
	private Long id;

	private Long prev;
	private Long next;
}
