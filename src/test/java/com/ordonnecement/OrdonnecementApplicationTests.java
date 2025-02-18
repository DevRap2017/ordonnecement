package com.ordonnecement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrdonnecementApplicationTests {


	@Test
	void contextLoads() {
	}

	@Test
	public void testExecuteOneCycleProcess(){
      //Given
     Process process = new Process(1,0,1);
     //WHEN
		Process result = process.run(process);
    // THEN
		Assertions.assertTrue(result.isDone());
	}

	@Test
	public void testExecuteOneCycleTwoTimeProcess(){
		//Given
		Process process = new Process(1,0,1);
		//WHEN
		Process result = process.run(process);
		// THEN
		Assertions.assertTrue(result.isDone());
	}


}
