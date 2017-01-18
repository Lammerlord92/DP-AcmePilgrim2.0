package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class Invoice {
	// Attributes ---------------------------------------------------------
	private Date moment;
	private Date paidMoment;
	private String description;
	private Money totalCost;
	private String comment;
	// Getters&Setters ----------------------------------------------------
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPaidMoment() {
		return paidMoment;
	}
	public void setPaidMoment(Date paidMoment) {
		this.paidMoment = paidMoment;
	}
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Valid
	public Money getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Money totalCost) {
		this.totalCost = totalCost;
	}
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
