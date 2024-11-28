package fr.efrei.Repository;

import fr.efrei.Domain.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepository implements IManagerRepository {
    private static ManagerRepository repository = null;
    private final List<Manager> managerList;

    private ManagerRepository() {
        this.managerList = new ArrayList<>();
    }

    public static ManagerRepository getRepository() {
        if (repository == null) {
            repository = new ManagerRepository();
        }
        return repository;
    }

    @Override
    public Manager create(Manager manager) {
        this.managerList.add(manager);
        return manager;
    }

    @Override
    public Manager read(Integer id) {
        for (Manager manager : this.managerList) {
            if (manager.getId() == id) {
                return manager;
            }
        }
        return null;
    }

    @Override
    public Manager update(Manager manager) {
        Manager existingManager = read(manager.getId());
        if (existingManager != null) {
            delete(existingManager.getId());
            this.managerList.add(manager);
            return manager;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Manager manager = read(id);
        if (manager != null) {
            this.managerList.remove(manager);
            return true;
        }
        return false;
    }

    @Override
    public List<Manager> getAll() {
        return new ArrayList<>(this.managerList);
    }
}
