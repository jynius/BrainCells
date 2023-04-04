/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.util.List;
import java.util.function.Function;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * @author USER
 *
 */
@Data
@Entity
public class Neuron {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String title;
	private String content;

	@OneToMany(mappedBy = "next", cascade = CascadeType.ALL)
	private List<Synapse> prev;
	@OneToMany(mappedBy = "prev", cascade = CascadeType.ALL)
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
