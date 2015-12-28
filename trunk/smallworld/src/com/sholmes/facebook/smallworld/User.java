package com.sholmes.facebook.smallworld;

/**
 *
 * @author sholmes
 *
 */
public class User {

    private Integer id;
    private Double latitude;
    private Double longitude;

    /**
     *
     * @param aId as
     * @param aLatitude as
     * @param aLongitude as
     */
    public User(Integer aId, Double aLatitude, Double aLongitude) {
        id = aId;
        latitude = aLatitude;
        setLongitude(aLongitude);
    }

    /**
     *
     * @param aLatitude as
     * @param aLongitude a
     */
    public final void setCoordinates(final Double aLatitude,
            final Double aLongitude) {

    }

    /**
     * @param aLatitude the latitude to set
     */
    public final void setLatitude(final Double aLatitude) {
        this.latitude = aLatitude;
    }
    /**
     * @return the latitude
     */
    public final Double getLatitude() {
        return latitude;
    }
    /**
     * @param aId the id to set
     */
    public final void setId(final Integer aId) {
        this.id = aId;
    }
    /**
     * @return the id
     */
    public final Integer getId() {
        return id;
    }

    /**
     * @param aLongitude the longitude to set
     */
    public final void setLongitude(final Double aLongitude) {
        this.longitude = aLongitude;
    }

    /**
     * @return the longitude
     */
    public final Double getLongitude() {
        return longitude;
    }
}
