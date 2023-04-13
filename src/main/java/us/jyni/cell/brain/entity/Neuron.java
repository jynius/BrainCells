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
 * @since 2023-03-18
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

	/**
	 * <p>synapse를 바인딩할 때, 그 synapse의 next neuron도 이 instance로 바인딩한다.</p>
	 * 
	 * @param prev previous synapses
	 */
	public void setPrev(List<Synapse> prev) {
		this.prev = prev==null? null: prev.stream()
				.peek(e->e.setNext(this))
				.toList();
	}

	/**
	 * <p>synapse를 바인딩할 때, 그 synapse의 previous neuron도 이 instance로 바인딩한다.</p>
	 * 
	 * @param next next synapses
	 */
	public void setNext(List<Synapse> next) {
		this.next = next==null? null: next.stream()
				.peek(e->e.setPrev(this))
				.toList();
	}
	
	/**
	 * <p>이 Neuron의 앞, 뒤 연결의 정합성을 검증한다.</p>
	 * <p> 정합성은 예를 들어, 내(this) 앞(Synapse)의 뒤(Neuron)는 나 자신(this)이어야 한다는 것.</p>
	 *  
	 * @return 앞 뒤 연결(Synapse)이 정합성이 있음.
	 */
	public boolean valid() {
		return valid(prev, e->e.getNext()) && valid(next, e->e.getPrev());
	}
	
	/**
	 * <p>(앞, 뒤) 연결의 정합성을 검증한다.</p>
	 * <p> 정합성은 예를 들어, 내(this) 앞(Synapse)의 뒤(Neuron)는 나 자신(this)이어야 한다는 것.</p>
	 *  
	 * @param l Synapse 목록
	 * @param f Synpase와 Neuron을 mapping하는 함수
	 * @return 정합성 여부
	 */
	public boolean valid(List<Synapse> l, Function<Synapse, Neuron> f) {
		return l==null || l.stream()
				.map(f)
				.allMatch(e->e!=null && e.equals(this));
	}
}
