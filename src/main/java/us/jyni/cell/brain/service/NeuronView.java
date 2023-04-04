/**
 * 
 */
package us.jyni.cell.brain.service;

import java.io.Serializable;

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
}
