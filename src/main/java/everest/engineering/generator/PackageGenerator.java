package everest.engineering.generator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;

public class PackageGenerator {
	private GenericData gd = new GenericData();

	public Package create(String name, Double weight, Double distance, String offerCode) {
		Package p = new Package();
		p.setPackageName(name);
		p.setWeight(weight);
		p.setDistance(distance);
		p.setOfferCode(offerCode);
		return p;
	}

	public GenericData acceptInput() {
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
		} catch (InputMismatchException e) {
			if (e.getMessage().isEmpty())
				System.out.println("ERROR: INVALID INPUT! Please enter correct input");
			else
				System.out.println(e.getMessage());
			acceptInput();
		} finally {
			scanner.close();
		}
		return gd;
	}

	public void validateInput(GenericData gd) {
		if (gd.getVehicles() <= 0 || gd.getMaxLoad() <= 0 || gd.getMaxSpeed() <= 0 || gd.getBaseCost() < 0) {
			throw new InputMismatchException("Input cant be less than or equal to zero");
		}
		Package[] pk = gd.getPackages();
		String[] names = new String[pk.length];
		for (int i = 0; i < pk.length; i++) {
			if (pk[i].getWeight() <= 0 || pk[i].getWeight() > gd.getMaxLoad() || pk[i].getDistance() <= 0)
				throw new InputMismatchException("Input can't be less than or equal to zero");
			if (pk[i].getPackageName().equalsIgnoreCase("null"))
				throw new InputMismatchException("Package Name cant be null");
			names[i] = pk[i].getPackageName();
		}
		//Check if all the package names are distinct 
		Set<String> pkName = new HashSet<String>(Arrays.asList(names));
		if (pkName.size() != pk.length)
			throw new InputMismatchException("Package Name cant be same");
	}
}
