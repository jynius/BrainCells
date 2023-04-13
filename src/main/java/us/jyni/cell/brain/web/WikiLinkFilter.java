/**
 * 
 */
package us.jyni.cell.brain.web;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import lombok.Data;
import us.jyni.cell.brain.base.Filter;
import us.jyni.cell.brain.entity.WikiLink;
import us.jyni.cell.brain.entity.WikiPageView;

/**
 * @author jynius
 *
 */
@Data
public class WikiLinkFilter implements Filter<WikiLink>, Serializable {

	private static final long serialVersionUID = 389847287626252492L;

	private Long pageId;
	private List<Long> pageIds;

	public void setPages(Page<WikiPageView> pages) {
		this.pageIds = pages==null? null: pages.stream()
				.map(e->e.getId()).collect(Collectors.toList());
	}
	
	public Specification<WikiLink> getSpecification() {
		return (root, query, builder)->{
			return builder.and(
					builder.equal(root.get("next"), pageId),
					root.get("prev").in(pageIds)
				);
		};
	}
}
