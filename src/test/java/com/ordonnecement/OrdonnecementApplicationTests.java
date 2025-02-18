package com.ordonnecement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrdonnecementApplicationTests {


	@Test
	void contextLoads() {
	}

	@Test
	public void testExecuteOneCycleProcess(){
      //Given
		ServiceExecutor serviceExecutor = new ServiceExecutor();
     Process process = new Process(1,0,1);
     //WHEN
		List<Process> result = serviceExecutor.run(List.of(process));
    // THEN
		Assertions.assertTrue(result.get(0).isDone());
	}

	@Test
	public void testExecuteOneCycleTwoTimeProcess(){
		//Given
		ServiceExecutor serviceExecutor = new ServiceExecutor();
		Process process = new Process(1,0,1);
		//WHEN
		List<Process> result = serviceExecutor.run(List.of(process));
		// THEN
		Assertions.assertTrue(result.get(0).isDone());
	}

	@Test
	public void testRun(){
		//GIVEN
		Process process1 = new Process(1,0,5);
		Process process2 = new Process(2,0,7);
		Process process3 = new Process(3,4,6);
		Process process4 = new Process(4,0,1);
		Process process5 = new Process(5,3,2);
		List<Process> processes = List.of(process1,process2,process3,process4,process5);
		ServiceExecutor serviceExecutor = new ServiceExecutor();

		//WHEN
		List<Process> result = serviceExecutor.run(processes);
		//THEN

		Assertions.assertEquals(4,result.get(0).pid());

	}


}
