package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {
	@Autowired
	WhiskyRepository whiskyRepository;
	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getAllWhiskiesByYear(){
		List<Whisky> whiskies = whiskyRepository.findWhiskiesByYear(2018);
		System.out.println(whiskies.size());
	}
	@Test
	public void getAllDistilleriesForLocation(){
		List<Distillery> distilleries = distilleryRepository.findDistilleriesByRegion("Islay");
		System.out.println(distilleries.size());
	}
	@Test
	public void getAllWhiskiesFromADistilleryThatIsASpecificAge(){
		List<Whisky> whiskies = whiskyRepository.findWhiskiesByDistilleryNameAndAge("Blair Athol", 12);
		System.out.println(whiskies);
	}
	@Test
	public void getAllWhiskiesFromRegion(){
		List<Whisky> whiskies = whiskyRepository.findWhiskiesByDistilleryRegion("Islay");
		System.out.println(whiskies.size());
	}
	@Test
	public void getDistilleriesWith12YearOldWhisky(){
		List<Distillery> distilleries = distilleryRepository.findDistilleriesByWhiskiesAge(12);
		System.out.println(distilleries.size());
	}
	@Test
	public void getAllWhiskiesFromADistilleryIdThatIsASpecificAge(){
		Distillery distillery = distilleryRepository.getById(7L);
		List<Whisky> whiskies = whiskyRepository.findWhiskiesByDistilleryAndAge(distillery, 12);
		System.out.println(whiskies.size());
	}

}
