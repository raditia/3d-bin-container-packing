package com.github.skjolberg.packing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class BruteForcePackagerTest extends AbstractPackagerTest {
	
	@Test
	public void testLargestAreaFitFirstDoesNotWork() {
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(15, 10, 10));
		Packager bruteForcePackager = new BruteForcePackager(containers, true);
		LargestAreaFitFirstPackager packager = new LargestAreaFitFirstPackager(containers, true, true);

		List<Box> products1 = new ArrayList<Box>();
		
		products1.add(new Box("01", 5, 10, 10));
		products1.add(new Box("02", 5, 10, 10).rotate3D());
		products1.add(new Box("03", 5, 10, 10).rotate3D().rotate3D());

		long time = System.currentTimeMillis();
		Container fits1 = bruteForcePackager.pack(products1);
		System.out.println(products1.size() + " boxes in " + (System.currentTimeMillis() - time));
		assertNotNull(fits1);
		assertEquals(products1.size(), fits1.getBoxCount());
		print(fits1);
 		assertNull(packager.pack(products1));
	}
	
	@Test
	public void testRunsForLimitedTimeSeconds() {
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(5000, 10, 10));
		runsLimitedTimeSeconds(new BruteForcePackager(containers, true), 1000);
	}

	@Test
	@Ignore("Run manually")
	public void testRunsPerformanceGraphLinearStacking() {
		long duration = 60 * 10;
		
		// n! permutations
		// 6 rotations per box
		// so something like n! * 6^n combinations, each needing to be stacked
		//
		// anyways my laptop cannot do more than 11 within 5 seconds on a single thread, 
		// and this is quite a simple scenario
		
		System.out.println("Run for " + duration + " seconds");
		
		long deadline = System.currentTimeMillis() + duration * 1000;
		int n = 1;
		while(deadline > System.currentTimeMillis()) {
			List<Box> containers = new ArrayList<Box>();
			containers.add(new Box(5 * n, 10, 10));
			Packager bruteForcePackager = new BruteForcePackager(containers, true);
			
			List<Box> products1 = new ArrayList<Box>();

			for(int i = 0; i < n; i++) {
				Box box = new Box(Integer.toString(i), 5, 10, 10);
				for(int k = 0; k < i % 2; k++) {
					box.rotate3D();
				}
				products1.add(box);
			}

			long time = System.currentTimeMillis();
			Container container = bruteForcePackager.pack(products1, deadline);
			if(container != null) {
				System.out.println(n + " in " + (System.currentTimeMillis() - time));
			} else {
				System.out.println(n + " discarded");
			}
			
			n++;
		}
		
	}

}
