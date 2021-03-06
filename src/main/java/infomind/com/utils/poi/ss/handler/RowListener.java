package infomind.com.utils.poi.ss.handler;

import infomind.com.utils.poi.ss.reader.SpreadsheetReader;


/**
 * An abstract representation of Row level Callback for {@link SpreadsheetReader} implementations.
 */
public interface RowListener<T> {

    /**
     * This method will be called after every row by the {@link SpreadsheetReader} implementation.
     * 
     * @param rowNum the Row Number in the sheet. (indexed from 0)
     * @param rowObj the java bean constructed using the Row data. 
     */
    void row(int rowNum, T rowObj);

}
