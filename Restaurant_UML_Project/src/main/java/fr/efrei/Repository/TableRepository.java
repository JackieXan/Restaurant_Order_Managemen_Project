package fr.efrei.Repository;


import fr.efrei.Domain.Table;

import java.util.ArrayList;
import java.util.List;
public class TableRepository implements ITableRepository{

    private static TableRepository repository = null;
    private final List<Table> tableList;
    private TableRepository(){
        this.tableList = new ArrayList<>();
    }

    public static TableRepository getRepository(){
        if(repository == null) {
            repository = new TableRepository();
        }
        return repository;
    }

    @Override
    public Table create(Table table){
        this.tableList.add(table);
        return table;
    }

    @Override
    public Table read(Integer id){
        for (Table table : this.tableList){
            if (table.getTableNumber() == id) {
                return  table;
            }
        }
        return null;
    }
    @Override
    public Table update(Table table) {
        Table existingTable = read(table.getTableNumber());
        if (existingTable != null) {
            delete(existingTable.getTableNumber());
            this.tableList.add(table);
            return table;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Table table = read(id);
        if (table != null) {
            this.tableList.remove(table);
            return true;
        }
        return false;
    }

    @Override
    public List<Table> getAll() {
        return new ArrayList<>(this.tableList);
    }
}
