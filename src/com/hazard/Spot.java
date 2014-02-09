package com.hazard;

import java.util.Date;

public class Spot {
    private String spotName;
    private int clientSpotDuration;
    private int partnersSpotDuration;
    private int spotShare;
    private double markup;
    private Date startDate;
    private Date endDate;

    public Spot(String spotName, int clientSpotDuration, int partnersSpotDuration, int spotShare, Date startDate,
	    Date endDate) {
	this.spotName = spotName;
	this.clientSpotDuration = clientSpotDuration;
	this.partnersSpotDuration = partnersSpotDuration;
	this.spotShare = spotShare;
	this.startDate = startDate;
	this.endDate = endDate;
    }

    public Date getStartDate() {
	return startDate;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public String getSpotName() {
	return spotName;
    }

    public void setSpotName(String spotName) {
	this.spotName = spotName;
    }

    public int getClientSpotDuration() {
	return clientSpotDuration;
    }

    public void setClientSpotDuration(int clientSpotDuration) {
	this.clientSpotDuration = clientSpotDuration;
    }

    public int getPartnersSpotDuration() {
	return partnersSpotDuration;
    }

    public void setPartnersSpotDuration(int partnersSpotDuration) {
	this.partnersSpotDuration = partnersSpotDuration;
    }

    public int getSpotShare() {
	return spotShare;
    }

    public void setSpotShare(int spotShare) {
	this.spotShare = spotShare;
    }

    public double getMarkup() {
	return markup;
    }

    public void setMarkup(double markup) {
	this.markup = markup;
    }
}
