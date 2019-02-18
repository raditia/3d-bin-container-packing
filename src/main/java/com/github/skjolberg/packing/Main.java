package com.github.skjolberg.packing;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		int maxContainers = 3;  // maximum number of containers which can be used
		boolean rotate3d = true; // whether the products can be rotated in 6 positions

		// initialization
		// nambahin tipe kendaraan beserta
		List<Container> containers = new ArrayList<Container>();
		containers.add(new Container("Fuso", 10, 10, 3, 100));
		containers.add(new Container("Double", 20, 20, 6, 200));
		Packager packager = new LargestAreaFitFirstPackager(containers);

		// nambahin item list yang perlu dibawa
		List<BoxItem> products = new ArrayList<BoxItem>();
		products.add(new BoxItem(new Box("Barang 1", 6, 10, 2, 25), 2));
		products.add(new BoxItem(new Box("Barang 2", 4, 10, 1, 25), 1));
		products.add(new BoxItem(new Box("Barang 3", 4, 10, 2, 50), 3));

		// limit search using 5 seconds deadline
		long deadline = System.currentTimeMillis() + 5000;

		// match multiple containers
		List<Container> fits = packager.packList(products, maxContainers, deadline);

		System.out.println("Hasil: \n" + fits);

	}
}
