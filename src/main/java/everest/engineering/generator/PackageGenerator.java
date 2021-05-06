package everest.engineering.generator;

import everest.engineering.data.Package;

public class PackageGenerator {
	public Package create(String name, Double weight, Double distance, String offerCode) {
		Package p = new Package();
		p.setPackageName(name);
		p.setWeight(weight);
		p.setDistance(distance);
		p.setOfferCode(offerCode);
		return p;
	}
}
