/**
 * 
 */
package us.jyni.cell.brain.repos;

import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.WikiCategory;

/**
 * @author jynius
 *
 */
@Repository
public interface WikiCategoryRepository extends GenericRepository<WikiCategory, Long> {

}
