/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiCategory;
import us.jyni.cell.brain.repos.WikiCategoryRepository;

/**
 * @author jynius
 *
 */
@Service
public class WikiCategoryServiceImpl implements WikiCategoryService {

	@Resource
	private WikiCategoryRepository repository;
	
	private List<WikiCategory> categories;
	
	@Override
	public GenericRepository<WikiCategory, Long> getRepository() {
		return repository;
	}

	@Override
	public void save(WikiCategory category) {
		
		if(categories.size()<1000) {
			categories.add(category);
		}
		
		if(categories.size()>=1000) {
			getRepository().saveAll(categories);
			categories.clear();
		}
	}
	
	public void flush() {
		
		if(categories.isEmpty()) {
			return;
		}
		
		getRepository().saveAll(categories);
		categories.clear();
	}

	@Override
	public void saveAll(List<WikiCategory> categories) {
		getRepository().saveAll(categories);
	}
}
