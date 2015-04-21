package main.java.uk.co.company;

import com.google.common.collect.ImmutableList;
import main.java.uk.co.company.domain.TrainStation;
import main.java.uk.co.company.domain.TrainStationSearchVO;
import main.java.uk.co.company.repository.TrainStationRepository;
import main.java.uk.co.company.service.TicketMachineService;
import main.java.uk.co.company.service.TicketMachineServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.String.format;
import static java.lang.System.out;

/**
 * Created by clelio on 21/04/15.
 */
public class Application {

    public static void main(String args[]) {

        // Stubbing the train station repository
        final TrainStationRepository repository = new TrainStationRepository() {

            @Override
            public List findAll() {
                return new ImmutableList.Builder<TrainStation>()
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
            }

            @Override
            public TrainStation save(TrainStation trainStation) {
                throw new UnsupportedOperationException("Method not implemented!");
            }

            @Override
            public TrainStation findByOneByName(String name) {
                throw new UnsupportedOperationException("Method not implemented!");
            }
        };

        final TicketMachineService ticketMachineService = new TicketMachineServiceImpl(repository);


        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String trainStationName = null;
        TrainStationSearchVO trainStationSearchVO = null;

        while(true) {
            out.print("User input (or enter 'EXIT' to finish): ");

            try {
                trainStationName = br.readLine();
                if (trainStationName.equals("EXIT")) {
                    System.exit(0);
                }

                 trainStationSearchVO = ticketMachineService.searchStationsAndNextExpectedChars(trainStationName);
            } catch (IOException ioe) {
                System.exit(1);
            }

            out.println(trainStationSearchVO);
        }
    }
}
