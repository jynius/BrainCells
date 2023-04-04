/*
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

/**
 * @author jynius
 *
 */
public interface NeuronService {

	public List<NeuronView> findAll();

	public List<NeuronView> findNeurons(String name);

	public Optional<NeuronView> getNeuron(Long id);

	public void save(NeuronForm form);
}
