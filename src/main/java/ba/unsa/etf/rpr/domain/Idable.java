package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces having an ID field.
 */
public interface Idable {
    /**
     * Sets id.
     *
     * @param id
     */
    void setId(int id);

    /**
     * Gets id.
     *
     * @return the id
     */
    int getId();
}
