package everest.engineering.main;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;

public class Display {
	private CostEstimator calculateCost = new CostEstimator();
	private DeliveryTimeEstimator findDeliveryTime = new DeliveryTimeEstimator();
	private GenericData gd = new GenericData();

	public void run() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter number of packages");
			int numOfPackages = scanner.nextInt();
			Package[] packages = new Package[numOfPackages];
			System.out.println("Enter the Base Delivery Cost");
			gd.setBaseCost(scanner.nextDouble());
			System.out.println("Enter the number of Vehicle MaxSpeed and MaxLoad");
			gd.setVehicles(scanner.nextDouble());
			gd.setMaxSpeed(scanner.nextDouble());
			gd.setMaxLoad(scanner.nextDouble());
			for (int i = 0; i < packages.length; i++) {
				packages[i] = new Package();
				System.out.println("Enter PackageName, Weight, Distance and OfferCode in the same order");
				packages[i].setPackageName(scanner.next());
				packages[i].setWeight(scanner.nextDouble());
				packages[i].setDistance(scanner.nextDouble());
				packages[i].setOfferCode(scanner.next());
			}
			gd.setPackages(packages);
			validateInput(gd);
			calculateCost.run(gd);
			findDeliveryTime.run(gd);
			displayOutput(gd.getPackages());
		} catch (InputMismatchException e) {
			System.out.println("ERROR: INVALID INPUT! Please enter correct input");
			run();
		} finally {
			scanner.close();
		}
	}

	public void validateInput(GenericData gd) {
		if(gd.getVehicles() <=0 || gd.getMaxLoad() <=0 || gd.getMaxSpeed() <= 0 || gd.getBaseCost() < 0) {
			throw new InputMismatchException();
		}
		Package[] packages = gd.getPackages();
		for(Package pk : packages) {
			if(pk.getWeight() <=0 || pk.getWeight() > gd.getMaxLoad() || pk.getDistance() <= 0)
				throw new InputMismatchException();
		}	
	}

	private void displayOutput(Package[] pkg) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println("******************************************************");
		System.out.println("Name \t\t Discount \t TotalCost \t Time");
		System.out.println("******************************************************");
		for (Package pk : pkg) {
			System.out.println(pk.getPackageName() + "\t\t" + df.format(pk.getDiscount()) + "\t\t"
					+ df.format(pk.getTotalCost()) + "\t\t" + df.format(pk.getDeliveryTime()));
		}
		System.out.println("******************************************************");
	}
}
