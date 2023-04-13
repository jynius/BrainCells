/*
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

import us.jyni.cell.brain.base.GenericService;
import us.jyni.cell.brain.entity.Neuron;
import us.jyni.cell.brain.entity.NeuronFormView;

/**
 * @author jynius
 * @since 2023-04-01
 */
public interface NeuronService extends GenericService<Neuron, Long> {

	/**
	 * @param name name for querying
	 * @return 이름이 일치하는 데이타 목록
	 */
	public List<NeuronFormView> findNeurons(String name);

	/**
	 * @param id id for querying
	 * @return id가 일치하는 데이타
	 */
	public Optional<NeuronFormView> getNeuron(Long id);

	/**
	 * @param form 저장할 데이타
	 */
	public void save(NeuronFormView form);
}
