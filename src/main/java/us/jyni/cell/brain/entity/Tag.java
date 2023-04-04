/**
 * 
 */
package us.jyni.cell.brain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @author USER
 *
 */
@Data
@Entity
public class Tag {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
}
