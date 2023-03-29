/*
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
public interface NeuronService {

	public void test();

	public List<Neuron> findNeurons(String name);

	public Optional<Neuron> getNeuron(Long id);
}
