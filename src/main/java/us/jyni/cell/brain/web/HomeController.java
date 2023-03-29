/**
 * 
 */
package us.jyni.cell.brain.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.entity.Neuron;
import us.jyni.cell.brain.service.NeuronService;

/**
 * @author jynius
 *
 */
@Controller
public class HomeController {
	
	@Resource
	private NeuronService neuronService;
	
	@GetMapping("/") 
	@ResponseBody
	public String home() {
		return "hello world!";
	}
	
	@GetMapping("/edit") 
	public String edit() {
		return "home/edit";
	}
	
	@GetMapping("/insert") 
	@ResponseBody
	public Neuron insert() {
		
		neuronService.test();
		Optional<Neuron> optional = neuronService.getNeuron(1L);
		
		return optional.get();
	}
}
