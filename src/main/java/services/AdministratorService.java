package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import domain.Complaint;
import domain.Folder;
import forms.AdministratorForm;

@Transactional
@Service
public class AdministratorService {

	//	Managed repository -----------------------------------------	
	@Autowired
	private AdministratorRepository administratorRepository;
	//	Supporting services ----------------------------------------
	//	Constructor ------------------------------------------------
	//	Simple CRUD methods ----------------------------------------
	public Administrator create(){
		Administrator result;
		result=new Administrator();
		
		UserAccount userAccount=new UserAccount();
		List<Authority> authorities=new ArrayList<Authority>();
		Authority a=new Authority();
		a.setAuthority(Authority.ADMIN);
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		
		result.setUserAccount(userAccount);
		
		Collection<Complaint>complaints = new ArrayList<Complaint>();
		result.setComplaints(complaints);
		
		Folder inbox = new Folder();
		inbox.setName("Inbox");
		Folder outbox = new Folder();
		outbox.setName("Outbox");
		Folder trashbox = new Folder();
		trashbox.setName("Trashbox");
		
		Collection<Folder> folders = new ArrayList<Folder>();
		folders.add(inbox);
		folders.add(outbox);
		folders.add(trashbox);
		result.setFolders(folders);
		
		return result;
	}
	
	public Collection<Administrator> findAll(){
		Collection<Administrator> result = administratorRepository.findAll();
		return result;
	}
	
	public Administrator findOne(int administratorId){
		Administrator result = administratorRepository.findOne(administratorId);
		return result;
	}
	
	public void save(Administrator administrator){
		administratorRepository.saveAndFlush(administrator);
	}
	
	public void delete(Administrator administrator){
		administratorRepository.delete(administrator);
	}
	
	//	Other business methods -------------------------------------
	public Administrator findByUserAccount(UserAccount userAccount) {
		Administrator result = administratorRepository.findByUserAccount(userAccount);
		return result;
	}

	public Administrator reconstruct(AdministratorForm administratorForm) {
		Assert.isTrue(administratorForm.getPassword().equals(administratorForm.getConfirmPassword()));
		Assert.isTrue(administratorForm.isAccepConditions());
		Administrator result;
		result=create();
		UserAccount userAccount=result.getUserAccount();
		userAccount.setUsername(administratorForm.getUserName());
		
		Md5PasswordEncoder encoder;		
		encoder= new Md5PasswordEncoder();
		String password=encoder.encodePassword(administratorForm.getPassword(), null);
		userAccount.setPassword(password);
		
		result.setUserAccount(userAccount);
		
		result.setName(administratorForm.getName());
		result.setSurname(administratorForm.getSurname());
		result.setEmailAddress(administratorForm.getEmailAddress());
		result.setContactPhone(administratorForm.getContactPhone());
		result.setUrl(administratorForm.getUrl());
		
		return result;
	}
}
