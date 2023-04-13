/*
 * 
 */
package us.jyni.cell.brain.base;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * <p>변경할 데이타를 담는 interface</p>
 * 
 * @author jynius
 * @since 2020-10-29
 */
public interface UpdatableEntity<E> {

	/** logger for static and default methods of UpdatableEntity interface */
	public static final Logger LOG = LoggerFactory.getLogger(UpdatableEntity.class);

	/**
	 * @param <E> Entity Type
	 * @param <F> Form Type
	 * @param found 변경할 Target Entities
	 * @param form 변경할 데이타
	 * @return 변경한 후의 Target Entities
	 */
	public static <E, F extends UpdatableEntity<E>> List<E> from(List<E> found, F form) {
		return found==null? null: found.stream()
			.peek(e->form.update(e))
			.collect(Collectors.toList());
	}

	/**
	 * @param <E> Entity Type
	 * @param <F> Form Type
	 * @param entity 변경할 Target Entity
	 * @param form 변경할 데이타
	 * @return 변경한 후의 Target Entity
	 */
	public static <E, F extends UpdatableEntity<E>> E of(E entity, F form) {
		form.update(entity);
		return entity;
	}
	
	/**
	 * @param entity 변경할 Target Entity
	 */
	default void update(E entity) {
		BeanUtils.copyProperties(this, entity);
	}
}
