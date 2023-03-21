/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.util.List;

import lombok.Data;

/**
 * @author USER
 *
 */
@Data
public class Synapse {
	
	private Neuron prev;
	private Neuron next;

	private String name;
	private List<Tag> tags;
	private Strength strength;
}
