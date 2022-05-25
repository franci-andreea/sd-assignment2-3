package com.example.foodpanda.business.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidator
{
    /**
     * CREATIONAL DESIGN PATTERN
     * SINGLETON DESIGN PATTERN IMPLEMENTATION FOR
     * REGISTER VALIDATOR CLASS
     */

    private static RegisterValidator validator;

    private RegisterValidator()
    {

    }

    public static RegisterValidator getInstance()
    {
        if(validator == null)
            validator = new RegisterValidator();
        return validator;
    }

    public boolean checkName(String name)
    {
        if(name == null || name.isEmpty())
            return false;

        return true;
    }

    /**
     * RULES FOR EMAIL
     * Non-empty field
     * A-Z characters allowed
     * a-z characters allowed
     * 0-9 numbers allowed
     * Additionally email may contain only dot(.), dash(-) and underscore(_)
     * Rest all characters are not allowed
     * @param email - email introduced by the user
     * @return false - at least one rule is not respected
     *         true - successful validation
     */
    public boolean checkEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return !email.isEmpty() && matcher.matches();
    }

    /**
     * RULES FOR PHONE NUMBER
     * Non-empty field
     * The number has ten digits and nothing else
     * @param phoneNumber - phone number introduced by the user
     * @return false - the rule is not respected
     *         true - successful validation
     */
    public boolean checkPhoneNumber(String phoneNumber)
    {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return !phoneNumber.isEmpty() && matcher.matches();
    }

    public boolean checkAddress(String address)
    {
        return address != null && !address.isEmpty();
    }

    /**
     * RULES FOR USERNAME
     * Non-empty field
     * Only allow alphabet and numbers
     * @param username - username inserted by the user
     * @return false - at least one rule is not respected
     *         true - all rules are respected and validation is successful
     */

    public boolean checkUsername(String username)
    {
        String regex = "^[A-Za-z][A-Za-z0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return !username.isEmpty() && matcher.matches();
    }
}
