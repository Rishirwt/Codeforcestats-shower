package com.example.dell.codeforcestats;

public class userinfo {
    String handle;
    String firstname;
    String current;
    String country;
    String image;
    String rank;
    String maxrating;

    public userinfo(String handle,String firstname, String current, String country, String image, String rank, String maxrating) {
        this.handle=handle;
        this.firstname = firstname;

        this.current = current;
        this.country = country;
        this.image = image;
        this.rank = rank;
        this.maxrating = maxrating;
    }

    public String getFirstname() {
        return firstname;
    }



    public String getCountry() {
        return country;
    }

    public String getImage() {
        return image;
    }

    public String getRank() {
        return rank;
    }

    public String getMaxrating() {
        return maxrating;
    }

    public String getHandle() {
        return handle;
    }

    public String getCurrent() {
        return current;
    }
}
