package src.main.java.com.bookstore.util;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableModelUtil {
	public static DefaultTableModel combineTableModels(TableModel model1, TableModel model2) {
		// 모델1과 모델2의 열 개수와 이름을 합칩니다.
		int columnCount1 = model1.getColumnCount();
		int columnCount2 = model2.getColumnCount();
		String[] combinedColumnNames = new String[columnCount1 + columnCount2];

		for (int i = 0; i < columnCount1; i++) {
			combinedColumnNames[i] = model1.getColumnName(i);
		}
		for (int i = 0; i < columnCount2; i++) {
			combinedColumnNames[columnCount1 + i] = model2.getColumnName(i);
		}

		// 새로운 DefaultTableModel을 생성합니다.
		DefaultTableModel combinedModel = new DefaultTableModel(combinedColumnNames, 0);

		// 모델1과 모델2의 데이터를 결합하여 새로운 모델에 추가합니다.
		int rowCount1 = model1.getRowCount();
		int rowCount2 = model2.getRowCount();
		int maxRowCount = Math.max(rowCount1, rowCount2);

		for (int row = 0; row < maxRowCount; row++) {
			Object[] combinedRowData = new Object[columnCount1 + columnCount2];
			for (int col = 0; col < columnCount1; col++) {
				combinedRowData[col] = (row < rowCount1) ? model1.getValueAt(row, col) : null;
			}
			for (int col = 0; col < columnCount2; col++) {
				combinedRowData[columnCount1 + col] = (row < rowCount2) ? model2.getValueAt(row, col) : null;
			}
			combinedModel.addRow(combinedRowData);
		}

		return combinedModel;
	}
}
