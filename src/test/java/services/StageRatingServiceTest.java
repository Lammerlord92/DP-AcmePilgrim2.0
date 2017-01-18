package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import domain.StageRating;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class StageRatingServiceTest extends AbstractTest{

	// ------------------- Supporting services -------------------
	
	@Autowired
	private StageRatingService stageRatingService;

	// ------------------- Service under test --------------------
	
	@Test
	public void testFindOne() {
		System.out.println(stageRatingService.findOne(62));
	}

	@Test
	public void testFindAll(){
		for( StageRating aux : stageRatingService.findAll())
			System.out.println("StageRating: " + aux.getId() + " Del d�a: " + aux.getStartMoment());;
	}
}
