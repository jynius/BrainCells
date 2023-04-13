/*
 * 
 */
package us.jyni.cell.brain.base;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;

/**
 * @author jynius
 * @since 2020-10-29
 */
public interface GenericService<E, I> {

	/**
	 * <p>Service Layer에서 사용하는 type이 &lt;E&gt;이고 id가 &lt;I&gt;인 repository</p>
	 * 
	 * @return type이 &lt;E&gt;이고 id가 &lt;I&gt;인 repository
	 */
	public abstract GenericRepository<E, I> getRepository();
	
	/**
	 * @param filter {@link Filter} instance
	 * @return count of queried
	 */
	default long count(Filter<E> filter) {
		long count = getRepository().count(filter.getSpecification());
		return count;
	}

	/**
	 * @param <T> Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return Page of target beans queried
	 */
	default <T> Page<T> findPage(PageableFilter<E> filter, Function<E, T> function) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		return EntityView.from(page, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param clazz class of Type &lt;D&gt;
	 * @return Page of target beans queried
	 */
	default <D extends EntityView<E>> Page<D> findPage(PageableFilter<E> filter, Class<D> clazz) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		return EntityView.from(page, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return List of target beans queried
	 */
	default <T> List<T> findAll(Filter<E> filter, Function<E, T> function) {
		List<E> list = getRepository().findAll(filter.getSpecification(), filter.getSort());
		return EntityView.from(list, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param clazz class of Type &lt;D&gt;
	 * @return List of target beans queried
	 */
	default <D extends EntityView<E>> List<D> findAll(Filter<E> filter, Class<D> clazz) {
		List<E> list = getRepository().findAll(filter.getSpecification(), filter.getSort());
		return EntityView.from(list, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param ids collection of id for querying
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return List of target beans having each ids
	 */
	default <T> List<T> findAllByIds(List<I> ids, Function<E, T> function) {
		List<E> list = getRepository().findAllById(ids);
		return EntityView.from(list, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param ids collection of id for querying
	 * @param clazz class of Type &lt;D&gt;
	 * @return List of target beans having each ids
	 */
	default <D extends EntityView<E>> List<D> findAllByIds(List<I> ids, Class<D> clazz) {
		List<E> list = getRepository().findAllById(ids);
		return EntityView.from(list, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return first among queried
	 */
	default <T> Optional<T> findOne(PageableFilter<E> filter, Function<E, T> function) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		Optional<E> entity = page.stream().findFirst();
		return EntityView.of(entity, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param clazz class of Type &lt;D&gt;
	 * @return first among queried
	 */
	default <D extends EntityView<E>> Optional<D> findOne(PageableFilter<E> filter, Class<D> clazz) {
		Page<E> page = getRepository().findAll(filter.getSpecification(), filter.getPageable());
		Optional<E> entity = page.stream().findFirst();
		return EntityView.of(entity, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return the exact One queried
	 */
	default <T> Optional<T> findUnique(Filter<E> filter, Function<E, T> function) {
		Optional<E> entity = getRepository().findOne(filter.getSpecification());
		return EntityView.of(entity, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param filter {@link Filter} instance
	 * @param clazz class of Type &lt;D&gt;
	 * @return the exact One queried
	 */
	default <D extends EntityView<E>> Optional<D> findUnique(Filter<E> filter, Class<D> clazz) {
		Optional<E> entity = getRepository().findOne(filter.getSpecification());
		return EntityView.of(entity, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param id id for querying
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return the One having that id
	 */
	default <T> Optional<T> findById(I id, Function<E, T> function) {
		Optional<E> optional = getRepository().findById(id);
		return EntityView.of(optional, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param id id for querying
	 * @param clazz class of Type &lt;D&gt;
	 * @return the One having that id
	 */
	default <D extends EntityView<E>> Optional<D> findById(I id, Class<D> clazz) {
		Optional<E> optional = getRepository().findById(id);
		return EntityView.of(optional, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param <F> Form Type
	 * @param list 저장할 데이타 목록
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return 함수로 지정된 Type의 저장된 데이타 목록
	 */
	default <T, F extends FormEntity<E>> List<T> saveAll(List<F> list, Function<E, T> function) {
		List<E> saved = getRepository().saveAll(FormEntity.from(list));
		return EntityView.from(saved, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param <F> Form Type
	 * @param list 저장할 데이타 목록
	 * @param clazz class of Type &lt;D&gt;
	 * @return EntityView Type의 저장된 데이타 목록
	 */
	default <D extends EntityView<E>, F extends FormEntity<E>> List<D> saveAll(List<F> list, Class<D> clazz) {
		List<E> saved = getRepository().saveAll(FormEntity.from(list));
		return EntityView.from(saved, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param <F> Form Type
	 * @param list 수정할 데이타의 id 목록
	 * @param form 수정할 내용
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return 함수로 지정된 Type의 수정된 목록
	 */
	default <T, F extends UpdatableEntity<E>> List<T> changeAll(List<I> list, F form, Function<E, T> function) {
		
		List<E> found = getRepository().findAllById(list);
		if(found==null || found.isEmpty()) {
			return EntityView.from(found, function);
		}
		
		List<E> saved = getRepository().saveAll(UpdatableEntity.from(found, form));
		return EntityView.from(saved, function);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param <F> Form Type
	 * @param list 수정할 데이타의 id 목록
	 * @param form 수정할 내용
	 * @param clazz class of Type &lt;D&gt;
	 * @return EntityView Type의 수정된 데이타 목록
	 */
	default <D extends EntityView<E>, F extends UpdatableEntity<E>> List<D> changeAll(List<I> list, F form, Class<D> clazz) {
		
		List<E> found = getRepository().findAllById(list);
		if(found==null || found.isEmpty()) {
			return EntityView.from(found, clazz);
		}
		
		List<E> saved = getRepository().saveAll(UpdatableEntity.from(found, form));
		return EntityView.from(saved, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param <F> Form Type
	 * @param form 수정할 내용
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return 함수로 지정된 Type의 수정된 데이타
	 */
	default <T, F extends FormEntity<E>> T save(F form, Function<E, T> function) {
		E saved = getRepository().save(form.getEntity());
		return function==null? null: function.apply(saved);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param <F> Form Type
	 * @param form 수정할 내용
	 * @param clazz class of Type &lt;D&gt;
	 * @return EntityView Type의 수정된 데이타
	 */
	default <D extends EntityView<E>, F extends FormEntity<E>> D save(F form, Class<D> clazz) {
		E saved = getRepository().save(form.getEntity());
		return EntityView.of(saved, clazz);
	}

	/**
	 * @param <T> Target Bean Type
	 * @param <F> Form Type
	 * @param id 수정할 데이타의 ID
	 * @param form 수정할 내용
	 * @param function Entity를 Target Bean으로 변환하는 함수
	 * @return 함수로 지정된 Type의 수정된 데이타
	 */
	default <T, F extends UpdatableEntity<E>> T change(I id, F form, Function<E, T> function) {
		
		Optional<E> optional = getRepository().findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		
		E saved = getRepository().save(UpdatableEntity.of(optional.get(), form));
		return function==null? null: function.apply(saved);
	}

	/**
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param <F> Form Type
	 * @param id 수정할 데이타의 ID
	 * @param form 수정할 내용
	 * @param clazz class of Type &lt;D&gt;
	 * @return EntityView Type의 수정된 데이타
	 */
	default <D extends EntityView<E>, F extends UpdatableEntity<E>> D change(I id, F form, Class<D> clazz) {
		
		Optional<E> optional = getRepository().findById(id);
		if(!optional.isPresent()) {
			return null;
		}
		
		E saved = getRepository().save(UpdatableEntity.of(optional.get(), form));
		return EntityView.of(saved, clazz);
	}

	/**
	 * @param <F> Form Type
	 * @param list 삭제할 목록
	 * @return 삭제한 갯수
	 */
	default <F extends FormEntity<E>> int deleteAll(List<F> list) {
		getRepository().deleteAll(FormEntity.from(list));
		return list.size();
	}

	/**
	 * @param filter {@link Filter} instance
	 * @return 삭제된 갯수
	 */
	default int delete(Filter<E> filter) {
		List<E> entities = getRepository().findAll(filter.getSpecification());
		getRepository().deleteAllInBatch(entities);
		return entities.size();
	}

	/**
	 * @param ids 삭제할 데이타의 id들
	 * @return 삭제된 갯수
	 */
	default int deleteAllByIds(List<I> ids) {
		List<E> entities = getRepository().findAllById(ids);
		getRepository().deleteAllInBatch(entities);
		return entities.size();
	}

	/**
	 * @param id 삭제할 데이타의 id
	 * @return 삭제된 갯수(성공한 경우 1)
	 */
	default int deleteById(I id) {
		getRepository().deleteById(id);
		return 1;
	}
}
