package com.tag.world.model.factory;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.WriteResult;

public class MongoPlayerFactory extends MongoFactory {

	private final static String COLLECTION_NAME = "player";

	public static enum Fields {
		NICKNAME(String.class), FIRST_NAME(String.class), LAST_NAME(
				String.class), FACEBOOK_ID(Long.class), INSTALL_SOUCE(
				String.class), INSTALL_DATE(Date.class), FLAGS(Integer.class), CASH(
				Integer.class), XP(Integer.class), LAST_COLLECTED_FLAG(
				Date.class);

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

	public MongoPlayerFactory() {
		if (playerCollection == null) {
			init();
		}
	}

	protected static DBCollection playerCollection = null;

	synchronized private void init() {
		if (playerCollection == null) {
			try {
				Mongo m = new Mongo(DB_ADDRESS, DB_PORT);
				DB db = m.getDB(DB_NAME);
				playerCollection = db.getCollection(COLLECTION_NAME);
				playerCollection.ensureIndex(Fields.NICKNAME.toString());
				playerCollection.ensureIndex(Fields.FACEBOOK_ID.toString());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

	public DBObject getPlayer(Fields field, Object player_id) {
		BasicDBObject ref = new BasicDBObject();

		ref.put(field.toString(), field.getValue(player_id));
		DBCursor p = playerCollection.find(ref);

		DBObject rtrn = null;
		try {
			if (p.hasNext()) {
				rtrn = p.next();
			}
		} finally {
			p.close();
		}

		return rtrn;
	}

	public boolean containsPlayer(Fields field, Object player_id) {
		BasicDBObject ref = new BasicDBObject();

		ref.put(field.toString(), field.getValue(player_id));
		DBCursor p = playerCollection.find(ref);
		boolean rtrn = p.count() != 0;
		p.close();
		return rtrn;
	}

	public String serializePlayer(DBObject player) {
		Map map = player.toMap();
		for (String privateField : Fields.getPrivateFields()) {
			map.remove(privateField);
		}
		return new Gson().toJson(map);
	}

	public DBObject createPlayer(String player_id, String source) {
		BasicDBObject ref = new BasicDBObject();
		ref.put(Fields.NICKNAME.toString(), player_id);
		ref.put(Fields.CASH.toString(), 100);
		ref.put(Fields.XP.toString(), 0);
		ref.put(Fields.FLAGS.toString(), 0);
		ref.put(Fields.INSTALL_DATE.toString(), new Date());
		ref.put(Fields.INSTALL_SOUCE.toString(), source == null ? "unknown"
				: source);
		WriteResult rtrn = playerCollection.insert(ref);
		if (rtrn.getError() != null) {
			BasicDBObject rtrn0 = new BasicDBObject();
			rtrn0.put("error", rtrn.getError());
			return rtrn0;
		}
		return ref;
	}

	// public List<Player> getPlayers(long... player_ids) {
	// return getPlayers(ID, player_ids);
	// }
	//
	// public List<Player> getPlayers(String key, long... player_ids) {
	// List<Player> rtrn = new ArrayList<Player>();
	// BasicDBObject query = new BasicDBObject();
	// query.put(key, new BasicDBObject("$in", player_ids));
	// DBCursor resultsCursor = playerCollection.find(query);
	// while (resultsCursor.hasNext()) {
	// DBObject o = resultsCursor.next();
	// rtrn.add(createFromDb((String) o.get(ID), o));
	// }
	// return rtrn;
	// }
	//
	// public Player createFromDb(Object player_id, DBObject o) {
	// Player player = new Player(Long.valueOf(player_id.toString()));
	// if (o.get(FIRST_NAME) != null) {
	// player.setFirstName((String) o.get(FIRST_NAME));
	// }
	// if (o.get(LAST_NAME) != null) {
	// player.setLastName((String) o.get(LAST_NAME));
	// }
	// if (o.get(INSTALL_SOURCE) != null) {
	// player.setInstallSource((String) o.get(INSTALL_SOURCE));
	// }
	// if (o.get(INSTALL_DATE) != null) {
	// player.setInstallDate((Date) o.get(INSTALL_DATE));
	// }
	// if (o.get(FLAGS) != null) {
	// player.setFlags((int) o.get(FLAGS));
	// }
	// if (o.get(CASH) != null) {
	// player.setCash((int) o.get(CASH));
	// }
	// if (o.get(XP) != null) {
	// player.setXp((int) o.get(XP));
	// }
	// if (o.get(LAST_COLLECTED_FLAG) != null) {
	// player.setLastCollectedFlag((Date) o.get(LAST_COLLECTED_FLAG));
	// }
	// return player;
	// }
}
