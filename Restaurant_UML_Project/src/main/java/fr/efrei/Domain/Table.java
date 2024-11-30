package fr.efrei.Domain;

public class Table {
    private final int tableNumber;

    public Table(Builder builder) {
        this.tableNumber = builder.tableNumber;
    }

    public static class Builder{
        private int tableNumber;

        public Builder tableNumber(int tableNumber){
            this.tableNumber = tableNumber;
            return this;
        }

        public Table build(){
            return new Table(this);
        }
    }
    public int getTableNumber(){
        return tableNumber;
    }
}
