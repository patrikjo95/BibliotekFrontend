package com.example.bibliotekfrontend;

public class RoomCalender {

    String Date;
    String firstTime;
    String secondTime;
    String thirdTime;
    String fourthTime;
    String fifthTime;
    String sixTime;
    String sevenTime;
    String eightTime;
    String nineTime;

    public RoomCalender(String date, String firstTime, String secondTime, String thirdTime, String fourthTime, String fifthTime, String sixTime, String sevenTime, String eightTime, String nineTime) {
        Date = date;
        this.firstTime = firstTime;
        this.secondTime = secondTime;
        this.thirdTime = thirdTime;
        this.fourthTime = fourthTime;
        this.fifthTime = fifthTime;
        this.sixTime = sixTime;
        this.sevenTime = sevenTime;
        this.eightTime = eightTime;
        this.nineTime = nineTime;
    }





    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getSecondTime() {
        return secondTime;
    }

    public void setSecondTime(String secondTime) {
        this.secondTime = secondTime;
    }

    public String getThirdTime() {
        return thirdTime;
    }

    public void setThirdTime(String thirdTime) {
        this.thirdTime = thirdTime;
    }

    public String getFourthTime() {
        return fourthTime;
    }

    public void setFourthTime(String fourthTime) {
        this.fourthTime = fourthTime;
    }

    public String getFifthTime() {
        return fifthTime;
    }

    public void setFifthTime(String fifthTime) {
        this.fifthTime = fifthTime;
    }

    public String getSixTime() {
        return sixTime;
    }

    public void setSixTime(String sixTime) {
        this.sixTime = sixTime;
    }

    public String getSevenTime() {
        return sevenTime;
    }

    public void setSevenTime(String sevenTime) {
        this.sevenTime = sevenTime;
    }

    public String getEightTime() {
        return eightTime;
    }

    public void setEightTime(String eightTime) {
        this.eightTime = eightTime;
    }

    public String getNineTime() {
        return nineTime;
    }

    public void setNineTime(String nineTime) {
        this.nineTime = nineTime;
    }

    @Override
    public String toString() {
        return "RoomCalender{" +
                ", Date='" + Date + '\'' +
                ", firstTime='" + firstTime + '\'' +
                ", secondTime='" + secondTime + '\'' +
                ", thirdTime='" + thirdTime + '\'' +
                ", fourthTime='" + fourthTime + '\'' +
                ", fifthTime='" + fifthTime + '\'' +
                ", sixTime='" + sixTime + '\'' +
                ", sevenTime='" + sevenTime + '\'' +
                ", eightTime='" + eightTime + '\'' +
                ", nineTime='" + nineTime + '\'' +
                '}';
    }
}
