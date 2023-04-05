/**
 * 
 */
package us.jyni.cell.brain.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import us.jyni.cell.brain.base.EntityView;
import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
@Data
public class NeuronView implements EntityView<Neuron>, Serializable {

	private static final long serialVersionUID = -7422197257773706232L;

	private Long id;
	
	private String name;
	private String title;
	private String content;
	
	private List<SynapseView> prev;
	private List<SynapseView> next;
	
	@Override
	public void setEntity(Neuron entity) {
		BeanUtils.copyProperties(entity, this);
		setPrev(EntityView.from(entity.getPrev(), SynapseView.class));
		setNext(EntityView.from(entity.getNext(), SynapseView.class));
	}
}
