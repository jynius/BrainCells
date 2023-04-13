/**
 * 
 */
package us.jyni.cell.brain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.Filter;
import us.jyni.cell.brain.entity.NeuronFormView;

/**
 * @author jynius
 *
 */
@ExtendWith(SpringExtension.class)
@Profile("default")
class NeuronServiceImplTest {

	@Resource
	private NeuronService service;
	
	/**
	 * Test method for {@link us.jyni.cell.brain.service.NeuronServiceImpl#test()}.
	 */
	@Test
	void testTest() {
		service.findAll(Filter.empty(), NeuronFormView.class);
	}
}
