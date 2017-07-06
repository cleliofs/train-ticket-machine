package uk.co.company.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Arrays.toString;

/**
 * A value object to return the final solution containing the
 * list of all stations and next expect chars for a given matched
 * station name prefix.
 *
 * This VO is immutable to assure once created its values are not changed.
 *
 * Created by clelio on 21/04/15.
 */
public class TrainStationSearchVO extends AbstractEntity {

    private List<TrainStation> trainStations;
    private Set<Character> nextExpectChars;

    public TrainStationSearchVO(List<TrainStation> trainStations, Set<Character> nextExpectChars) {
        this.trainStations = trainStations;
        this.nextExpectChars = nextExpectChars;
    }

    public List<TrainStation> getTrainStations() {
        return trainStations;
    }

    public Set<Character> getNextExpectChars() {
        return nextExpectChars;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(format("\nNext expected chars: %s", Arrays.toString(getNextExpectChars().toArray())));
        sb.append(format("\nMatched stations: %s", Arrays.toString(getTrainStations().toArray())));
        return sb.toString();
    }
}
