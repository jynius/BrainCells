/**
 * 
 */
package us.jyni.cell.brain.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.Resource;
import us.jyni.cell.brain.base.Filter;
import us.jyni.cell.brain.entity.NeuronFormView;
import us.jyni.cell.brain.service.NeuronService;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Controller
@RequestMapping("/neuron")
public class NeuronController {

	@Resource
	private NeuronService neuronService;
	
	/**
	 * <p>Neuron의 전체 목록에 대해 diagram을 그림.</p>
	 * 
	 * @param model front에 표시할 데이타를 담을 container
	 * @return view path
	 */
	@GetMapping
	public String diagram(Model model) {
		
		List<NeuronFormView> views = neuronService.findAll(Filter.empty(), NeuronFormView.class);
		model.addAttribute("forms", views);
		
		return "neuron/diagram";
	}

	/**
	 * <p>Neuron의 전체 목록</p>
	 * 
	 * @param model front에 표시할 데이타를 담을 container
	 * @return view path
	 */
	@GetMapping("/list")
	public String list(Model model) {
		
		List<NeuronFormView> views = neuronService.findAll(Filter.empty(), NeuronFormView.class);
		model.addAttribute("forms", views);
		
		return "neuron/list";
	}
	
	/**
	 * @param model front에 표시할 데이타를 담을 container
	 * @param id id for querying and edit
	 * @return view path
	 */
	@GetMapping("/edit") 
	public String edit(Model model, @RequestParam(name="id", required = false) Long id) {
		
		Optional<NeuronFormView> optional = neuronService.getNeuron(id);
		NeuronFormView view = optional.orElse(new NeuronFormView());
		
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
	
	/**
	 * @param form data for saving
	 * @return view path
	 */
	@PostMapping("/edit")
	public String edit(NeuronFormView form) {
		
		neuronService.save(form);
		
		return "redirect:edit?id=" + form.getId();
	}
}
