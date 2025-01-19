package cristhianrp.forohub.api.infra.errores;

public class ValidacionException extends RuntimeException {
    public ValidacionException(String mensage) {
        super(mensage);
    }
}
