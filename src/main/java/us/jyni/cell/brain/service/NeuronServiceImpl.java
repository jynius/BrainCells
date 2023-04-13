/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.EntityView;
import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.base.GenericService;
import us.jyni.cell.brain.entity.Neuron;
import us.jyni.cell.brain.entity.NeuronFormView;
import us.jyni.cell.brain.repos.NeuronRepository;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Service
public class NeuronServiceImpl implements NeuronService {

	@Resource
	private NeuronRepository repository;

	/**
	 * <p>{@link GenericService} 구현</p>
	 */
	@Override
	public GenericRepository<Neuron, Long> getRepository() {
		return repository;
	}

	@Override
	public List<NeuronFormView> findNeurons(String name) {
		List<Neuron> entities = repository.findNeuronsByName(name);
		return EntityView.from(entities, NeuronFormView.class);
	}
	
	@Override
	public Optional<NeuronFormView> getNeuron(Long id) {
		Optional<Neuron> optional = repository.findNeuronById(id);
		return EntityView.of(optional, NeuronFormView.class);
	}

	@Override
	public void save(NeuronFormView form) {
		Neuron entity = form.getEntity();
		repository.save(entity);
		form.setId(entity.getId());
	}
}
