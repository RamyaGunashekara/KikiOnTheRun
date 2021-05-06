package everest.engineering.main;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;
import everest.engineering.generator.GenericDataGenerator;
import everest.engineering.generator.PackageGenerator;

@RunWith(JUnit4.class)
public class DeliveryTimeEstimatorTest {

	private DeliveryTimeEstimator deliveryApp;
	private PackageGenerator pkgGeneratorService;
	private GenericDataGenerator gdGeneratorService;

	@Before
	public void setUp() {
		this.deliveryApp = new DeliveryTimeEstimator();
		this.pkgGeneratorService = new PackageGenerator();
		this.gdGeneratorService = new GenericDataGenerator();
	}
	
	@Test
	public void checkDeliveryTimeIfValidInputs() {
		Package[] packages = new Package[5];
		packages[0] = new Package();
		packages[0] = pkgGeneratorService.create("P1", 50.0, 30.0, "ofr001");
		packages[1] = new Package();
		packages[1] = pkgGeneratorService.create("P2", 75.0, 125.0, "ofr002");
		packages[2] = new Package();
		packages[2] = pkgGeneratorService.create("P3", 175.0, 100.0, "ofr002");
		packages[3] = new Package();
		packages[3] = pkgGeneratorService.create("P4", 110.0, 60.0, "ofr002");
		packages[4] = new Package();
		packages[4] = pkgGeneratorService.create("P5", 155.0, 95.0, "ofr002");
		Package[] outputPkg = new Package[5];
		outputPkg = packages;
		GenericData outputgd = gdGeneratorService.create(outputPkg, 320, 1, 200, 90);
		GenericData inputgd = gdGeneratorService.create(packages, 100, 2, 70, 200);
		packages[2].setDeliveryTime(100.0/70.0);
		packages[4].setDeliveryTime(95.0/70.0);
		packages[3].setDeliveryTime(2 * packages[4].getDeliveryTime() + 110.0/70.0);
		packages[0].setDeliveryTime(2 * packages[4].getDeliveryTime() + 30.0/70.0);
		packages[1].setDeliveryTime(2 * packages[2].getDeliveryTime() + 125.0/70.0);
		deliveryApp.run(outputgd);
		assertTrue(inputgd.getPackages().equals(outputgd.getPackages()));
	}

	@Test
	public void checkDeliveryTimeIfAllWeightsAreSame() {
		Package[] packages = new Package[2];
		packages[0] = new Package();
		packages[0] = pkgGeneratorService.create("a1", 10.0, 120.0, "ofr001");
		packages[1] = new Package();
		packages[1] = pkgGeneratorService.create("a2", 10.0, 220.0, "ofr001");
		Package[] outputPkg = new Package[2];
		outputPkg = packages;
		GenericData inputgd = gdGeneratorService.create(packages, 320, 1, 200, 90);
		packages[0].setDeliveryTime(0.6);
		packages[1].setDeliveryTime(1.1);
		GenericData outputgd = gdGeneratorService.create(outputPkg, 320, 1, 200, 90);
		deliveryApp.run(outputgd);
		assertTrue(inputgd.getPackages().equals(outputgd.getPackages()));
	}

}
