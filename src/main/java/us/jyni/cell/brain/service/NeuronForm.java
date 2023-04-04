/**
 * 
 */
package us.jyni.cell.brain.service;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import lombok.Data;
import us.jyni.cell.brain.base.FormEntity;
import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
@Data
public class NeuronForm implements FormEntity<Neuron>, Serializable {

	private static final long serialVersionUID = 404155253901858820L;

	private Long id;
	
	private String name;	// 이름
	private String title;	// 제목
	private String content;	// 내용

	/*
	 *
	 */
	@Override
	public Neuron getEntity() {
		Neuron entity = new Neuron();
		BeanUtils.copyProperties(this, entity);
		return entity;
	}
}
