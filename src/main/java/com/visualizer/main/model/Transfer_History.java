package com.visualizer.main.model;

public class Transfer_History {

    private String id;

    private String transferId;
    private String partyId;
    private String currency;
    private String amount;
    private String valueDate;
    private String counts;

public Transfer_History(){

}

    public Transfer_History(String id, String transferId, String partyId, String currency, String amount, String valueDate, String counts) {
        this.id = id;
        this.transferId = transferId;
        this.partyId = partyId;
        this.currency = currency;
        this.amount = amount;
        this.valueDate = valueDate;
        this.counts = counts;

    }




    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTransferId() {
        return transferId;
    }
    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getPartyId(){return partyId;}
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return id+","+transferId+","+partyId+","+currency+","+amount+","+valueDate+","+counts;
    }
}
