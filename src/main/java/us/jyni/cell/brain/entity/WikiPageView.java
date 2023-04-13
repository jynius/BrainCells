/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;

import lombok.Data;
import us.jyni.cell.brain.base.EntityView;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Data
public class WikiPageView implements EntityView<WikiPage>, Node, Serializable {

	private static final long serialVersionUID = -8129821160589556551L;

	private Long id;
	
	private String name;

	@Override
	public String getNodeId() {
		return id.toString();
	}

	@Override
	public String getLabel() {
		return name;
	}
}
