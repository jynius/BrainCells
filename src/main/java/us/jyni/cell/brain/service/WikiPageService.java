/*
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;

import us.jyni.cell.brain.base.GenericService;
import us.jyni.cell.brain.entity.WikiPage;

/**
 * @author jynius
 *
 */
public interface WikiPageService extends GenericService<WikiPage, Long> {

	public void save(WikiPage page);

	public void saveAll(List<WikiPage> pages);
}
