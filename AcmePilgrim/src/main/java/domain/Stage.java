package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class Stage extends DomainEntity{
	
	// Attributes --------------------------------------------------------
	
	private String name;
	private String description;
	private GPS origin;
	private GPS destination;
	private Double lenghtKm;
	private Double lenghtMi;
	private Integer difficultyLevel;
	private String briefTextDescription;
	private Double ratingL;
	private Double ratingD;
	
	// Getters&Setters ----------------------------------------------------
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@AttributeOverrides({
		@AttributeOverride(name="title",
			column=@Column(name="originTitle")),
		@AttributeOverride(name="description",
			column=@Column(name="originDescription")),
		@AttributeOverride(name="longitude",
			column=@Column(name="originLongitude")),
		@AttributeOverride(name="latitude",
			column=@Column(name="originLatitude")),
		@AttributeOverride(name="altitude",
			column=@Column(name="originAltitude"))
	})
	public GPS getOrigin() {
		return origin;
	}
	public void setOrigin(GPS origin) {
		this.origin = origin;
	}
	
	
	@AttributeOverrides({
		@AttributeOverride(name="title",
			column=@Column(name="destinationTitle")),
		@AttributeOverride(name="description",
			column=@Column(name="destinationDescription")),
		@AttributeOverride(name="longitude",
			column=@Column(name="destinationLongitude")),
		@AttributeOverride(name="latitude",
			column=@Column(name="destinationLatitude")),
		@AttributeOverride(name="altitude",
			column=@Column(name="destinationAltitude"))
	})
	public GPS getDestination() {
		return destination;
	}
	public void setDestination(GPS destination) {
		this.destination = destination;
	}
	
	public Double getLenghtKm() {
		return lenghtKm;
	}
	public void setLenghtKm(Double lenghtKm) {
		this.lenghtKm = lenghtKm;
	}
	
//	@Transient
	public Double getLenghtMi() {
		lenghtMi = lenghtKm*0.62;
		return lenghtMi;
	}	
	public void setLenghtMi(Double lenghtMi) {
		this.lenghtMi = lenghtMi;
	}
	
	@Range(min=0,max=10)
	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getBriefTextDescription() {
		return briefTextDescription;
	}
	public void setBriefTextDescription(String briefTextDescription) {
		this.briefTextDescription = briefTextDescription;
	}
	
//	@Transient
	@Range (min = 0, max = 10)
	public Double getRatingL() {
		Collection<StageRating> ratings = getStageRatings();
		if(ratings==null)
			return 0.0;
		Integer ratingTotal = 0;
		Integer number = 0;
		for(StageRating aux : ratings){
			if (aux.getAssessment()!=null) {
				number++;
				Assessment assessment = aux.getAssessment();
				ratingTotal += assessment.getRatingLenght();
			}
		}
		if(number==0){
			ratingL=0.0;
		}
		else{
			ratingL = new Double(ratingTotal/number);
		}
		return ratingL;
	}
	public void setRatingL(Double ratingL) {
		this.ratingL = ratingL;
	}
	
//	@Transient
	@Range(min = 0, max =10)
	public Double getRatingD() {
		Collection<StageRating> ratings = getStageRatings();
		if(ratings==null)
			return 0.0;
		Integer ratingTotal = 0;
		Integer number = 0;
		for(StageRating aux : ratings){
			if (aux.getAssessment()!=null) {
				number++;
				Assessment assessment = aux.getAssessment();
				ratingTotal += assessment.getRatingDifficulty();
			}
		}
		
		if(number==0){
			ratingD=0.0;
		}
		else{
			ratingD = new Double(ratingTotal/number);
		}
		return ratingD;
	}
	public void setRatingD(Double ratingD) {
		this.ratingD = ratingD;
	}
	
	// Relationships ----------------------------------------------------
	
	private Collection<StageRating> stageRatings;

	@Valid
	@OneToMany(mappedBy = "stage")
	public Collection<StageRating> getStageRatings() {
		return stageRatings;
	}
	public void setStageRatings(Collection<StageRating> stageRatings) {
		this.stageRatings = stageRatings;
	}
}