package akanshaapp.com.geu.akanshaapp.akanshaapp;

/**
 * Created by GB on 11/25/2015.
 */
public class TrainBetweenInfo
{
    String name;
    String number;
    String arrivaltime;
    String departuretime;
    String from;
    String to;

    public TrainBetweenInfo(String name, String number, String arrivaltime, String departuretime, String from, String to) {
        this.name = name;
        this.number = number;
        this.arrivaltime = arrivaltime;
        this.departuretime = departuretime;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
