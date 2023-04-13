/*
 * 
 */
package us.jyni.cell.brain.base;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author jynius
 * @since 2020-10-29
 */
public interface Filter<E> {

	/**
	 * <p>Query 조건(Specification)이 없고(null) 출력 순서(Sort)가 없는({@link Sort#unsorted()}) Filter를 반환한다.</p>
	 * 
	 * @param <E> Entity Type
	 * @return 조건과 순서가 없는 Filter (default filter)
	 */
	public static <E> Filter<E> empty() {
		return new Filter<E>() {};
	}
	
	/**
     * {@link List&lt;E&gt; org.springframework.data.jpa.repository.JpaSpecificationExecutor#findAll(@Nullable Specification<E> spec, Sort sort)}에서 sort는 not null
     * 
	 * @return Sort
	 */
	default Sort getSort() {
		return Sort.unsorted();
	}
	
	/**
	 * @return Specification
	 */
	default Specification<E> getSpecification() {
		return null;
	}
}
