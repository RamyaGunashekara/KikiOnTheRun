package everest.engineering.data;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import everest.engineering.generator.GenericDataGenerator;
import everest.engineering.generator.PackageGenerator;

@RunWith(JUnit4.class)
public class PackageGeneratorTest {

	private PackageGenerator pkgGeneratorService;
	private GenericDataGenerator gdGeneratorService;

	@Before
	public void setUp() {
		this.pkgGeneratorService = new PackageGenerator();
		this.gdGeneratorService = new GenericDataGenerator();
	}

	@Test(expected = InputMismatchException.class)
	public void checkGenericDataForNegativeOrZeroValues() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 0.0, 0.00, "OFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 0.0, 0.0, -11.89);
		pkgGeneratorService.validateInput(gd);
	}

	@Test(expected = InputMismatchException.class)
	public void checkIfWeightsAreLessThanMaxLoad() {
		Package[] pkg = new Package[1];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 345.12, 75.00, "OFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 65.00, 310.00);
		pkgGeneratorService.validateInput(gd);
	}
	
	@Test(expected = InputMismatchException.class)
	public void checkIfPackageNameIsNotSameOrEqualToNull() {
		Package[] pkg = new Package[2];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 345.12, 75.00, "OFR001");
		pkg[1] = new Package();
		pkg[1] = pkgGeneratorService.create("P1", 345.12, 75.00, "OFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 65.00, 400.00);
		pkgGeneratorService.validateInput(gd);
	}
	
}
