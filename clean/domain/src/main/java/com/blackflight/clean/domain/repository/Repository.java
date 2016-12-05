package com.blackflight.clean.domain.repository;

import java.util.Collection;

/**
 * Repository interface.
 *
 * @param <TKey> Class representing the id used to identify items in this repository
 * @param <TItem> Class representing the contents of the items held by this repository.
 */
public interface Repository<TKey, TItem extends Identifiable<TKey>> {

    /**
     * Returns the only item that is uniquely identified by the provided id or null if there is
     * no value associated to it.
     * @param id The id that uniquely identifies the requested item.
     * @return The item associated to the provided id or null if there is not any.
     * @throws Exception any exception on the repository.
     */
    TItem getById(TKey id) throws Exception;

    /**
     * Returns all the items available in the repository or null if the operation does not make
     * sense in this repository.
     * @return A collection of items or null if the operation is not implemented.
     * @throws Exception any exception on the repository.
     */
    Collection<TItem> getAll() throws Exception;

    /**
     * Adds or updates the provided item into this repository.
     * @param item The item to be persisted.
     * @throws Exception any exception on the repository.
     */
    void addOrUpdate(TItem item) throws Exception;

    /**
     * Adds or updates all the provided items into this repository.
     * @param items A collection of items to be persisted.
     * @throws Exception any exception on the repository.
     */
    void addOrUpdateAll(Collection<TItem> items) throws Exception;

    /**
     * Deletes a item given its associated id.
     * @param id The id that uniquely identifies the item to be deleted.
     * @throws Exception any exception on the repository.
     */
    void deleteById(TKey id) throws Exception;

    /**
     * Delete all the items stored in this repository.
     * @throws Exception any exception on the repository.
     */
    void deleteAll() throws Exception;
}
