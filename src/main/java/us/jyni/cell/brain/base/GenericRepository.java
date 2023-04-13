/*
 * 
 */
package us.jyni.cell.brain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * https://jinseobbae.github.io/jpa/2021/12/06/jpa-repository-not-managed-type-error.html
 * 
 * @author jynius
 * @since 2020-08-06
 * 
 * @param <E> Entity Type
 * @param <I> Id Type of Entity
 */
@NoRepositoryBean
public interface GenericRepository<E, I> extends JpaSpecificationExecutor<E>, JpaRepository<E, I> {

}