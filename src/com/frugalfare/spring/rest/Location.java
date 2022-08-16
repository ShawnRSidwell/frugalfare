package com.frugalfare.spring.rest;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
	private long place_id;
	private String licence;
	private String osm_type;
	private long osm_id;
	private ArrayList<String> boundingbox;
	private String lat;
	private String lon;

	@JsonProperty("display_name")
	private String displayName;
	@JsonProperty("class")
	private String className;
	private String type;
	@JsonIgnore
	private double importance;
	@JsonIgnore
	private String icon;
	@JsonIgnore
	private Address address;

	public Location() {
	}

	public long getPlace_id() {
		return place_id;
	}

	public void setPlace_id(long place_id) {
		this.place_id = place_id;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public String getOsm_type() {
		return osm_type;
	}

	public void setOsm_type(String osm_type) {
		this.osm_type = osm_type;
	}

	public long getOsm_id() {
		return osm_id;
	}

	public void setOsm_id(long osm_id) {
		this.osm_id = osm_id;
	}

	public ArrayList<String> getBoundingbox() {
		return boundingbox;
	}

	public void setBoundingbox(ArrayList<String> boundingbox) {
		this.boundingbox = boundingbox;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getImportance() {
		return importance;
	}

	public void setImportance(double importance) {
		this.importance = importance;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Location [place_id=" + place_id + ", licence=" + licence + ", osm_type=" + osm_type + ", osm_id="
				+ osm_id + ", boundingbox=" + boundingbox + ", lat=" + lat + ", lon=" + lon + ", displayName="
				+ displayName + ", className=" + className + ", type=" + type + ", importance=" + importance + ", icon="
				+ icon + ", address=" + address + "]";
	}

}