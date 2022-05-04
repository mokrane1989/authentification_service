package io.auth.auth.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by k.kezzar on 26/02/2020.
 */
public class AuthentificationResponse {

    Date expirationDate;
    public AuthentificationResponse(String jwt) {
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, 1);
       setExpirationDate( c.getTime());
         this.token = jwt;
    }

    private final String token;

    public String getToken() {
        return token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
