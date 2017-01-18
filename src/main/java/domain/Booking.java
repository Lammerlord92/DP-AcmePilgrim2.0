package domain;

import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Booking extends DomainEntity{

	// Attributes ----------------------------------------------------------
	
	private Date creationMoment;
	private Date arrivalDate;
	private Integer numberOfNights;
	private Integer numberOfBeds;
	private Money pricePerNight;
	private String comments;
	private LodgeAssessment lodgeAssessment;
	private Invoice invoice;
	
	// Getters&Setters ----------------------------------------------------
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationMoment() {
		return creationMoment;
	}
	public void setCreationMoment(Date creationMoment) {
		this.creationMoment = creationMoment;
	}

	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@Min(1)
	public Integer getNumberOfNights() {
		return numberOfNights;
	}
	public void setNumberOfNights(Integer numberOfNights) {
		this.numberOfNights = numberOfNights;
	}
	
	@Min(1)
	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	
	@Valid
	public Money getPricePerNight() {
		return pricePerNight;
	}
	public void setPricePerNight(Money pricePerNight) {
		this.pricePerNight = pricePerNight;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Valid
	public LodgeAssessment getLodgeAssessment() {
		return lodgeAssessment;
	}
	public void setLodgeAssessment(LodgeAssessment lodgeAssessment) {
		this.lodgeAssessment = lodgeAssessment;
	}
	
	@Valid
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	// Relationships ----------------------------------------------------
	
	private Pilgrim pilgrim;
	private Lodge lodge;

	@Valid
	@ManyToOne(optional = false)
	public Pilgrim getPilgrim() {
		return pilgrim;
	}
	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}
	
	@Valid
	@ManyToOne(optional = false)
	public Lodge getLodge() {
		return lodge;
	}
	public void setLodge(Lodge lodge) {
		this.lodge = lodge;
	}
}