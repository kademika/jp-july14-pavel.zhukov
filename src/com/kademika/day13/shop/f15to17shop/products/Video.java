package com.kademika.day13.shop.f15to17shop.products;

import com.kademika.day13.shop.f15to17shop.Department;

public class Video extends Product {
	private VideoType vt;
	private String screentype;
	private double diagonal;
	private String format;
	private int powerLamp;
	private String resolution;

	public Video(String id, String model, VideoType vt, String producer,
			String character, String color, String screentype, double diagonal,
			String format, String resolution, int number, double price) {
		super(id, model, producer, character, color, number, price);
		dept = Department.VIDEO;
		this.vt = vt;
		this.screentype = screentype;
		this.diagonal = diagonal;
		this.format = format;
		this.powerLamp = 0;
		this.resolution = resolution;
	}

	public Video(String id, String model, VideoType vt, String producer,
			String character, String color, String format, int number,
			double price) {
		super(id, model, producer, character, color, number, price);
		dept = Department.VIDEO;
		this.vt = vt;
		this.screentype = "none";
		this.diagonal = 0;
		this.format = format;
		this.powerLamp = 0;
		this.resolution = "none";
	}

	public Video(String id, String model, VideoType vt, String producer,
			String character, String color, String format, String resolution,
			int powerLamp, int number, double price) {
		super(id, model, producer, character, color, number, price);
		dept = Department.VIDEO;
		this.vt = vt;
		this.screentype = "none";
		this.diagonal = 0;
		this.format = format;
		this.powerLamp = powerLamp;
		this.resolution = resolution;
	}

	@Override
	public String getInfo() {
		if (vt == VideoType.TV) {
			return id + " TV: " + model + ", made in " + producer
					+ ", basic characteristics: " + character + ", \ncolor: "
					+ color + ", screentype: " + screentype + ", diagonal: "
					+ diagonal + ", resolution: " + resolution + ", \nformat: "
					+ format + ", department: " + dept + "\nnumber: " + number
					+ " \nprice: " + price + " rub";
		} else if (vt == VideoType.PLAYER) {
			return id + " Player: " + model + ", made in "
					+ producer + ", basic characteristics: " + character
					+ ", \ncolor: " + color + ", format: " + format
					+ ", department: " + dept + "\nnumber: " + number
					+ " \nprice: " + price + " rub";
		} else {
			return id + " Projector: " + model + ", made in "
					+ producer + ", basic characteristics: " + character
					+ ", \ncolor: " + color + ", resolution: " + resolution
					+ ", \nformat: " + format + ", department: " + dept
					+ "\nnumber: " + number + " \nprice: " + price + " rub";
		}
	}

	public VideoType getVt() {
		return vt;
	}

	public String getScreentype() {
		return screentype;
	}

	public double getDiagonal() {
		return diagonal;
	}

	public String getFormat() {
		return format;
	}

	public int getPowerLamp() {
		return powerLamp;
	}

	public String getResolution() {
		return resolution;
	}

}
