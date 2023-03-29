/**
 * 
 */
package us.jyni.cell.brain.repos;

import java.util.List;
import java.util.Optional;

import us.jyni.cell.brain.entity.Synapse;

/**
 * @author jynius
 *
 */
public interface SynapseRepository {
	
	/**
	 * @return
	 */
	public List<Synapse> findSynapses();

	/**
	 * @param id
	 * @return
	 */
	public Optional<Synapse> getSynapse(Long id);
	
	/**
	 * @param entity
	 * @return
	 */
	public int insert(Synapse entity);
	
	/**
	 * @param id
	 * @param entity
	 * @return
	 */
	public int update(Long id, Synapse entity);
	
	/**
	 * @param entity
	 * @return
	 */
	public int delete(Synapse entity);
}
