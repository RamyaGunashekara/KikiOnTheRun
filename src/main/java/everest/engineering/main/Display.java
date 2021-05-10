package everest.engineering.main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;
import everest.engineering.generator.PackageGenerator;

public class Display {
	private CostEstimator calculateCost = new CostEstimator();
	private DeliveryTimeEstimator findDeliveryTime = new DeliveryTimeEstimator();
	private GenericData gd = new GenericData();
	private PackageGenerator pkgGeneratorService = new PackageGenerator();

	public void run() {
			gd = pkgGeneratorService.acceptInput();
			calculateCost.run(gd);
			findDeliveryTime.run(gd);
			displayOutput(gd.getPackages());
	}

	private void displayOutput(Package[] pkg) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println();
		System.out.println();
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
