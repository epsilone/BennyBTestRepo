package com.tag.world.model;

import java.util.Date;

import com.tag.world.model.factory.MongoPlayerFactory.Fields;

public class Player {

	public Player(long id) {
		super();
		this.id = id;
	}

	public Player(long id, String firstName, String lastName,
			String installSource, Date installDate, int flags, int cash,
			int xp, Date lastCollectedFlag) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.installSource = installSource;
		this.installDate = installDate;
		this.flags = flags;
		this.cash = cash;
		this.xp = xp;
		this.lastCollectedFlag = lastCollectedFlag;
	}

	private long id;

	private String firstName;

	private String lastName;

	private String installSource;

	private Date installDate;

	private int flags;

	private int cash;

	private int xp;

	private Date lastCollectedFlag;

	public Date getLastCollectedFlag() {
		return lastCollectedFlag;
	}

	public void setLastCollectedFlag(Date lastCollectedFlag) {
		this.lastCollectedFlag = lastCollectedFlag;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setInstallSource(String installSource) {
		this.installSource = installSource;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getInstallSource() {
		return installSource;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public int getFlags() {
		return flags;
	}

	public int getCash() {
		return cash;
	}

	public int getXp() {
		return xp;
	}

	public long getId() {
		return id;
	}
	
	public static void main(String[] args) {
		System.err.println(Fields.CASH.getValue(3));
		System.err.println(Fields.CASH.getValue("3"));
	}
	
}
