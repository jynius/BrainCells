/**
 * 
 */
package us.jyni.cell.brain.web;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.service.NeuronForm;
import us.jyni.cell.brain.service.NeuronService;
import us.jyni.cell.brain.service.NeuronView;
import us.jyni.cell.brain.service.SynapseView;

/**
 * @author jynius
 *
 */
@Controller
@RequestMapping("/neuron")
public class NeuronController {

	@Resource
	private NeuronService neuronService;
	
	@GetMapping
	public String list(Model model) {
		
		List<NeuronView> views = neuronService.findAll();
		model.addAttribute("forms", views);
		
		return "neuron/list";
	}
	
	@GetMapping("/edit") 
	public String edit(Model model, @RequestParam(name="id", required = false) Long id) {
		
		Optional<NeuronView> optional = neuronService.getNeuron(id);
		NeuronView view = optional.orElse(new NeuronView());
		
//		List<SynapseView> prev = view.getPrev();
//		if(prev==null) {
//			view.setPrev(Collections.singletonList(SynapseView.builder().id(1L).name("Prev Synapse").build()));
//		}
//		else {
//			prev.add(SynapseView.builder().id(1L).name("Prev Synapse").build());
//		}
//		
//		List<SynapseView> next = view.getNext();
//		if(next==null) {
//			view.setNext(Collections.singletonList(SynapseView.builder().id(2L).name("Next Synapse").build()));
//		}
//		else {
//			next.add(SynapseView.builder().id(2L).name("Next Synapse").build());
//		}
		
		model.addAttribute("form", view);
		
		return "neuron/edit";
	}
	
	@PostMapping("/edit")
	public String edit(NeuronForm form) {
		
		neuronService.save(form);
		
		return "redirect:edit?id=" + form.getId();
	}
}
