package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Pilgrim extends Actor{

	// Attributes -------------------------------------------------------------
	private CreditCard creditCard;
	private Date birthDate;
	private String nationality;
	
	public Pilgrim(){
		super();
	}
	
	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	// Relationships ----------------------------------------------------
	private Collection<Complaint> complaints;
	private Collection<Anecdote> anecdotes;
	public Collection<Register> registers;

	@Valid
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(Collection<Complaint> complaints) {
		this.complaints = complaints;
	}
	@Valid
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Anecdote> getAnecdotes() {
		return anecdotes;
	}
	public void setAnecdotes(Collection<Anecdote> anecdotes) {
		this.anecdotes = anecdotes;
	}
	@OneToMany(mappedBy = "pilgrim")
	public Collection<Register> getRegisters() {
		return registers;
	}
	public void setRegisters(Collection<Register> registers) {
		this.registers = registers;
	}
}
