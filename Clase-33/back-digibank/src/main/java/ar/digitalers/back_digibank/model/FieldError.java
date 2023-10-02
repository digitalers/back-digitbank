package ar.digitalers.back_digibank.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldError {

    private String field;
    private String errorCode;

}
