package com.example.dell.codeforcestats;

public class Step implements java.io.Serializable {
    String contestname;
    String rank;
    String oldrank;
    String newrank;

    public String getContestname() {
        return contestname;
    }

    public String getRank() {
        return rank;
    }

    public String getOldrank() {
        return oldrank;
    }

    public String getNewrank() {
        return newrank;
    }

    public Step(String contestname, String rank, String oldrank, String newrank) {

        this.contestname = contestname;
        this.rank = rank;
        this.oldrank = oldrank;
        this.newrank = newrank;
    }
}

