package gr.aueb.cf.recipesapp.service.exceptions;

public class EntityAlreadyExistsException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException(Class<?> entityClass) {
        super("entity " + entityClass.getSimpleName()  + " already exists");
    }
}
