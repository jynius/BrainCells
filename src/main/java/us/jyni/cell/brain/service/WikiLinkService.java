/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;

import us.jyni.cell.brain.base.GenericService;
import us.jyni.cell.brain.entity.WikiLink;

/**
 * @author jynius
 *
 */
public interface WikiLinkService extends GenericService<WikiLink, Long> {

	public void save(WikiLink link);

	public void saveAll(List<WikiLink> links);
}
