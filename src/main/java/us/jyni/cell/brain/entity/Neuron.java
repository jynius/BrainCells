/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.util.List;
import java.util.function.Function;

import lombok.Data;

/**
 * @author USER
 *
 */
@Data
public class Neuron {

	private String name;
	private String title;
	private String content;
	
	private List<Synapse> prev;
	private List<Synapse> next;

	public boolean valid() {
		return valid(prev, e->e.getNext()) && valid(next, e->e.getPrev());
	}
	
	public boolean valid(List<Synapse> l, Function<Synapse, Neuron> f) {
		return l==null || l.stream()
				.map(f)
				.allMatch(e->e!=null && e.equals(this));
	}
}
