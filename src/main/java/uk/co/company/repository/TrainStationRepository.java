package main.java.uk.co.company.repository;

import main.java.uk.co.company.domain.TrainStation;

import java.io.Serializable;
import java.util.List;

/**
 * Repository interface to provide DataSource functionality, such as
 * findAll, findByOneByName, add and so on for TrainStation.
 *
 * This interfaces follows what Spring Data Repositories provides for
 * Crud JPA functionality.
 *
 * Created by clelio on 21/04/15.
 */
public interface TrainStationRepository<T extends TrainStation> {

    /**
     * Returns a list of all stored TrainStations
     * @return
     */
    List<T> findAll();

    /**
     * Persist a new TrainStation to a Data Store
     * @param trainStation
     * @return
     */
    <S extends T> S save(T trainStation);

    /**
     * Return a TrainStation by name;
     * @param name
     * @return
     */
    T findByOneByName(String name);
}
