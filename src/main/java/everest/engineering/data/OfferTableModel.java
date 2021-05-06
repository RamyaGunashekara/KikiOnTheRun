package everest.engineering.data;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.Range;

public class OfferTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	Range<Integer> distanceRange;

	private Object data[][] = {
			{ "OFR001", Integer.valueOf(10), Range.between(0.0, 200.0), Range.between(70.0, 200.0) },
			{ "OFR002", Integer.valueOf(7), Range.between(50.0, 150.0), Range.between(100.0, 250.0) },
			{ "OFR003", Integer.valueOf(5), Range.between(50.0, 250.0), Range.between(10.0, 150.0) },
			{ "OFR004", Integer.valueOf(20), Range.between(100.4, 250.0), null },
			{ "OFR005", Integer.valueOf(6), null, null } };

	private String[] columnNames = { "ID", "DISCOUNT", "DISTANCE", "WEIGHT" };

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
