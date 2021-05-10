package everest.engineering.main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;
import everest.engineering.generator.GenericDataGenerator;
import everest.engineering.generator.PackageGenerator;

@RunWith(JUnit4.class)
public class CostEstimatorTest {

	private CostEstimator costEstimator;
	private PackageGenerator pkgGeneratorService;
	private GenericDataGenerator gdGeneratorService;

	@Before
	public void setUp() throws Exception {
		this.costEstimator = new CostEstimator();
		this.pkgGeneratorService = new PackageGenerator();
		this.gdGeneratorService = new GenericDataGenerator();
	}

	@Test
	public void givenInvalidOfferCodeDiscountEqual0() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 10.23, 100.00, "INVALIDOFFERCODE");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 122.0, 12.89);
		costEstimator.run(gd);
		assertEquals(0.0, pkg[0].getDiscount(), 0.00);
	}

	@Test
	public void givenNullOfferCodeDiscountEqual0() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 10.23, 100.00, null);
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 122.0, 12.89);
		costEstimator.run(gd);
		assertEquals(0.0, pkg[0].getDiscount(), 0.00);
	}

	@Test
	public void givenValidOfferCodeDiscountShouldBeAdded() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 70.23, 100.00, "OFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 122.0, 12.89);
		double totalCost = 122.0 + (70.23 * 10) + (100.00 * 5);
		double expectedDiscount = 0.1 * totalCost;
		costEstimator.run(gd);
		assertEquals(expectedDiscount, pkg[0].getDiscount(), 0.00);
	}

	@Test
	public void givenCaseSensitiveOfferCodeDiscountShouldBeAdded() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 70.23, 100.00, "oFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 122.0, 12.89);
		double totalCost = 122.0 + (70.23 * 10) + (100.00 * 5);
		double expectedDiscount = 0.1 * totalCost;
		costEstimator.run(gd);
		assertEquals(expectedDiscount, pkg[0].getDiscount(), 0.00);
	}
}
