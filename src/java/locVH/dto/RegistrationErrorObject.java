package locVH.dto;

import java.io.Serializable;

/**
 *
 * @author LocVH
 */
public class RegistrationErrorObject implements Serializable {

    private String usernameError, passwordError, confirmError, fullnameError, DOBError,phoneNumberError,emailError,addressError;

    public void RegistrationErrorObject() {
    }

    
    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }   
    
    public String getUsernameError() {
        return usernameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }



    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public String getPhoneNumberError() {
        return phoneNumberError;
    }

    public void setPhoneNumberError(String phoneNumberError) {
        this.phoneNumberError = phoneNumberError;
    } 

    public String getDOBError() {
        return DOBError;
    }

    public void setDOBError(String DOBError) {
        this.DOBError = DOBError;
    }

    
}
