package ee.ege.veebipood.model;


import lombok.Data;

import java.util.Date;

@Data
public class Token {
    private String token;
    private Date expiration;
}
