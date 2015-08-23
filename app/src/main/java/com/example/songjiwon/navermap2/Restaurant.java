package com.example.songjiwon.navermap2;

public class Restaurant
{
    private String title;
    private String link;
    private String category;
    private String description;
    private String telephone;
    private String adress;
    private String roadadress;
    private double mapx;
    private double mapy;

    private String[] info;

    public Restaurant()
    {
        title = null;
        link = null;
        category = null;
        description = null;
        telephone = null;
        adress = null;
        roadadress = null;
        //mapx = ;
        //mapy = ;

        info = new String[10];

        info[0] = title;
        info[1] = link;
        info[2] = category;
        info[3] = description;
        info[4] = telephone;
        info[5] = adress;
        info[6] = roadadress;
        info[7] = ""+mapx;
        info[8] = ""+mapy;
        info[9] = "";

    }


    public String[] getInfo() {
        return info;
    }

//    public void setInfo(String[] info) {
//        this.info = info;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        info[9]+="0";
        this.title = title;
    }



    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        info[9]+="1";
        this.link = link;
    }




    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        info[9]+="2";
        this.category = category;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        info[9]+="3";
        this.description = description;
    }




    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        info[9]+="4";
        this.telephone = telephone;
    }




    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        info[9]+="5";
        this.adress = adress;
    }




    public String getRoadadress() {
        return roadadress;
    }

    public void setRoadadress(String roadadress) {
        info[9]+="6";
        this.roadadress = roadadress;
    }




    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        info[9]+="7";
        this.mapx = mapx;
    }




    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        info[9]+="8";
        this.mapy = mapy;
    }


}
