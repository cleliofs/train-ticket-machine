package main.java.uk.co.company.service;

import main.java.uk.co.company.domain.TrainStation;
import main.java.uk.co.company.domain.TrainStationSearchVO;

import java.util.List;
import java.util.Set;

/**
 * Service to provide ticket machine search functionality.
 *
 * Created by clelio on 21/04/15.
 */
public interface TicketMachineService {

    /**
     * Finds the TrainStation that matches exactly the informed station name
     *
     * @param stationName
     * @return
     */
    TrainStation findStationByName(String stationName);

    /**
     * Returns a list of all TrainStations matching a station name prefix
     *
     * @param stationNamePrefix
     * @return
     */
    List<TrainStation> findStationsByNamePrefix(String stationNamePrefix);

    /**
     * Returns a set of all possible chars that are next expected matching the
     * given station name prefix.
     *
     * @param stationNamePrefix
     * @return
     */
    Set<Character> findNextValidCharsByNamePrefix(String stationNamePrefix);

    /**
     * Returns a VO (Value Object) containing the list of all stations and next expected chars
     * that matched the given station name prefix.
     *
     * @param stationNamePrefix
     * @return
     */
    TrainStationSearchVO searchStationsAndNextExpectedChars(String stationNamePrefix);

}
