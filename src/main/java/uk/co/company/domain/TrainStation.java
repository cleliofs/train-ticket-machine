package uk.co.company.domain;

/**
 * An Entity to represent train stations.
 *
 * This entity follows the Builder creation design patterns,
 * which assures a unique mechanism to create the entity and
 * it favors immutability.
 *
 * Created by clelio on 21/04/15.
 */
public class TrainStation extends AbstractEntity {

    private String name;
    private String description;
    private String location;

    private TrainStation(final Builder builder) {
        if (builder != null) {
            this.name = builder.name;
            this.description = builder.description;
            this.location = builder.location;
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public boolean startsWithNamePrefix(String stationNamePrefix) {
        return name.toLowerCase().startsWith(stationNamePrefix);
    }

    @Override
    public String toString() {
        return name;
    }

    public static final class Builder {
        private String name;
        private String description;
        private String location;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public Builder withLocation(final String location) {
            this.location = location;
            return this;
        }

        public TrainStation build() {
            return new TrainStation(this);
        }
    }
}
