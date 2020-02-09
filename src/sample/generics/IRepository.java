package sample.generics;

public interface IRepository<ID,E extends Entity> {


    /**
     * Save an entity.
     * @param e - entity, must be not null;
     * @return null, if the entity is succesfully saved, e - otherwise(eg. entity already exists)
     * @throws ValidationException - if the entity is not valid!
     */
    E save(E e) throws ValidationException;

    /**
     * Update an entity
     * @param e - entity, must be not null;
     * @return null, if the entity is succesfully update, e - otherwise (eg. the id of the entity not found)
     * @throws ValidationException - if the entity is not valid!
     */
    E update(E e) throws ValidationException;

    /**
     * Remove an entity
     * @param e - the entity, must be not null
     * @return removed entity if is succesfully deleted, null - otherwise
     */
    E remove(E e);

    /**
     * Find the entity with specific ID
     * @param id: - the ID, must be not null.
     * @return entity with specific id, or null.
     */
    E findOne(ID id);

    /**
     * @return an Iterator with all Entities saved.
     */
    Iterable<E> findAll();

}
