/**
 * 
 */
package us.jyni.cell.brain.service;

import java.io.Serializable;

import lombok.Data;
import us.jyni.cell.brain.base.EntityView;
import us.jyni.cell.brain.entity.Synapse;

/**
 * @author jynius
 *
 */
@Data
public class SynapseView implements EntityView<Synapse>, Serializable {

	private static final long serialVersionUID = 2061860475294663509L;

	private Long id;

	private String name;
}
