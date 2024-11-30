package fr.efrei.Repository;

import fr.efrei.Domain.Table;

import java.util.List;

public interface ITableRepository extends IRepository<Table, Integer> {
    List<Table> getAll();
}
