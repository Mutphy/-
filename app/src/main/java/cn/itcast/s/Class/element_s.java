package cn.itcast.s.Class;
//用来存放首页输入的信息
public class element_s {
    private String start;
    private String end;
    private String date;
    private int isgo;

    public element_s() {
        this.start =null;
        this.end = null;
        this.date = null;
        this.isgo = 0;
    }

    public String getStart() {
        return start;
    }

    public element_s(String start, String end, String date, int isgo) {
        this.start = start;
        this.end = end;
        this.date = date;
        this.isgo = isgo;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsgo() {
        return isgo;
    }

    public void setIsgo(int isgo) {
        this.isgo = isgo;
    }
}
