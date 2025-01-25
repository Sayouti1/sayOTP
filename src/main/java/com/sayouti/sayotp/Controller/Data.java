/****************************************************
 @Created_by :  ABDELAZIZ ES SAYOUTI aka @Sayouti1
 @mail:         aes-sayo@student.1337.ma
 @Updated_at:   25-01-2025 by : aes-sayo
 ****************************************************/

package com.sayouti.sayotp.Controller;

import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Data
{
    private static Hashtable<String, String> my_list = new Hashtable<>();

    public static Set<String> getListKeys()
    {
        return my_list.keySet();
    }
    public static void loadData() throws Exception
    {
        try(FileInputStream fis = new FileInputStream("data.sayOTP"))
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String  line;
            while ((line = br.readLine()) != null)
            {
                my_list.put(line.split(":")[0].trim().toLowerCase(), line.split(":")[1].trim());
            }
        }
    }

    public static Map<String, String> getList() throws Exception
    {
        Map<String, String> list = new HashMap<>();
        for (String key : my_list.keySet())
            list.put(key, SayOTP.getOTP(my_list.get(key)));
        return (list);
    }

    public static void saveData()
    {
        try(FileOutputStream fos = new FileOutputStream("data.sayOTP");
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos)))
        {
            my_list.forEach((k,v)->{
                try
                {
                    bw.write(k + ":" + v + "\n");
                } catch (IOException e)
                {
                    System.out.println("Error saving data");
                }
            });
        } catch (Exception e)
        {
            System.out.println("Error saving data2");
        }
    }

    public static void addData(String key, String value)
    {
        if (my_list.containsKey(key))
        {
            System.out.println(key + " already exists !!");
            return;
        }
        my_list.put(key.toLowerCase(), value);
        saveData();
    }

    public static void deleteAccount()
    {
        //TO BE ADDED LATER
    }

}
