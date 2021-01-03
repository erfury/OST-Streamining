package com.visualizer.main.model;

import org.apache.kafka.clients.consumer.StickyAssignor;
import org.springframework.data.cassandra.core.mapping.Column;

import java.time.LocalDateTime;

public class Invoice_history {

    private String id;

    private String invoiceId;
    private String partyId;
    private String currency;
    private String taxExclusiveAmount;
    private String taxInclusiveAmount;
    private String payableAmount;
    private String issueDate;
    private String taxPointDate;
    private String dueDate;
    private String issueYear;
    private String totalInvoice;

    public Invoice_history() {
    }

    public Invoice_history(String id, String invoiceId, String partyId, String currency, String taxExclusiveAmount, String taxInclusiveAmount, String issueDate, String taxPointDate, String dueDate, String issueYear, String totalInvoice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.partyId = partyId;
        this.currency = currency;
        this.taxExclusiveAmount = taxExclusiveAmount;
        this.taxInclusiveAmount = taxInclusiveAmount;
        this.payableAmount = payableAmount;
        this.issueDate = issueDate;
        this.taxPointDate = taxPointDate;
        this.dueDate = dueDate;
        this.issueYear = issueYear;
        this.totalInvoice = totalInvoice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTaxExclusiveAmount() {
        return taxExclusiveAmount;
    }

    public void setTaxExclusiveAmount(String taxExclusiveAmount) {
        this.taxExclusiveAmount = taxExclusiveAmount;
    }

    public String getTaxInclusiveAmount() {
        return taxInclusiveAmount;
    }

    public void setTaxInclusiveAmount(String taxInclusiveAmount) {
        this.taxInclusiveAmount = taxInclusiveAmount;
    }

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getTaxPointDate() {
        return taxPointDate;
    }

    public void setTaxPointDate(String taxPointDate) {
        this.taxPointDate = taxPointDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getIssueYear() {return issueYear; }

    public void setIssueYear(String issueYear) {this.issueYear = issueYear; }

    public String getTotalInvoice(){return totalInvoice; }

    public void setTotalInvoice(String totalInvoice) {this.totalInvoice = totalInvoice; }

    @Override
    public String toString() {
        return id+","+invoiceId+","+partyId+","+currency+","+taxExclusiveAmount+","+taxInclusiveAmount+","+payableAmount+","+issueDate+","+taxPointDate+","+dueDate+","+issueYear+","+totalInvoice;
    }
}
