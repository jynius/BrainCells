/**
 * 
 */
package us.jyni.cell.brain.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
 * @author jynius
 * @since 2023-04-12
 */
@Data
@Entity
public class WikiPage implements Serializable {

	private static final long serialVersionUID = 5165152635156530711L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@ManyToMany
	private List<WikiCategory> categories;

	@ManyToMany
	@JoinTable(name = "wiki_reference",
			joinColumns = @JoinColumn(name = "refering"),
	        inverseJoinColumns = @JoinColumn(name = "refered")
	)
	private List<WikiPage> refered;
}
