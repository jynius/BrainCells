/**
 * 
 */
package us.jyni.cell.brain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
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
	public void test() {
		
		repository.save(neuron());
	}

	@Override
	public List<Neuron> findNeurons(String name) {
		return repository.findNeuronsByName(name);
	}
	
	@Override
	public Optional<Neuron> getNeuron(Long id) {
		return repository.findNeuronById(id);
	}

	private Neuron neuron() {

		Neuron neuron = new Neuron();
//		neuron.setId(1L);
		neuron.setName("새 뉴런");
		neuron.setTitle("새 뉴런을 등록합니다.");
		neuron.setContent("새 뉴런 컨텐츠");
		
		return neuron;
	}
}
