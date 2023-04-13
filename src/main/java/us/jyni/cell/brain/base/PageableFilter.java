/*
 * 
 */
package us.jyni.cell.brain.base;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author jynius
 * @since 2020-10-29
 */
public interface PageableFilter<E> extends Filter<E> {
	
	public static class BasePageable extends AbstractPageRequest {

		private static final long serialVersionUID = 5891194863233290160L;
		
		private Sort sort;

		public BasePageable(Sort sort) {
			this(0, 20, sort);
		}

		public BasePageable(int page, int size, Sort sort) {
			super(page, size);
			this.sort = sort;
		}

		public BasePageable create(int page) {
			return new BasePageable(page, getPageSize(), getSort());
		}

		@Override
		public Sort getSort() {
			return sort;
		}
		
		@Override
		public Pageable first() {
			return create(0);
		}

		@Override
		public Pageable previous() {
			return create(getPageNumber()-1);
		}

		@Override
		public Pageable next() {
			return create(getPageNumber()+1);
		}

		@Override
		public Pageable withPage(int pageNumber) {
			return create(pageNumber);
		}
	}

	default Pageable getPageable() {
		return new BasePageable(getSort());
	}
}
