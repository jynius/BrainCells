/*
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Data
@Entity
public class WikiCategory implements Serializable {

	private static final long serialVersionUID = -5351370920277001383L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private Integer count;
	
	@ManyToMany(mappedBy = "categories")
	private List<WikiPage> pages;
}
