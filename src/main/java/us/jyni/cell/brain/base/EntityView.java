/**
 * 
 */
package us.jyni.cell.brain.base;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * @author jynius
 *
 */
public interface EntityView<E> {

	public static final Logger LOG = LoggerFactory.getLogger(EntityView.class);
	
	/**
	 * @param <E>
	 * @param <T>
	 * @param page
	 * @param function
	 * @return
	 */
	public static <E, T> Page<T> from(Page<E> page, Function<E, T> function) {
		return new PageImpl<T>(from(page.getContent(), function), page.getPageable(), page.getTotalElements());
	}

	/**
	 * @param <E>
	 * @param <D>
	 * @param page
	 * @param clazz
	 * @return
	 */
	public static <E, D extends EntityView<E>> Page<D> from (Page<E> page, Class<D> clazz) {
		return new PageImpl<D>(from(page.getContent(), clazz), page.getPageable(), page.getTotalElements());
	}
	
	/**
	 * @param <E>
	 * @param <T>
	 * @param entities
	 * @param function
	 * @return
	 */
	public static <E, T> List<T> from(List<E> entities, Function<E, T> function) {
		return entities==null? null: entities.stream()
				.map(function)
				.collect(Collectors.toList());
	}
	
	/**
	 * @param <E>
	 * @param <D>
	 * @param entities
	 * @param clazz
	 * @return
	 */
	public static <E, D extends EntityView<E>> List<D> from(List<E> entities, Class<D> clazz) {
		return entities==null? null: entities.stream()
				.map(e->of(e, clazz))
				.collect(Collectors.toList());
	}

	/**
	 * @param <E>
	 * @param <T>
	 * @param optional
	 * @param function
	 * @return
	 */
	public static <E, T> Optional<T> of(Optional<E> optional, Function<E, T> function) {
		return !optional.isPresent()? Optional.empty(): Optional.of(function.apply(optional.get()));
	}
	
	/**
	 * @param <E>
	 * @param <D>
	 * @param optional
	 * @param clazz
	 * @return
	 */
	public static <E, D extends EntityView<E>> Optional<D> of(Optional<E> optional, Class<D> clazz) {
		return !optional.isPresent()? Optional.empty(): Optional.of(of(optional.get(), clazz));
	}

	/**
	 * @param <E>
	 * @param <D>
	 * @return
	 */
	public static <E, D extends EntityView<E>> D empty() {
		return (D)null;
	}
	
	/**
	 * @param <E>
	 * @param <D>
	 * @param entity
	 * @param clazz
	 * @return
	 */
	public static <E, D extends EntityView<E>> D of(E entity, Class<D> clazz) {
		
		try {
			D data = clazz.getDeclaredConstructor().newInstance();
			data.setEntity(entity);
			return data;
		}
		catch (InstantiationException | InvocationTargetException e) {
			LOG.error("class: {}", clazz, e);
		}
		catch (NoSuchMethodException | IllegalArgumentException e) {
			LOG.error("class: {}", clazz, e);
		}
		catch (IllegalAccessException | SecurityException e) {
			LOG.error("class: {}", clazz, e);
		}
		
		return null;
	}
	
	/**
	 * @param entity
	 */
	default void setEntity(E entity) {
		BeanUtils.copyProperties(entity, this);
	}
}