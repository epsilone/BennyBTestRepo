package com.tag.world;

import com.tag.world.model.factory.MongoPlayerFactory.Fields;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.err.println(Fields.CASH.getValue(3));
		System.err.println(Fields.CASH.getValue("3"));
		System.err.println(Fields.CASH.getValue("op"));

	}

}
