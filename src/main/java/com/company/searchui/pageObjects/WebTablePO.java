package com.company.searchui.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * WebTable Page Object Class
 *
 * @author phildolganov
 */
public class WebTablePO<M> {
    private WebElement table;

    /**
     * constructor
     *
     * @param table
     * @throws Exception
     */
    public WebTablePO(WebElement table) throws Exception {
        setTable(table);
    }

    /**
     * setTable - method to set the table on the page
     *
     * @param table
     * @throws Exception
     */
    public void setTable(WebElement table) throws Exception {
        this.table = table;
    }

    /**
     * getTable - method to get the table on the page
     *
     * @return WebElement
     * @throws Exception
     */
    public WebElement getTable() throws Exception {
        return this.table;
    }

    // Note: JavaDoc will be eliminated in these examples for simplicity sake

    public int getRowCount(){
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));

        return tableRows.size();
    }

    public int getColumnCount(){
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement headerRow = tableRows.get(1);
        List<WebElement> tableCols = headerRow.findElements(By.tagName("td"));

        return tableCols.size();
    }

    public int getColumnCount(int index){
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement headerRow = tableRows.get(index);
        List<WebElement> tableCols = headerRow.findElements(By.tagName("td"));

        return tableCols.size();
    }

    public String getRowData(int rowIndex){
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement currentRow = tableRows.get(rowIndex);

        return currentRow.getText();
    }

    public String getCellData(int rowIndex, int colIndex){
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        WebElement currentRow = tableRows.get(rowIndex);
        List<WebElement> tableCols = currentRow.findElements(By.tagName("td"));
        WebElement cell = tableCols.get(colIndex - 1);

        return cell.getText();
    }
}
