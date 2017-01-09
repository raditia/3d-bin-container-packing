package com.skjolberg.packing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.skjolberg.packing.Box;
import com.skjolberg.packing.Container;
import com.skjolberg.packing.Packager;

public class PackagerTest {

	@Test
	public void testStackingSquaresOnSquare() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("A", 5, 5, 1));
		products.add(new Box("B", 5, 5, 1));
		products.add(new Box("C", 5, 5, 1));
		products.add(new Box("D", 5, 5, 1));
		
		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquare() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("E", 5, 10, 1));
		products.add(new Box("F", 5, 10, 1));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquareRectangle() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 5, 10, 1));
		products.add(new Box("K", 5, 5, 1));
		products.add(new Box("L", 5, 5, 1));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}
	
	@Test
	public void testStackingRectanglesOnSquareRectangleVolumeFirst() {
		
		List<Dimension> containers = new ArrayList<Dimension>();
		containers.add(new Dimension(10, 10, 3));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 6, 10, 2));
		products.add(new Box("L", 4, 10, 1));
		products.add(new Box("K", 4, 10, 2));

		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 2);

		assertEquals(1, fits.getLevels().get(fits.getLevels().size() - 1).getHeight());
	}
	
	@Test
	public void testStackingBinary() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(8, 8, 1));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 4, 4, 1));
		
		for(int i = 0; i < 4; i++) {
			products.add(new Box("K", 2, 2, 1));
		}
		for(int i = 0; i < 16; i++) {
			products.add(new Box("K", 1, 1, 1));
		}
		
		Container fits = packager.pack(products);
		assertNotNull(fits);
		assertEquals(fits.getLevels().size(), 1);
	}


	@Test
	public void testStackingTooHigh() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 5));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 10, 10, 6));

		Container fits = packager.pack(products);
		assertNull(fits);
	}

	@Test
	public void testStackingTooHighLevel() {
		
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(10, 10, 5));
		Packager packager = new Packager(containers);
		
		List<Box> products = new ArrayList<Box>();

		products.add(new Box("J", 10, 10, 5));

		products.add(new Box("J", 5, 10, 1));
		products.add(new Box("K", 5, 5, 1));
		products.add(new Box("L", 5, 5, 1));

		Container fits = packager.pack(products);
		assertNull(fits);
	}	
	
	
	@Test
	public void testStacking3xLP() {
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(350, 150, 400));
		Packager packager = new Packager(containers);
		
		List<Box> products1 = new ArrayList<Box>();

		products1.add(new Box("A", 400, 50, 350));
		products1.add(new Box("B", 400, 50, 350));
		products1.add(new Box("C", 400, 50, 350));

		Container fits1 = packager.pack(products1);
		assertNotNull(fits1);
		
		List<Box> products2 = new ArrayList<Box>();
		products2.add(new Box("A", 350, 50, 400));
		products2.add(new Box("B", 350, 50, 400));
		products2.add(new Box("C", 350, 50, 400));

		Container fits2 = packager.pack(products2);
		assertNotNull(fits2);

	}
	
	@Test
	public void testIssue2() {
		List<Box> containers = new ArrayList<Box>();
		containers.add(new Box(320, 490, 290));
		Packager packager = new Packager(containers);

		List<Box> products1 = new ArrayList<Box>();

		products1.add(new Box("01", 330, 88, 88));
		products1.add(new Box("02", 330, 88, 88));
		products1.add(new Box("03", 330, 88, 88));
		products1.add(new Box("04", 330, 88, 88));
		products1.add(new Box("05", 330, 88, 88));
		products1.add(new Box("06", 330, 88, 88));
		products1.add(new Box("07", 330, 88, 88));
		products1.add(new Box("08", 330, 88, 88));
		products1.add(new Box("09", 330, 88, 88));
		products1.add(new Box("10", 330, 88, 88));
		products1.add(new Box("11", 330, 88, 88));
		products1.add(new Box("12", 330, 88, 88));
		products1.add(new Box("13", 330, 88, 88));
		products1.add(new Box("14", 330, 88, 88));
		products1.add(new Box("15", 330, 88, 88));
		products1.add(new Box("16", 330, 88, 88));
		products1.add(new Box("17", 330, 88, 88));

		Container fits1 = packager.pack(products1);
		assertNull(fits1);
	}
	
	
	
}