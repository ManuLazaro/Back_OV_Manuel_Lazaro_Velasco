package main.OV.db.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;

/**
 * Instantiates a new abstract entity.
 */
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // PARA NO DEFINIR EL ID EN SUS CLASES HIJAS
public abstract class AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// ID GENERADO EN LA BBDD DE FORMA AUTOINCREMENTAL
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	protected Long id;

//	 METODOS DE COMPARACION PARA NO TENER IDs IGUALES
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractBaseEntity)) return false;

		AbstractBaseEntity that = (AbstractBaseEntity) o;

		return id != null ? id.equals(that.id) : that.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}