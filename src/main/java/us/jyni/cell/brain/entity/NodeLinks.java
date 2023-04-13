/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author jynius
 *
 */
@Data
public class NodeLinks implements Serializable {
	
	private static final long serialVersionUID = -7941694648867913783L;

	private List<? extends Node> nodes;
	
	private List<? extends Link> links;
	
	public NodeLinks(List<? extends Node> nodes, List<? extends Link> links) {
		this.nodes = nodes;
		this.links = links;
	}
}
