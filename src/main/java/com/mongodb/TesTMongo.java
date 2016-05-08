package com.mongodb;

/**
 * Created by baljit on 8/05/2016.
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//http://www.thejavageek.com/2015/08/24/retrieve-array-from-mongodb-using-java/
//https://www.google.co.nz/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#safe=off&q=java%20mongodb%20document%20read%20array
//http://www.javahotchocolate.com/notes/mongodb-crud.html

public class TesTMongo {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("tree");

        Document findQuery = new Document();
        //findQuery.append("_id", "dbm");
        //findQuery.append("parent", "Databases");
        findQuery.append("ancestors", "Programming");


        FindIterable<Document> iterable = db.getCollection("categories").find(findQuery);
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
                List privileges = (ArrayList) document.get("ancestors");
                System.out.println(privileges.size());
              //  System.out.println(document.get("ancestors") + " : " + document.get("path"));
            }
        });

       //

  /*      Document regQuery = new Document();
        regQuery.append("$regex", "^(?)" + Pattern.quote("/,Programming,/"));
     //   regQuery.append("$options", "i");

        Document findQuery = new Document();
        findQuery.append("path", regQuery);

        iterable = db.getCollection("categories").find(new Document(findQuery));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
              //  System.out.println(document.get("_id") + " : " + document.get("path"));
            }
        });*/

    }
}
