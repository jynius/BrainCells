/*
 * 
 */
package us.jyni.cell.brain.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Repository
public interface NeuronRepository extends GenericRepository<Neuron, Long>  {
	
	/**
	 * @param name name for querying
	 * @return 이름과 일치하는 목록
	 */
	public List<Neuron> findNeuronsByName(String name);

	/**
	 * @param id id for querying
	 * @return id와 일치하는 데이타
	 */
	public Optional<Neuron> findNeuronById(Long id);
}
