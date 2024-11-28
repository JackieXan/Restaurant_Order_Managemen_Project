package fr.efrei.Repository;

import fr.efrei.Domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository implements IMenuItemRepository {
    private static MenuItemRepository repository = null;
    private final List<MenuItem> menuItemList;

    private MenuItemRepository() {
        this.menuItemList = new ArrayList<>();
    }

    public static MenuItemRepository getRepository() {
        if (repository == null) {
            repository = new MenuItemRepository();
        }
        return repository;
    }

    @Override
    public MenuItem create(MenuItem menuItem) {
        this.menuItemList.add(menuItem);
        return menuItem;
    }

    @Override
    public MenuItem read(Integer id) {
        for (MenuItem menuItem : this.menuItemList) {
            if (menuItem.getId() == id) {
                return menuItem;
            }
        }
        return null;
    }

    @Override
    public MenuItem update(MenuItem menuItem) {
        MenuItem existingMenuItem = read(menuItem.getId());
        if (existingMenuItem != null) {
            delete(existingMenuItem.getId());
            this.menuItemList.add(menuItem);
            return menuItem;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        MenuItem menuItem = read(id);
        if (menuItem != null) {
            this.menuItemList.remove(menuItem);
            return true;
        }
        return false;
    }

    @Override
    public List<MenuItem> getAll() {
        return new ArrayList<>(this.menuItemList);
    }
}
