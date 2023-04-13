/*
 * 
 */
package us.jyni.cell.brain.base;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>폼 데이타 빈를 Entity의 Data로 변환하는 Interface</p>
 * 
 * @author jynius
 * @since 2020-08-06
 */
public interface FormEntity<E> {

	/**
	 * @param <E> Entity Type
	 * @param <D> EntityView를 구현한 Target Bean Type
	 * @param forms 폼 데이타 목록
	 * @return Entity 목록
	 */
	public static <E, D extends FormEntity<E>> List<E> from(List<D> forms) {
		return forms==null? null: forms.stream()
				.filter(FormEntity::valid)
				.map(FormEntity::getEntity)
				.collect(Collectors.toList());
	}

	/**
	 * 목록 filtering에 사용할 검증 함수
	 * 
	 * @return true
	 */
	default boolean valid() {
		return true;
	}

	/**
	 * @return Entity
	 */
	public E getEntity();
}