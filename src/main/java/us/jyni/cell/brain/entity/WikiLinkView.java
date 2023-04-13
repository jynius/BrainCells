/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;

import lombok.Data;
import us.jyni.cell.brain.base.EntityView;

/**
 * @author jynius
 *
 */
@Data
public class WikiLinkView implements EntityView<WikiLink>, Link, Serializable {

	private static final long serialVersionUID = 9156560325694532087L;

	private Long id;

	private Long prev;
	private Long next;
	@Override
	public String getSource() {
		return prev.toString();
	}
	@Override
	public String getTarget() {
		return next.toString();
	}
	@Override
	public Number getStrength() {
		return 1;
	}
}
