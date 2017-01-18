package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor{
	// Attributes ----------------------------------------------------
	// Getters&Setters ----------------------------------------------------
	// Relationships ----------------------------------------------------
	private Collection<Complaint> complaints;

	@Valid
	@OneToMany(mappedBy = "administrator")
	public Collection<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Collection<Complaint> complaints) {
		this.complaints = complaints;
	}
	
	
}
