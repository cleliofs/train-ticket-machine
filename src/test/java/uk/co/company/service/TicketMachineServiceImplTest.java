package test.java.uk.co.company.service;

import com.google.common.collect.ImmutableList;
import main.java.uk.co.company.domain.TrainStation;
import main.java.uk.co.company.domain.TrainStationSearchVO;
import main.java.uk.co.company.exception.StationNotFoundException;
import main.java.uk.co.company.repository.TrainStationRepository;
import main.java.uk.co.company.service.TicketMachineServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by clelio on 21/04/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketMachineServiceImplTest {

    // Immutable list of stations
    private static final List TRAIN_STATIONS = new ImmutableList.Builder<TrainStation>()
            .add(new TrainStation.Builder().withName("Dartford").withLocation("DA3 XXX").build())
            .add(new TrainStation.Builder().withName("Dartmouth").withLocation("TQ6 XXX").build())
            .add(new TrainStation.Builder().withName("Deptford").withLocation("SE8 XXX").build())
            .add(new TrainStation.Builder().withName("Tower Hill").withLocation("EC1 XXX").build())
            .add(new TrainStation.Builder().withName("Derby").withLocation("DE22 XXX").build())
            .add(new TrainStation.Builder().withName("Liverpool Street").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Liverpool Lime Street").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Paddington").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Euston").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("London Bridge").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Lancaster Gate").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Victoria").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Kings Cross").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Acton Town").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Aldgate").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Aldgate East").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Alperton").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Amersham").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Angel").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Archway").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Arnos Grove").withLocation("XXX YYY").build())
            .add(new TrainStation.Builder().withName("Arsenal").withLocation("XXX YYY").build())
        .build();

    private TicketMachineServiceImpl underTest;

    @Mock
    private TrainStationRepository trainStationRepository;

    @Before
    public void setup() {
        underTest = new TicketMachineServiceImpl(trainStationRepository);
    }

    @Test
    public void testFindStationByName_Success() throws Exception {
        final String stationName = "Kings Cross";
        final TrainStation trainStation = new TrainStation.Builder().withName("Kings Cross").withLocation("XXX YYY").build();

        when(trainStationRepository.findByOneByName(stationName)).thenReturn(trainStation);

        final TrainStation retTrainStation = underTest.findStationByName(stationName);

        verify(trainStationRepository).findByOneByName(stationName);

        assertEquals(trainStation, retTrainStation);
    }

    @Test(expected = StationNotFoundException.class)
    public void testFindStationByName_NotFound() throws Exception {
        final String stationName = "Kings Cross";

        when(trainStationRepository.findByOneByName(stationName)).thenReturn(null);

        final TrainStation retTrainStation = underTest.findStationByName(stationName);

        verify(trainStationRepository).findByOneByName(stationName);
    }

    @Test
    public void testFindStationsMatchingWithStationPrefix_Success1() throws Exception {
        final String stationPrefix = "dart";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final List<TrainStation> stations = underTest.findStationsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(stations);
        assertEquals(2, stations.size());
        assertTrue(stations.get(0).getName().toLowerCase().startsWith(stationPrefix));
        assertTrue(stations.get(1).getName().toLowerCase().startsWith(stationPrefix));
    }

    @Test
    public void testFindStationsMatchingWithStationPrefix_Success2() throws Exception {
        final String stationPrefix = "l";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final List<TrainStation> stations = underTest.findStationsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(stations);
        assertEquals(4, stations.size());
        assertTrue(stations.get(0).getName().toLowerCase().startsWith(stationPrefix));
        assertTrue(stations.get(1).getName().toLowerCase().startsWith(stationPrefix));
        assertTrue(stations.get(2).getName().toLowerCase().startsWith(stationPrefix));
        assertTrue(stations.get(3).getName().toLowerCase().startsWith(stationPrefix));
    }

    @Test
    public void testFindStationsMatchingWithStationPrefix_NotFound() throws Exception {
        final String stationPrefix = "prefixNonExisting";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final List<TrainStation> stations = underTest.findStationsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(stations);
        assertTrue(stations.isEmpty());
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_Success1() throws Exception {
        final String stationPrefix = "dart";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(2, nextValidCharsByNamePrefix.size());
        assertTrue(nextValidCharsByNamePrefix.contains('f'));
        assertTrue(nextValidCharsByNamePrefix.contains('m'));
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_Success2() throws Exception {
        final String stationPrefix = "liverpool";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(1, nextValidCharsByNamePrefix.size());
        assertTrue(nextValidCharsByNamePrefix.contains(' '));
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_Success3() throws Exception {
        final String stationPrefix = "d";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(2, nextValidCharsByNamePrefix.size());
        assertTrue(nextValidCharsByNamePrefix.contains('a'));
        assertTrue(nextValidCharsByNamePrefix.contains('e'));
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_Success4() throws Exception {
        final String stationPrefix = "l";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(3, nextValidCharsByNamePrefix.size());
        assertTrue(nextValidCharsByNamePrefix.contains('i'));
        assertTrue(nextValidCharsByNamePrefix.contains('o'));
        assertTrue(nextValidCharsByNamePrefix.contains('a'));
    }


    @Test
    public void testFindNextValidCharsByNamePrefix_Success5() throws Exception {
        final String stationPrefix = "de";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(2, nextValidCharsByNamePrefix.size());
        assertTrue(nextValidCharsByNamePrefix.contains('r'));
        assertTrue(nextValidCharsByNamePrefix.contains('p'));
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_Success6() throws Exception {
        final String stationPrefix = "derby";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(0, nextValidCharsByNamePrefix.size());
    }

    @Test
    public void testFindNextValidCharsByNamePrefix_NotFound() throws Exception {
        final String stationPrefix = "derby";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final Set<Character> nextValidCharsByNamePrefix = underTest.findNextValidCharsByNamePrefix(stationPrefix);

        verify(trainStationRepository).findAll();

        assertNotNull(nextValidCharsByNamePrefix);
        assertEquals(0, nextValidCharsByNamePrefix.size());
    }

    @Test
    public void testSearchStationsAndNextExpectedChars() throws Exception {
        final String stationPrefix = "dart";

        when(trainStationRepository.findAll()).thenReturn(TRAIN_STATIONS);

        final TrainStationSearchVO trainStationSearchVO = underTest.searchStationsAndNextExpectedChars(stationPrefix);

        verify(trainStationRepository, times(2)).findAll();

        assertNotNull(trainStationSearchVO);
        assertEquals(2, trainStationSearchVO.getTrainStations().size());
        assertTrue(trainStationSearchVO.getTrainStations().get(0).getName().toLowerCase().startsWith(stationPrefix));
        assertTrue(trainStationSearchVO.getTrainStations().get(1).getName().toLowerCase().startsWith(stationPrefix));

        assertEquals(2, trainStationSearchVO.getNextExpectChars().size());
        assertTrue(trainStationSearchVO.getNextExpectChars().contains('f'));
        assertTrue(trainStationSearchVO.getNextExpectChars().contains('m'));
    }
}
