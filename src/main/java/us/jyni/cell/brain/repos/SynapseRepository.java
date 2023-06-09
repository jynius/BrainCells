/*
 * 
 */
package us.jyni.cell.brain.repos;

import org.springframework.stereotype.Repository;

import us.jyni.cell.brain.base.GenericRepository;
import us.jyni.cell.brain.entity.Synapse;

/**
 * @author jynius
 * @since 2023-04-01
 */
@Repository
public interface SynapseRepository extends GenericRepository<Synapse, Long> {

}
