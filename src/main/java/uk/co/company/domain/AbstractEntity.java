package main.java.uk.co.company.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Abstract entity to provide common hashCode and equals functionality.
 * Also this class implements serializable in case used to stream over
 * the internet or persist it into a DataStore (i.e DB, file and so on).
 *
 * Created by clelio on 21/04/15.
 */
public abstract class AbstractEntity implements Serializable {

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
