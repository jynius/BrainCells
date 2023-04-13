/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import us.jyni.cell.brain.base.FormEntity;
import us.jyni.cell.brain.base.FormView;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Data
public class SynapseFormView implements FormView<Synapse>, Serializable {

	private static final long serialVersionUID = -2809439679981968622L;

	/** id */
	private Long id;

	/** name */
	private String name;
	/** prev */
	private NeuronFormView prev;
	/** next */
	private NeuronFormView next;

	/**
	 * <p>name이 null이 아니고 빈 값이 아님.</p>
	 */
	@Override
	public boolean valid() {
		return name!=null && !name.isEmpty();
	}

	/**
	 * <p>{@link FormEntity} 구현.</p>
	 */
	@Override
	public Synapse getEntity() {
		Synapse entity = new Synapse();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
