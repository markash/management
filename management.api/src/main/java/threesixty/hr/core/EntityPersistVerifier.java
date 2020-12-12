package threesixty.hr.core;

import java.util.Objects;

import threesixty.hr.core.exception.EntityPersistException;

public interface EntityPersistVerifier {

    /**
     * Verifies that the entity is not null else throws EntityCreateException
     * @param entity The entity to verify
     */
    default void verifyNotNull(final Object entity) {

        if (Objects.isNull(entity)) {
            throw new EntityPersistException("The entity to persist is null");
        }
    }
    
    /**
     * Verifies that the entity is not null else throws EntityCreateException
     * @param entity The entity to verify
     * @param message The message in the event that the entity is null
     */
    default void verifyNotNull(
        final Object entity,
        final String message) {

        if (Objects.isNull(entity)) {
            throw new EntityPersistException(message);
        }
    }
}
