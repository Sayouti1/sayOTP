/****************************************************
 @Created_by :  ABDELAZIZ ES SAYOUTI aka @Sayouti1
 @mail:         aes-sayo@student.1337.ma
 @Updated_at:   25-01-2025 by : aes-sayo
 ****************************************************/

package com.sayouti.sayotp.Controller;

import com.warrenstrange.googleauth.GoogleAuthenticator;

public class SayOTP
{
    public static String getOTP(String key) throws Exception
    {
        GoogleAuthenticator gauth = new GoogleAuthenticator();
        String val = "ERROR";
        try {
            val = String.valueOf(gauth.getTotpPassword(key));
            if (val.length() < 6)
                val = "0" + val;
        } catch (Exception e)
        {
            System.out.println("getTotpPassword error" + e.getMessage());
            return "ERROR : Key Not Valid";
        }
        return (val);
    }
}
