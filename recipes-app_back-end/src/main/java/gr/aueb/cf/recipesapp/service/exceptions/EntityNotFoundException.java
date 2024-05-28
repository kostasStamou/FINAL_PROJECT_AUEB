package gr.aueb.cf.recipesapp.service.exceptions;

public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(Class<?> entityClass) {
        super("entity " + entityClass.getSimpleName()  + " does not exist");
    }
}
