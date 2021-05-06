package everest.engineering.main;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import everest.engineering.data.GenericData;
import everest.engineering.data.Package;
import everest.engineering.generator.GenericDataGenerator;
import everest.engineering.generator.PackageGenerator;

@RunWith(JUnit4.class)
public class DisplayTest {

	private Display app;
	private PackageGenerator pkgGeneratorService;
	private GenericDataGenerator gdGeneratorService;

	@Before
	public void setUp() {
		this.app = new Display();
		this.pkgGeneratorService = new PackageGenerator();
		this.gdGeneratorService = new GenericDataGenerator();
	}

	@Test(expected = InputMismatchException.class)
	public void checkGenericDataForNegativeOrZeroValues() {
		Package[] pkg = new Package[2];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 0.0, 0.00, "OFR001");
		GenericData gd = new GenericData();
		gd = gdGeneratorService.create(pkg, 122.0, 0.0, 0.0, -11.89);
		app.validateInput(gd);
	}
	
	@Test(expected = InputMismatchException.class)
	public void checkIfWeightsAreLessThanMaxLoad() {
		Package[] pkg = new Package[2];
		pkg[0] = new Package();
		pkg[0] = pkgGeneratorService.create("P1", 345.12, 75.00, "OFR001");
		GenericData gd = new GenericData();
		//Package BaseCost NumberOfVehicle MaxSpeed MaxLoad
		gd = gdGeneratorService.create(pkg, 122.0, 2.0, 65.00, 310.00);
		app.validateInput(gd);
	}
}
