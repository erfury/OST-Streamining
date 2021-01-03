package com.visualizer.main.model;

public class LoginHistoryModel {
    private String id;

    private String partyId;
    private String dateTime;
    public LoginHistoryModel() {
    }

    public LoginHistoryModel(String id, String partyId, String dateTime) {
        this.id = id;
        this.partyId = partyId;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return id+","+partyId+","+dateTime;
    }
}
