package cn.itcast.s.Class;
//记录列车订单信息类
public class TrainOrder {
    private String id;
    private String account;
    private String password;
    private String dtime;
    private String atime;
    private String start;
    private String end;
    private String traino;
    private String time;
    private String price;
    private int F;

    public void setId(String id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setTraino(String traino) {
        this.traino = traino;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getDtime() {
        return dtime;
    }

    public String getAtime() {
        return atime;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getTraino() {
        return traino;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }

    public void setF(int f) {
        F = f;
    }

    public int getF() {
        return F;
    }
}
