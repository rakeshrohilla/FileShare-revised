package com.rakeshrohilla.demo.Fileshare;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.List;

public class DatabaseAccess {
    private static final String COGNITO_POOL_ID = "ap-south-1:1111aaaa-111a-1a11-11aa-a1111111111a";
    private static final Regions MY_REGION = Regions.AP_SOUTH_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private Context context;
    private final String DYNAMODB_TABLE = "user";
    CognitoCachingCredentialsProvider credentialsProvider;


    private static volatile DatabaseAccess instance;
    private DatabaseAccess (Context context) {
        this.context =context;
        credentialsProvider = new CognitoCachingCredentialsProvider(context, COGNITO_POOL_ID, MY_REGION);
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);
    }
    public static synchronized DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }
    public Document getItem (String user_id){
        Document result = dbTable.getItem(new Primitive(credentialsProvider.getCachedIdentityId()), new Primitive(user_id));
        return result;
    }
    public List<Document> getAllItems() {
        return dbTable.query(new Primitive("9988")).getAllResults();
    }

}
