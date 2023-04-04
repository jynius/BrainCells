/**
 * 
 */
package us.jyni.cell.brain.base;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jynius
 *
 */
public interface FormEntity<E> {

	/**
	 * @param <E>
	 * @param <D>
	 * @param views
	 * @return
	 */
	public static <E, D extends FormEntity<E>> List<E> from(List<D> views) {
		return views==null? null: views.stream()
				.filter(FormEntity::valid)
				.map(FormEntity::getEntity)
				.collect(Collectors.toList());
	}

	/**
	 * @return
	 */
	default boolean valid() {
		return true;
	}

	/**
	 * @return
	 */
	public E getEntity();
}