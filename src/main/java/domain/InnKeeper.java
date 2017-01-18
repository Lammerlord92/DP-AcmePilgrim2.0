package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class InnKeeper extends Actor{
	// Attributes ----------------------------------------------------
	// Getters&Setters ----------------------------------------------------
	// Relationships ----------------------------------------------------
	private Collection<Lodge> lodges;

	@Valid
	@OneToMany(mappedBy = "innKeeper")
	public Collection<Lodge> getLodges() {
		return lodges;
	}
	public void setLodges(Collection<Lodge> lodges) {
		this.lodges = lodges;
	}
	
	
}
