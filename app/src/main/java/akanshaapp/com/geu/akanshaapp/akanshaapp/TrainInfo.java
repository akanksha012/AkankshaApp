package akanshaapp.com.geu.akanshaapp.akanshaapp;

import java.util.List;

/**
 * Created by GB on 11/25/2015.
 */
public class TrainInfo {


    Days  days;
    List<Route> route;
    Classes classes;

    public void setDays(Days days) {
        this.days = days;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public void setClasses(Classes classes) {
        this.classes = classes;
    }

    public Days getDays() {
        return days;
    }

    public List<Route> getRoute() {
        return route;
    }

    public Classes getClasses() {
        return classes;
    }

    public TrainInfo(Days days, List<Route> route, Classes classes) {
        this.days = days;
        this.route = route;

        this.classes = classes;
    }
}
class Route

{

    String no;
    String distance;
    String day;
    String halt;
    String route;
    String code;
    String fullname;
    String lat;
    String  lng;
    String state;
    String scharr;
    String departure;


    public Route(String no, String distance, String day, String halt, String route, String code, String fullname, String lat, String lng, String state, String scharr, String departure) {
        this.no = no;
        this.distance = distance;
        this.day = day;
        this.halt = halt;
        this.route = route;
        this.code = code;
        this.fullname = fullname;
        this.lat = lat;
        this.lng = lng;
        this.state = state;
        this.scharr = scharr;
        this.departure = departure;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHalt() {
        return halt;
    }

    public void setHalt(String halt) {
        this.halt = halt;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScharr() {
        return scharr;
    }

    public void setScharr(String scharr) {
        this.scharr = scharr;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}

class Days
{
    String number="N";
    String name="N";
    String SUN="N";
    String MON="N";
   String TUE="N";
   String WED="N";
    String THU="N";
    String FRI="N";
  String SAT="N";



}

class Classes
{
    String A3;
    String CC;
    String SL;
    String S2;
    String E3;
    String A2;
    String A1;
    String FC;

    public String getA3() {
        return A3;
    }

    public void setA3(String a3) {
        A3 = a3;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getS2() {
        return S2;
    }

    public void setS2(String s2) {
        S2 = s2;
    }

    public String getE3() {
        return E3;
    }

    public void setE3(String e3) {
        E3 = e3;
    }

    public String getA2() {
        return A2;
    }

    public void setA2(String a2) {
        A2 = a2;
    }

    public String getA1() {
        return A1;
    }

    public void setA1(String a1) {
        A1 = a1;
    }

    public String getFC() {
        return FC;
    }

    public void setFC(String FC) {
        this.FC = FC;
    }

    public Classes(String a3, String CC, String SL, String s2, String e3, String a2, String a1, String FC) {
        A3 = a3;
        this.CC = CC;
        this.SL = SL;
        S2 = s2;
        E3 = e3;
        A2 = a2;
        A1 = a1;
        this.FC = FC;
    }
}