package com.company.searchui.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

/**
 * Home Page Object Class
 *
 * @author phildolganov
 *
 */
public class MyHomePagePO<M extends WebElement> extends WebTablePO {
    /**
     * constructor
     *
     * @param table
     * @throws Exception
     */
    public MyHomePagePO(M table) throws Exception {
        super(table);
    }

    @FindBy(id = "my_table")
    protected M myTable;

    // table methods
    public int getTableRowCount() throws Exception {
        WebTablePO table = new WebTablePO(getTable());
        return table.getRowCount();
    }

    public int getTableColumnCount() throws Exception {
        WebTablePO table = new WebTablePO(getTable());
        return  table.getColumnCount();
    }

    public int getTableColumnCount(int index) throws Exception {
        WebTablePO table = new WebTablePO(getTable());
        return table.getColumnCount(index);
    }

    public String getTableCellData(int row, int column) throws Exception {
        WebTablePO table = new WebTablePO(getTable());
        return table.getCellData(row, column);
    }

    public String getTableRowData(int row) throws Exception {
        WebTablePO table = new WebTablePO(getTable());
        return table.getRowData(row).replace("\n"," ");
    }

    public void verifyTableRowData(String expRowText) throws Exception {
        String actRowText = "";
        int totalNumRows = getTableRowCount();

        // parse each row until row data found
        for (int i = 0; i < totalNumRows; i++) {
            if (this.getTableRowData(i).contains(expRowText)){
                actRowText = this.getTableRowData(i);
                break;
            }
        }

        // verify the row data
        try {
            assertEquals(actRowText, expRowText, "Verify Row Data");
        } catch (AssertionError e) {
            String error = "Row data '" + expRowText + "' Not found!";
            throw new Exception(error);
        }
    }
}
