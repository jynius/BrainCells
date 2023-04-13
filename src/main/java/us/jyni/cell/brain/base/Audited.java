/*
 * 
 */
package us.jyni.cell.brain.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>Spring {@link Auditable}을 구현한 Class</p>
 * 
 * @author jynius
 * @since 2020-11-01
 *
 * @param <U> the auditing type. Typically some kind of user.
 * @param <ID> the type of the audited type's identifier
 */
@Data
@EqualsAndHashCode(callSuper = true, of = {})
@ToString(callSuper = true)
public class Audited<U, ID extends Serializable>
	extends AbstractPersistable<ID>
	implements Auditable<U, ID, LocalDateTime>, Serializable {

	private static final long serialVersionUID = -1893813153741205367L;

	/** 생성한 사람 */
	@ManyToOne
	private U createdBy;
	/** 마지막으로 수정한 사람 */
	@ManyToOne
	private U lastModifiedBy;
	
	/** 생성한 날짜와 시간 */
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;
	/** 마지막으로 수정한 날짜와 시간 */
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime lastModifiedDate;

	/**
	 * 생성한 사람
	 */
	@Override
	public Optional<U> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	/**
	 * 생성한 날짜와 시간
	 */
	@Override
	public Optional<LocalDateTime> getCreatedDate() {
		return Optional.ofNullable(createdDate);
	}

	/**
	 * 마지막 수정한 사람
	 */
	@Override
	public Optional<U> getLastModifiedBy() {
		return Optional.ofNullable(lastModifiedBy);
	}

	/**
	 * 마지막 수정한 날짜와 시간
	 */
	@Override
	public Optional<LocalDateTime> getLastModifiedDate() {
		return Optional.ofNullable(lastModifiedDate);
	}
}
