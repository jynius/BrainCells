/**
 * 
 */
package us.jyni.cell.brain.repos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
class NeuronReposImplTest {

	@Resource
	private NeuronRepository repository;
	
	/**
	 * Test method for {@link us.jyni.cell.brain.repos.NeuronReposImpl#findNeurons(java.lang.String)}.
	 */
	@Test
	void testFindNeurons() {
		List<Neuron> neurons = repository.findNeuronsByName("user");
		assertNotNull(neurons, "");
		assertEquals(0, neurons.size(), "");
	}

	/**
	 * Test method for {@link us.jyni.cell.brain.repos.NeuronReposImpl#findNeuronById(java.lang.Long)}.
	 */
	@Test
	void testGetNeuron() {
		Optional<Neuron> optional = repository.findNeuronById(1L);
		assertNotNull(optional, "");
		assertNull(optional.get(), "");
	}

	/**
	 * Test method for {@link us.jyni.cell.brain.repos.NeuronReposImpl#insert(us.jyni.cell.brain.entity.Neuron)}.
	 */
	@Test
	void testInsert() {
		repository.save(neuron());
	}

	/**
	 * Test method for {@link us.jyni.cell.brain.repos.NeuronReposImpl#update(java.lang.Long, us.jyni.cell.brain.entity.Neuron)}.
	 */
	//@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link us.jyni.cell.brain.repos.NeuronReposImpl#delete(us.jyni.cell.brain.entity.Neuron)}.
	 */
	//@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	private Neuron neuron() {

		Neuron neuron = new Neuron();
		//neuron.setId(1L);
		neuron.setName("새 뉴런");
		neuron.setTitle("새 뉴런을 등록합니다.");
		neuron.setContent("새 뉴런 컨텐츠");
		
		return neuron;
	}
}
