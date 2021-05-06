package everest.engineering.data;

public class GenericData {
	Package[] packages;
	double baseCost;
	double vehicles;
	double maxSpeed;
	double maxLoad;

	public double getBaseCost() {
		return baseCost;
	}

	public void setBaseCost(double baseCost) {
		this.baseCost = baseCost;
	}

	public double getVehicles() {
		return vehicles;
	}

	public void setVehicles(double vehicles) {
		this.vehicles = vehicles;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(double maxLoad) {
		this.maxLoad = maxLoad;
	}

	public Package[] getPackages() {
		return packages;
	}

	public void setPackages(Package[] packages) {
		this.packages = packages;
	}
}
