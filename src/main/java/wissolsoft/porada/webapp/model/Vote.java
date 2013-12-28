package wissolsoft.porada.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private Law law;
}
