package ee.ege.veebipood.model.payment;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentStatusResponse {
    private Object error;
    private Object debtor_iban;
    private String creditor_iban;
    private String payment_reference;
    private String payment_state;
    private Object token;
}

@Data
class ProcessingError{
    private int code;
    private String message;
}

@Data
class Root{
    private String account_name;
    private String order_reference;
    private Object email;
    private String customer_ip;
    private String customer_url;
    private Date payment_created_at;
    private double initial_amount;
    private double standing_amount;
    private String payment_reference;
    private String payment_link;
    private String api_username;
    private ProcessingError processing_error;
    private Object warnings;
    private int stan;
    private int fraud_score;
    private String payment_state;
    private String payment_method;
    private PaymentStatusResponse ob_details;
    private Date transaction_time;
}

