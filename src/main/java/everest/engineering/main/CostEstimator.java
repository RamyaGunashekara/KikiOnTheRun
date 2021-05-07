package everest.engineering.main;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import everest.engineering.data.GenericData;
import everest.engineering.data.OfferTableModel;
import everest.engineering.data.Package;

public class CostEstimator {

	public void run(GenericData gd) {
		Package[] packages = gd.getPackages();
		double baseCost = gd.getBaseCost();
		if(packages.length == 1) {
			calculateDiscount(packages[0]);
			calculateCost(packages[0], baseCost);
			return;
		}
		for (Package pk : packages) {
			calculateDiscount(pk);
			calculateCost(pk, baseCost);
		}
	}

	private void calculateCost(Package pkg, double baseCost) {
		double deliveryCost = baseCost + (pkg.getWeight() * 10) + (pkg.getDistance() * 5);
		double discount = deliveryCost * (pkg.getDiscount() / 100.00);
		pkg.setDiscount(discount);
		pkg.setTotalCost(deliveryCost - discount);
	}

	@SuppressWarnings("unchecked")
	private void calculateDiscount(Package pkg) {
		if (StringUtils.isNotEmpty(pkg.getOfferCode())) {
			if (pkg.getOfferCode().equals(null) || pkg.getOfferCode().equalsIgnoreCase("NA")) {
				pkg.setDiscount(0);
				return;
			}
		} else {
			pkg.setDiscount(0);
			return;
		}
		OfferTableModel offTable = new OfferTableModel();
		Range<Double> distanceRange = null, weightRange = null;
		double discount = 0;
		int rowCount = offTable.getRowCount();
		for (int i = 0; i < rowCount; i++) {
			// Check if the OfferCode is present in the JTable data
			if (((String) offTable.getValueAt(i, 0)).equalsIgnoreCase(pkg.getOfferCode())) {
				distanceRange = (Range<Double>) offTable.getValueAt(i, 2);
				weightRange = (Range<Double>) offTable.getValueAt(i, 3);
				discount = (int) offTable.getValueAt(i, 1);
				/*
				 * Check if the Distance and weight of the package are within the range, if not
				 * discount = 0
				 */
				if (distanceRange == null && weightRange == null)
					// If no range specified retain the discount value
					break;
				else if (distanceRange == null && weightRange.contains(pkg.getWeight())) {
					break;
				} else if (weightRange == null && distanceRange.contains(pkg.getDistance())) {
					break;
				} else if (!(distanceRange.contains(pkg.getDistance()) && weightRange.contains(pkg.getWeight()))) {
					discount = 0;
					break;
				}
				break;
			}
		}
		pkg.setDiscount(discount);
	}

}