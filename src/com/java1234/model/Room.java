package com.java1234.model;

/**
 * 会议室实体
 * @author zhang
 *
 */
public class Room {

	private int id;
	private int size;
	private String name;
	private String location;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Room(int size, String name, String location) {
		super();
		this.size = size;
		this.name = name;
		this.location = location;
	}

	public Room(int id, int size, String name, String location) {
		super();
		this.id = id;
		this.size = size;
		this.name = name;
		this.location = location;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
