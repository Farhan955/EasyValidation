package com.idealogics.easyvalidation;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by FA on 5/28/2021.
 */
public class EasyValidation {


    public boolean isValidName(String name, int security) {
        while (true) {
            if (security >= 2 && !checklen(name)) break;
            if (security >= 3 && !checknum(name)) break;
            if (security >= 4 && !checkvowel(name)) break;
            if (security >= 5 && !checkcon(name)) break;
            return true;
        }
        return false;
    }

    /*Checks if length of both strings are less than three*/
    public boolean checklen(String Firstname) {
        if (Firstname.length() < 3)
            return false;
        return true;
    }

    /*Checks if both strings contain non-alphabetic characters excluding a space character*/
    public boolean checknum(String Firstname) {
        int length = 0, length2 = 0, i = 0;
        char c;
        String Fullname;

        length = Firstname.length();


        for (i = 0; i < length; i++)  //Check for `Firstname`
        {
            c = Firstname.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && c != ' ') {
                return false;
            }
        }


        return true;
    }

    /*Checks if both strings contain more than 2 consecutive vowels. Also checks if they contain more than 3 vowels. Both are done in each word*/
    public boolean checkvowel(String Firstname) {
        int numv = 0, conv = 0, length = Firstname.length();
        char c;
        int i;
        char[] vowel = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U', '\0'};
        for (i = 0; i < length; i++) {
            c = Firstname.charAt(i);
            for (char test : vowel) {
                if (c != ' ') {

                    if (test == c) //If current char is a vowel
                    {
                        numv++;
                        conv++; //Increase counters
                        if (conv > 2 || numv > 3) //Invalid name detected
                            return false;
                        break;
                    }
                    if (test == '\0') conv = 0;
                } else {
                    numv = conv = 0;
                    break;
                } //New word. So reset counters
            }
        }
        numv = conv = 0;

        return true;
    }

    /*Checks if both strings contain more than 2 consecutive same consonants. Also checks if they contain more than 4 consonants. Both are done in each word*/
    public boolean checkcon(String Firstname) {
        int num = 0, length = Firstname.length(), con = 0;
        char c, tmp = 'a';
        int i;
        for (i = 0; i < length; i++) {
            if ((c = Firstname.charAt(i)) != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != ' ') //If current character is not a vowel or a space
            {
                num++;
                if (tmp != 'a' && c == tmp)
                    con++;
                if (num > 3 || con > 1) //Invalid name
                    return false;
                tmp = c;
                continue;
            }
            num = 0;
            con = 0;
            tmp = 'a';
        }
        num = 0;
        con = 0;
        tmp = 'a'; //Reset everything

        return true;
    }


    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPhone(String phone) {
        String allCountryRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";

        if (phone.matches(allCountryRegex)){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isValidURL(String url) {
        if (url == null) {
            return false;
        }
        if (url.isEmpty()) {
            return false;
        }
        boolean b = Patterns.WEB_URL.matcher(url).matches();
        return b;
    }


    private  final char[] SPECIAL = "!@%^*()_-,.;:'{[]}$!+'\'-#?_%&/".toCharArray();
    private  final char[] NUMBER = "0123456789".toCharArray();


    public  boolean isValidPassword(String password, int security) {

        String lowerPass = password.toLowerCase();
        String upperPass = password.toUpperCase();
        if (security == 1) {

            if (password.length() > 5)
                return true;
            return false;


        }

        if (security == 2) {

            if (password.length() > 0 && !password.equals(lowerPass) && !password.equals(upperPass))
                return true;
            return false;
        }

        if (security == 3) {

            if (password.length() > 0 && contains(password, NUMBER) && !password.equals(lowerPass) && !password.equals(upperPass))
                return true;
            return false;

        }

        if (security == 4) {

            if (password.length() > 5 && contains(password, NUMBER) && contains(password, SPECIAL) && !password.equals(lowerPass) && !password.equals(upperPass))
                return true;
            return false;
        }


        return false;
    }


    public  boolean contains(String pwd, char[] value) {
        int i = 0;
        boolean success = false;
        while (i < value.length && !success) {
            if (pwd.indexOf("" + value[i]) != -1) {
                success = true;
            }
            i++;
        }
        return success;
    }

    public  String getPasswordStrength(String password) {

        int points = 0;
        String lowerPass = password.toLowerCase();
        String upperPass = password.toUpperCase();


        if (password.length() == 0) {
            return "Invalid";
        }

        if (password.length() < 6) {

            return "Weakest";
        }
        if (password.length() > 5) {

            points++;

        }

        if (!password.equals(lowerPass) && !password.equals(upperPass)) {
            // if contains upper or lower letter
            points++;
        }

        if (contains(password, SPECIAL)) {
            // if it contains special character
            points++;
        }
        if (contains(password, NUMBER)) {
            // if it contains Number
            points++;
        }


        if (points == 1) {
            return "Weak";
        }
        if (points == 2) {
            return "Medium";
        }
        if (points == 3) {
            return "Good";
        }

        return "Strong";

    }

}
