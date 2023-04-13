/*
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;

import us.jyni.cell.brain.base.GenericService;
import us.jyni.cell.brain.entity.WikiCategory;

/**
 * @author jynius
 *
 */
public interface WikiCategoryService extends GenericService<WikiCategory, Long> {

	public void save(WikiCategory category);

	public void saveAll(List<WikiCategory> categories);
}
