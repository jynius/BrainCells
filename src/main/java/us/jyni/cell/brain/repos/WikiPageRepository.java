/**
 * 
 */
package us.jyni.cell.brain.repos;

import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiPage;

/**
 * @author jynius
 *
 */
@Repository
public interface WikiPageRepository extends GenericRepository<WikiPage, Long> {

}
