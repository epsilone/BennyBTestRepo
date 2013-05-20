package com.tag.world.model.factory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MongoFlagFactory extends MongoFactory {

	protected static DBCollection flagCollection = null;
	private final static String COLLECTION_NAME = "flag";

	public MongoFlagFactory() {
		if (flagCollection == null) {
			init();
		}
	}

	public static enum Fields {
		LAT(Float.class), LONG(Float.class), OWNERS(Integer.class), NAME(
				String.class), BONUS(String.class);

		private static final List<String> privateFields = new ArrayList<>();
		static {
			// privateFields.add(INSTALL_SOUCE.toString());
			// privateFields.add(INSTALL_DATE.toString());
			privateFields.add("_id");
		}

		private Class associatedClass;

		public Class getAssociatedClass() {
			return associatedClass;
		}

		private Fields(Class c) {
			this.associatedClass = c;
		}

		public static Fields getField(String field) {
			return Fields.valueOf(field.toUpperCase());
		}

		public static List<String> getPrivateFields() {
			return privateFields;
		}

		public <T> T getValue(Object o) {
			try {
				if (associatedClass == Integer.class) {
					return (T) Integer.valueOf(o.toString());
				} else if (associatedClass == Long.class) {
					return (T) Long.valueOf(o.toString());
				} else if (associatedClass == String.class) {
					return (T) o.toString();
				} else
					return null;
			} catch (Exception e) {
				return null;
			}
		}
	}

	synchronized private void init() {
		if (flagCollection == null) {
			try {
				Mongo m = new Mongo(DB_ADDRESS, DB_PORT);
				DB db = m.getDB(DB_NAME);
				flagCollection = db.getCollection(COLLECTION_NAME);
				BasicDBObject index = new BasicDBObject();
				index.put(Fields.LAT.toString(), 0);
				index.put(Fields.LONG.toString(), 1);
				flagCollection.ensureIndex(index);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
}
