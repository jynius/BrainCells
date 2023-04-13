/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiPage;
import us.jyni.cell.brain.repos.WikiPageRepository;

/**
 * @author jynius
 *
 */
@Service
public class WikiPageServiceImpl implements WikiPageService {

	@Resource
	private WikiPageRepository repository;
	
	private List<WikiPage> pages = new ArrayList<>();
	
	@Override
	public GenericRepository<WikiPage, Long> getRepository() {
		return repository;
	}

	@Override
	public void save(WikiPage page) {
		
		if(pages.size()<1000) {
			pages.add(page);
		}
		
		if(pages.size()>=1000) {
			flush();
		}
	}
	
	public void flush() {
		
		if(pages.isEmpty()) {
			return;
		}
		
		getRepository().saveAll(pages);
		pages.clear();
	}

	@Override
	@Transactional
	public void saveAll(List<WikiPage> pages) {
		getRepository().saveAll(pages);
	}
}
