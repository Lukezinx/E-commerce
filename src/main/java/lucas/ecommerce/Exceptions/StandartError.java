package lucas.ecommerce.Exceptions;

import java.time.LocalDate;


public record StandartError(LocalDate timestamp,
                            Integer status,
                            String error,
                            String message,
                            String path) {

}
