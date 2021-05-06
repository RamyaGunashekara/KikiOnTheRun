package everest.engineering.generator;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;

public class GenericDataGenerator {
	public GenericData create(Package[] p, double baseCost, double vehicles, double maxSpeed, double maxLoad) {
		GenericData gd = new GenericData();
		gd.setPackages(p);
		gd.setBaseCost(baseCost);
		gd.setMaxSpeed(maxSpeed);
		gd.setMaxLoad(maxLoad);
		gd.setVehicles(vehicles);
		return gd;

	}
}
