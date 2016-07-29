///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package data;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
///**
// *
// * @author Nguyen Viet Bach
// */
//public class User implements Comparable<User> {
//
//    private final String ip;
//    private final List<String> dateList;
//
//    public User(String ip) {
//        this.ip = ip;
//        this.dateList = new ArrayList<>();
//    }
//
//    public void addDateString(String d) {
//        dateList.add(d);
//    }
//
//    public String getIp() {
//        return ip;
//    }
//
//    public List<String> getDateList() {
//        return dateList;
//    }
//
//    @Override
//    public int compareTo(User other) {
//        return this.getIp().compareTo(other.getIp());
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 53 * hash + Objects.hashCode(this.ip);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final User other = (User) obj;
//        if (!Objects.equals(this.ip, other.ip)) {
//            return false;
//        }
//        return true;
//    }
//}
