/**
 * 
 */
package us.jyni.cell.brain.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
@Repository
public interface NeuronRepository extends JpaRepository<Neuron, Long>  {
	
	/**
	 * @param name
	 * @return
	 */
	public List<Neuron> findNeuronsByName(String name);

	/**
	 * @param id
	 * @return
	 */
	public Optional<Neuron> findNeuronById(Long id);
	
	/**
	 * @param entity
	 * @return
	 */
	public void delete(Neuron entity);
}
