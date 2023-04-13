/**
 * 
 */
package us.jyni.cell.brain.web;

import java.io.Serializable;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import lombok.Data;
import us.jyni.cell.brain.base.PageableFilter;
import us.jyni.cell.brain.entity.WikiPage;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Data
public class WikiPageFilter implements PageableFilter<WikiPage>, Serializable {

	private static final long serialVersionUID = -2361403890901203788L;
	
	private Long pageId = 67L;
	
	public Specification<WikiPage> getSpecification() {
		return (root, query, builder)->{
			Join<WikiPage, WikiPage> refered = root.join("refered");
			return builder.or(
					builder.equal(root.get("id"), pageId),
					builder.equal(refered.get("id"), pageId)
				);
		};
	}
}
