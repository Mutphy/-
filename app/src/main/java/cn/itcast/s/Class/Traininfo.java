package cn.itcast.s.Class;

//获取到的列车信息类
public class Traininfo {
    private String departuretime; //出发时间
    private String arrivaltime; //到达时间
    private String type;     //类型
    private String station; //出发站
    private String endstation;  //到达站
    private String trainno; //车次
    private String costime; //花费时间
    private String pricesw; //商务座票价
    private String privetd; //特等座票价
    private String pricegr1;    //高级软卧上票价
    private String pricegr2;    //高级软卧下票价
    private String pricerw1;    //软卧上票价
    private String pricerw2;    //软卧下票价
    private String priceyw1;    //硬卧上票价
    private String priceyw2;    //硬卧中票价
    private String priceyw3;    //硬卧下票价
    private String priceyd; //一等座票价
    private String priceed; //二等座票价
    private String pricerz; //软座座票价
    private String priceyz; //硬座票价

    public Traininfo(String departuretime, String arrivaltime, String station, String endstation,
                     String trainno,String costime, String pricesw, String privetd, String pricegr1,
                     String pricegr2, String pricerw1, String pricerw2, String priceyw1, String priceyw2,
                     String priceyw3, String priceyd, String priceed, String pricerz, String priceyz,String type) {
        this.departuretime = departuretime;
        this.arrivaltime = arrivaltime;
        this.station = station;
        this.endstation = endstation;
        this.trainno = trainno;
        this.costime = costime;
        this.pricesw = pricesw;
        this.privetd = privetd;
        this.pricegr1 = pricegr1;
        this.pricegr2 = pricegr2;
        this.pricerw1 = pricerw1;
        this.pricerw2 = pricerw2;
        this.priceyw1 = priceyw1;
        this.priceyw2 = priceyw2;
        this.priceyw3 = priceyw3;
        this.priceyd = priceyd;
        this.priceed = priceed;
        this.pricerz = pricerz;
        this.priceyz = priceyz;
        this.type = type;
    }

    public void setArrivaltime(String arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setEndstation(String endstation) {
        this.endstation = endstation;
    }

    public void setTrainno(String trainno) { this.trainno = trainno; }

    public void setCostime(String costime) { this.costime = costime; }

    public void setPricesw(String pricesw) { this.pricesw = pricesw; }

    public void setPrivetd(String privetd) {
        this.privetd = privetd;
    }

    public void setPricegr1(String pricegr1) {
        this.pricegr1 = pricegr1;
    }

    public void setPricegr2(String pricegr2) {
        this.pricegr2 = pricegr2;
    }

    public void setPricerw1(String pricerw1) { this.pricerw1 = pricerw1; }

    public void setPricerw2(String pricerw2) {
        this.pricerw2 = pricerw2;
    }

    public void setPriceyw1(String priceyw1) {
        this.priceyw1 = priceyw1;
    }

    public String getType() { return type; }

    public void setPriceyw2(String priceyw2) {
        this.priceyw2 = priceyw2;
    }

    public String getDeparturetime() { return departuretime; }

    public void setPriceyw3(String priceyw3) {
        this.priceyw3 = priceyw3;
    }

    public void setPriceyd(String priceyd) { this.priceyd = priceyd; }

    public void setDeparturetime(String departuretime) {this.departuretime = departuretime; }

    public void setType(String type) { this.type = type; }

    public void setPriceed(String priceed) {
        this.priceed = priceed;
    }

    public void setPricerz(String pricerz) {
        this.pricerz = pricerz;
    }

    public void setPriceyz(String priceyz) {
        this.priceyz = priceyz;
    }


    public String getArrivaltime() {
        return arrivaltime;
    }

    public String getStation() {
        return station;
    }

    public String getEndstation() {
        return endstation;
    }

    public String getTrainno() {
        return trainno;
    }

    public String getCostime() {
        return costime;
    }

    public String getPricesw() {
        return pricesw;
    }

    public String getPrivetd() {
        return privetd;
    }

    public String getPricegr1() {
        return pricegr1;
    }

    public String getPricegr2() {
        return pricegr2;
    }

    public String getPricerw1() {
        return pricerw1;
    }

    public String getPricerw2() {
        return pricerw2;
    }

    public String getPriceyw1() {
        return priceyw1;
    }

    public String getPriceyw2() {
        return priceyw2;
    }

    public String getPriceyw3() {
        return priceyw3;
    }

    public String getPriceyd() {
        return priceyd;
    }

    public String getPriceed() {
        return priceed;
    }

    public String getPricerz() {
        return pricerz;
    }

    public String getPriceyz() {
        return priceyz;
    }


}
