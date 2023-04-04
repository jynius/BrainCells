/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.EntityView;
import us.jyni.cell.brain.entity.Neuron;
import us.jyni.cell.brain.repos.NeuronRepository;

/**
 * @author jynius
 *
 */
@Service
public class NeuronServiceImpl implements NeuronService {

	@Resource
	private NeuronRepository repository;

	@Override
	public List<NeuronView> findAll() {
		List<Neuron> entities = repository.findAll();
		return EntityView.from(entities, NeuronView.class);
	}

	@Override
	public List<NeuronView> findNeurons(String name) {
		List<Neuron> entities = repository.findNeuronsByName(name);
		return EntityView.from(entities, NeuronView.class);
	}
	
	@Override
	public Optional<NeuronView> getNeuron(Long id) {
		Optional<Neuron> optional = repository.findNeuronById(id);
		return EntityView.of(optional, NeuronView.class);
	}

	@Override
	public void save(NeuronForm form) {
		Neuron entity = form.getEntity();
		repository.save(entity);
		form.setId(entity.getId());
	}
}
