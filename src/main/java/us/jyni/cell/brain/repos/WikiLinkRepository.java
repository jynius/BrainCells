/**
 * 
 */
package us.jyni.cell.brain.repos;

import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiLink;

/**
 * @author jynius
 *
 */
@Repository
public interface WikiLinkRepository extends GenericRepository<WikiLink, Long> {
	
}
