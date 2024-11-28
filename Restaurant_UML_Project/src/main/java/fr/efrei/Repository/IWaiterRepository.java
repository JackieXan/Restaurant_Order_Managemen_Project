package fr.efrei.Repository;

import fr.efrei.Domain.Waiter;

import java.util.List;

public interface IWaiterRepository extends IRepository<Waiter, Integer> {
    List<Waiter> getAll();

}
