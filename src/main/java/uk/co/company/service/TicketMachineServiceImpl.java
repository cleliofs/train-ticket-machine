package main.java.uk.co.company.service;

import main.java.uk.co.company.domain.TrainStation;
import main.java.uk.co.company.domain.TrainStationSearchVO;
import main.java.uk.co.company.exception.StationNotFoundException;
import main.java.uk.co.company.repository.TrainStationRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Created by clelio on 21/04/15.
 */
public class TicketMachineServiceImpl implements TicketMachineService {

    private TrainStationRepository repository;

    public TicketMachineServiceImpl(TrainStationRepository repository) {
        this.repository = repository;
    }


    @Override
    public TrainStation findStationByName(String stationName) {
        final TrainStation trainStation = repository.findByOneByName(stationName);
        if (trainStation == null) {
            throw new StationNotFoundException(format("TrainStation[name=%s] not found in the DataStore", stationName));
        }

        return trainStation;

    }

    @Override
    public List<TrainStation> findStationsByNamePrefix(String stationNamePrefix) {
        final List<TrainStation> stations = repository.findAll();
        return stations.stream().filter(s -> s.startsWithNamePrefix(stationNamePrefix)).collect(Collectors.toList());
    }

    @Override
    public Set<Character> findNextValidCharsByNamePrefix(String stationNamePrefix) {
        final List<TrainStation> stations = findStationsByNamePrefix(stationNamePrefix);
        final Set<Character> nextChars = new HashSet<>();

        stations.stream().filter(s -> s.getName().length() > stationNamePrefix.length()).forEach(s -> {
            int posNextChar = stationNamePrefix.length();
            nextChars.add(s.getName().charAt(posNextChar));
        });

        return nextChars;
    }

    @Override
    public TrainStationSearchVO searchStationsAndNextExpectedChars(String stationNamePrefix) {
        final List<TrainStation> stations = findStationsByNamePrefix(stationNamePrefix);
        final Set<Character> nextExpectedChars = findNextValidCharsByNamePrefix(stationNamePrefix);
        return new TrainStationSearchVO(stations, nextExpectedChars);
    }
}
