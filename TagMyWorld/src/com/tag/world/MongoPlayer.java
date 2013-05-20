package com.tag.world;

import java.net.UnknownHostException;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoPlayer {

	public static void main(String[] args) {
		Mongo m;
		try {
			m = new Mongo("localhost", 27017);
			DB db = m.getDB("mydb");
			DBCollection playerCollection = db.getCollection("player");

			playerCollection.ensureIndex("id");

			BasicDBObject doc = new BasicDBObject();

			doc.put("id", new Random().nextInt());
			doc.put("name", "MongoDB");
			doc.put("type", "database");
			doc.put("count", 1);

			BasicDBObject info = new BasicDBObject();

			info.put("x", 203);
			info.put("y", 102);
			info.put("facebook_id", new Random().nextLong());

			doc.put("info", info);

			playerCollection.insert(doc);

			BasicDBObject ref = new BasicDBObject();
			
			ref.put("facebook_id", "{$in}");
			DBCursor p = playerCollection.find(ref);

			while (p.hasNext()) {
				DBObject o = p.next();
				System.out.println(o);
			}

			
			BasicDBObject query = new BasicDBObject();
			query.put("make", "Ford");
			String models[] = new String[]{"Galaxy", "Mustang", "Meteor"};
			query.put("model", new BasicDBObject("$in", models));
			//DBCursor resultsCursor = carsCollection.find(query);

			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
