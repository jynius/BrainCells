/**
 * 
 */
package us.jyni.cell.brain.repos;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import us.jyni.cell.brain.entity.Neuron;

/**
 * @author jynius
 *
 */
//@Component
public abstract class NeuronReposImpl implements NeuronRepository {

	@Resource
	private EntityManager entityManager;
	
	@Override
	public List<Neuron> findNeuronsByName(String name) {

		// JPQL 실행
		Query query = entityManager.createQuery("SELECT e FROM Neuron e WHERE e.name LIKE :name");
		query.setParameter("name", name.concat("%"));

		@SuppressWarnings("unchecked")
		List<Neuron> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	public Optional<Neuron> findNeuronById(Long id) {

		// 엔티티 조회
		Neuron foundEntity = entityManager.find(Neuron.class, id);

		return Optional.of(foundEntity);
	}

	public int insert(Neuron entity) {
		entityManager.persist(entity);
		return 0;
	}

	public int update(Long id, Neuron entity) {
		entityManager.persist(entity);
		return 0;
	}

	@Override
	public void delete(Neuron entity) {
		entityManager.remove(entity);
	}
}
