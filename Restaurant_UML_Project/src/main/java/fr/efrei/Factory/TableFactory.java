package fr.efrei.Factory;

import fr.efrei.Domain.Table;
import fr.efrei.Util.Helper;
public class TableFactory {
    private static int currentTableNumber = 1;
    public static Table buildTable(){

        int tableNumber = getNetxtTableNumber();

        return new Table.Builder()
                .tableNumber(tableNumber)
                .build();
    }
    public static int getNetxtTableNumber(){
        return currentTableNumber++;
    }

    public static Table buildTable(int tableNumber){

        return new Table.Builder()
                .tableNumber(tableNumber)
                .build();
    }
}
