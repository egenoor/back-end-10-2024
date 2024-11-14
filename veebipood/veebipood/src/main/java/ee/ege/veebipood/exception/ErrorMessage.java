package ee.ege.veebipood.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private String name;
    private Date date;
}
