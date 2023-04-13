/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import us.jyni.cell.brain.base.EntityView;
import us.jyni.cell.brain.base.FormEntity;
import us.jyni.cell.brain.base.FormView;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Data
public class NeuronFormView implements FormView<Neuron>, Serializable {

	private static final long serialVersionUID = 404155253901858820L;
	/** id */
	private Long id;
	
	/** 이름 */
	private String name; 
	/** 제목 */
	private String title; 
	/** 내용 */
	private String content; 
	
	/** prev */
	private List<SynapseFormView> prev;
	/** next */
	private List<SynapseFormView> next;
//	
//	public void setPrev(List<SynapseForm> prev) {
//		this.prev = prev==null? null: prev.stream()
//				.filter(e->e.valid())
//				.toList();
//	}
//	
//	public void setNext(List<SynapseForm> next) {
//		this.next = next==null? null: next.stream()
//				.filter(e->e.valid())
//				.toList();
//	}
	
	/**
	 * <p>{@link FormEntity} 구현</p>
	 */
	@Override
	public Neuron getEntity() {
		Neuron entity = new Neuron();
		BeanUtils.copyProperties(this, entity);
		entity.setPrev(FormEntity.from(prev));
		entity.setNext(FormEntity.from(next));
		return entity;
	}
	
	/**
	 * <p>{@link EntityView} 구현</p>
	 */
	@Override
	public void setEntity(Neuron entity) {
		BeanUtils.copyProperties(entity, this);
		setPrev(EntityView.from(entity.getPrev(), SynapseFormView.class));
		setNext(EntityView.from(entity.getNext(), SynapseFormView.class));
	}
}
