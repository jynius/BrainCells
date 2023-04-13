/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * @author USER
 * @since 2023-03-18
 */
@Data
@Entity
public class Synapse {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Neuron prev;
	@ManyToOne
	private Neuron next;

	private String name;
	@OneToMany
	private List<Tag> tags;
//	private Strength strength;
}
