package fr.efrei.Repository;

import fr.efrei.Domain.Manager;

import java.util.List;

public interface IManagerRepository extends IRepository<Manager, Integer> {
    List<Manager> getAll();
}
