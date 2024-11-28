package fr.efrei.Repository;

import fr.efrei.Domain.MenuItem;

import java.util.List;

public interface IMenuItemRepository extends IRepository<MenuItem, Integer> {
    List<MenuItem> getAll();
}
