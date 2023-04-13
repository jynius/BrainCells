/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiLink;
import us.jyni.cell.brain.repos.WikiLinkRepository;

/**
 * @author jynius
 *
 */
@Service
public class WikiLinkServiceImpl implements WikiLinkService {

	@Resource
	private WikiLinkRepository repository;
	
	private List<WikiLink> links;
	
	@Override
	public GenericRepository<WikiLink, Long> getRepository() {
		return repository;
	}

	@Override
	public void save(WikiLink link) {
		
		if(links.size()<1000) {
			links.add(link);
		}
		
		if(links.size()>=1000) {
			getRepository().saveAll(links);
			links.clear();
		}
	}
	
	public void flush() {
		
		if(links.isEmpty()) {
			return;
		}
		
		getRepository().saveAll(links);
		links.clear();
	}

	@Override
	public void saveAll(List<WikiLink> links) {
		getRepository().saveAll(links);
	}
}
