/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.util.List;

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
public class Synapse {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	private Neuron prev;
//	private Neuron next;

	private String name;
//	private List<Tag> tags;
//	private Strength strength;
}
