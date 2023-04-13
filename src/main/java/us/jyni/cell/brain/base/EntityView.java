/*
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
 * <p>Entity의 Data를 화면 출력용 데이타 빈으로 변환하는 Interface</p>
 * 
 * @author jynius
 * @since 2020-08-06
 */
public interface EntityView<E> {

	/** logger for static and default methods of EntityView interface */
	public static final Logger LOG = LoggerFactory.getLogger(EntityView.class);

	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @return null of Target Bean
	 */
	public static <E, D extends EntityView<E>> D empty() {
		return (D)null;
	}
	
	/**
	 * @param <E> Entity Type
	 * @param <T> Target Bean Type
	 * @param page Page of Entities
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return Page of Target Beans
	 */
	public static <E, T> Page<T> from(Page<E> page, Function<E, T> function) {
		return new PageImpl<T>(from(page.getContent(), function), page.getPageable(), page.getTotalElements());
	}

	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param page Page of Entities
	 * @param clazz class of Type &lt;D&gt;
	 * @return Page of Target Beans
	 */
	public static <E, D extends EntityView<E>> Page<D> from(Page<E> page, Class<D> clazz) {
		return new PageImpl<D>(from(page.getContent(), clazz), page.getPageable(), page.getTotalElements());
	}
	
	/**
	 * @param <E> Entity Type
	 * @param <T> Target Bean Type
	 * @param entities Entities의 목록
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return Target Beans의 목록
	 */
	public static <E, T> List<T> from(List<E> entities, Function<E, T> function) {
		return entities==null? null: entities.stream()
				.map(function)
				.collect(Collectors.toList());
	}
	
	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param entities Entities의 목록
	 * @param clazz class of Type &lt;D&gt;
	 * @return Target Beans의 목록
	 */
	public static <E, D extends EntityView<E>> List<D> from(List<E> entities, Class<D> clazz) {
		return entities==null? null: entities.stream()
				.map(e->of(e, clazz))
				.collect(Collectors.toList());
	}

	/**
	 * @param <E> Entity Type
	 * @param <T> Target Bean Type
	 * @param optional Optional of Entity Bean
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return Optional of Target Bean
	 */
	public static <E, T> Optional<T> of(Optional<E> optional, Function<E, T> function) {
		return !optional.isPresent()? Optional.empty(): Optional.of(function.apply(optional.get()));
	}
	
	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param optional Optional of Entity Bean
	 * @param clazz class of Type &lt;D&gt;
	 * @return Optional of Target Bean
	 */
	public static <E, D extends EntityView<E>> Optional<D> of(Optional<E> optional, Class<D> clazz) {
		return !optional.isPresent()? Optional.empty(): Optional.of(of(optional.get(), clazz));
	}
	
	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param entity Source Bean
	 * @param clazz class of Type &lt;D&gt;
	 * @return Target Bean
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
	 * @param entity Source
	 */
	default void setEntity(E entity) {
		BeanUtils.copyProperties(entity, this);
	}
}