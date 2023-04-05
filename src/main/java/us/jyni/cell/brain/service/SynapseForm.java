/**
 * 
 */
package us.jyni.cell.brain.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import us.jyni.cell.brain.base.FormEntity;
import us.jyni.cell.brain.entity.Synapse;

/**
 * @author jynius
 *
 */
@Data
public class SynapseForm implements FormEntity<Synapse>, Serializable {

	private static final long serialVersionUID = -2809439679981968622L;

	private Long id;

	private String name;
	private NeuronForm prev;
	private NeuronForm next;

	@Override
	public boolean valid() {
		return name!=null && !name.isEmpty();
	}

	@Override
	public Synapse getEntity() {
		Synapse entity = new Synapse();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
