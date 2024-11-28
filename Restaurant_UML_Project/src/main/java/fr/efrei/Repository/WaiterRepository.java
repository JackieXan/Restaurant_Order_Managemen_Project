package fr.efrei.Repository;

import fr.efrei.Domain.Waiter;

import java.util.ArrayList;
import java.util.List;

public class WaiterRepository implements IWaiterRepository {
    private static WaiterRepository repository = null;
    private final List<Waiter> waiters;

    private WaiterRepository() {
        this.waiters = new ArrayList<>();
    }

    public static WaiterRepository getRepository() {
        if (repository == null) {
            repository = new WaiterRepository();
        }
        return repository;
    }

    @Override
    public Waiter create(Waiter waiter) {
        if (waiter != null && !waiters.contains(waiter)) {
            waiters.add(waiter);
            return waiter;
        }
        return null;
    }

    @Override
    public Waiter read(Integer id) {
        for (Waiter waiter : waiters) {
            if (waiter.getId() == id) {
                return waiter;
            }
        }
        return null;
    }

    @Override
    public Waiter update(Waiter waiter) {
        Waiter existingWaiter = read(waiter.getId());
        if (existingWaiter != null) {
            waiters.remove(existingWaiter);
            waiters.add(waiter);
            return waiter;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Waiter waiter = read(id);
        if (waiter != null) {
            waiters.remove(waiter);
            return true;
        }
        return false;
    }

    @Override
    public List<Waiter> getAll() {
        return new ArrayList<>(waiters);
    }
}
