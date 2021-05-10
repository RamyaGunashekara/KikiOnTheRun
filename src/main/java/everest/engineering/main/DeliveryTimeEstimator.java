package everest.engineering.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;
import everest.engineering.data.Vehicle;

public class DeliveryTimeEstimator {

	public void run(GenericData gd) {
		Package[] packages = gd.getPackages();
		if (packages.length == 1) {
			packages[0].setDeliveryTime(packages[0].getDistance() / gd.getMaxSpeed());
			return;
		}
		List<List<Package>> combinations = new ArrayList<>();
		findCombinations(gd, combinations);
		calculateTime(gd, combinations);
	}

	private void findCombinations(GenericData gd, List<List<Package>> combinations) {
		Package[] pkg = gd.getPackages();
		double maxLoad = gd.getMaxLoad();
		Comparator<Package> byWeight = Comparator.comparing(Package::getWeight);
		Comparator<Package> byWeightReversed = byWeight.reversed();
		Arrays.sort(pkg, byWeightReversed);
		List<Package> combo = new ArrayList<Package>();
		int high = pkg.length - 1;
		for (int start = 0; start < pkg.length;) {
			double sum = pkg[start].getWeight();
			combo.add(pkg[start]);
			for (int end = high; end > 0;) {
				if (start == end) {
					combinations.add(new ArrayList<>(combo));
					return;
				}
				sum += pkg[end].getWeight();
				if (sum <= maxLoad) {
					combo.add(pkg[end]);
					end--;
					if (start == end) {
						// end of packages so return
						combinations.add(new ArrayList<>(combo));
						return;
					}
				}
				if (sum >= maxLoad) {
					start++;
					high = end;
					combinations.add(new ArrayList<>(combo));
					combo.clear();
					break;
				}
			}
		}
	}

	private void calculateTime(GenericData gd, List<List<Package>> combinations) {
		Vehicle[] vehicles = new Vehicle[(int) gd.getVehicles()];
		for (int i = 0; i < vehicles.length; i++) {
			vehicles[i] = new Vehicle();
			vehicles[i].setVehicle(i + 1);
			vehicles[i].setTime(Double.valueOf(0));
			vehicles[i].setMinTime(Double.valueOf(0));
		}
		Package[] pkg = gd.getPackages();
		double maxSpeed = gd.getMaxSpeed();
		System.out.println("Package Combinations");
		for (List<Package> list : combinations) {
			double maxDistance;
			if (list.size() == 1)
				maxDistance = list.get(0).getDistance();
			else
				maxDistance = list.stream().max(Comparator.comparingDouble(p -> p.getDistance())).get().getDistance();
			list.spliterator().forEachRemaining((pk) -> System.out.print(pk.getPackageName() + " | "));
			assignVehicle(maxDistance, list, maxSpeed, vehicles);
			System.out.println("____________________________________________________________________");
		}
		Comparator<Package> byName = Comparator.comparing(Package::getPackageName);
		Arrays.sort(pkg, byName);
	}

	private void assignVehicle(double maxDistance, List<Package> list, double maxSpeed, Vehicle[] vehicles) {
		Vehicle v = Arrays.stream(vehicles).min(Comparator.comparingDouble(Vehicle::getTime)).get();
		System.out.print("Vehicle " + v.getVehicle());
		System.out.printf("  ( StartTime %.2f ", v.getTime());
		v.setTime(v.getMinTime() + (2 * maxDistance / maxSpeed));
		for (Package p : list)
			p.setDeliveryTime(v.getMinTime() + (p.getDistance() / maxSpeed));
		v.setMinTime(v.getTime());
		System.out.printf(" EndTime %.2f )", v.getTime());
		System.out.println(" ");
	}
}