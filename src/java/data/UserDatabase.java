/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author Nguyen Viet Bach
 */
public class UserDatabase {

    private final SimpleDateFormat sdf;
    //private final HashSet<User> users;

    public UserDatabase() {
        sdf = new SimpleDateFormat("dd.MM.YYYY HH:mm:ss");
        //users = new HashSet<>();
    }

    public void addUser(String ip) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new FileWriter("usersConnections.txt", true)))) {
            Calendar calendar = Calendar.getInstance();
            out.println(ip + ";" + sdf.format(calendar.getTime())
                    + " " + calendar.getTimeZone().getDisplayName(false, TimeZone.SHORT) + ";");
        } catch (IOException e) {
            System.out.println("[E UDB] " + e.getMessage());
        }
        //User u = new User(ip);
        //u.addDateString(sdf.format(cal.getTime()));
        //users.add(u);
    }

    /*public void printDB() {
     for (User us : users) {
     System.out.println(us.getIp());
     for (int i = 0; i < us.getDateList().size(); i++) {
     System.out.println("[" + us.getDateList().get(i) + "]");
     }
     }
     System.out.println("***");
     }*/
    /*public static void main(String[] args) {
     Calendar cal = Calendar.getInstance();

     String name=cal.getTimeZone().getDisplayName();
     System.out.println("Current Time Zone:" + name );
     TimeZone tz = TimeZone.getTimeZone("GMT");
     cal.setTimeZone(tz);
     System.out.println(cal.getTimeZone().getDisplayName());
     System.out.println(cal.getTime());

     }*/
}
