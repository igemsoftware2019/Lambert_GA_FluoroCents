package com.krishikishore.flouro_q;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;


import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "fluorocents-mobilehub-1891069775-fluorocentsdata")

public class FluorocentsdataDO {
    private Double _userId;
    private String _timestampvalue;
    private String _diseasestatusvalue;
    private String _individualvalue;
    private String _latitude;
    private String _longitude;
    private String _nearestbodyofwatervalue;
    private String _notesvalue;
    private String _sourcevalue;
    private String _xCount;
    private String _xMean;
    private String _xVar;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public Double getUserId() {
        return _userId;
    }

    public void setUserId(final Double _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "timestampvalue")
    @DynamoDBAttribute(attributeName = "timestampvalue")
    public String getTimestampvalue() {
        return _timestampvalue;
    }

    public void setTimestampvalue(final String _timestampvalue) {
        this._timestampvalue = _timestampvalue;
    }
    @DynamoDBAttribute(attributeName = "diseasestatusvalue")
    public String getDiseasestatusvalue() {
        return _diseasestatusvalue;
    }

    public void setDiseasestatusvalue(final String _diseasestatusvalue) {
        this._diseasestatusvalue = _diseasestatusvalue;
    }
    @DynamoDBAttribute(attributeName = "individualvalue")
    public String getIndividualvalue() {
        return _individualvalue;
    }

    public void setIndividualvalue(final String _individualvalue) {
        this._individualvalue = _individualvalue;
    }
    @DynamoDBAttribute(attributeName = "latitude")
    public String getLatitude() {
        return _latitude;
    }

    public void setLatitude(final String _latitude) {
        this._latitude = _latitude;
    }
    @DynamoDBAttribute(attributeName = "longitude")
    public String getLongitude() {
        return _longitude;
    }

    public void setLongitude(final String _longitude) {
        this._longitude = _longitude;
    }
    @DynamoDBAttribute(attributeName = "nearestbodyofwatervalue")
    public String getNearestbodyofwatervalue() {
        return _nearestbodyofwatervalue;
    }

    public void setNearestbodyofwatervalue(final String _nearestbodyofwatervalue) {
        this._nearestbodyofwatervalue = _nearestbodyofwatervalue;
    }
    @DynamoDBAttribute(attributeName = "notesvalue")
    public String getNotesvalue() {
        return _notesvalue;
    }

    public void setNotesvalue(final String _notesvalue) {
        this._notesvalue = _notesvalue;
    }
    @DynamoDBAttribute(attributeName = "sourcevalue")
    public String getSourcevalue() {
        return _sourcevalue;
    }

    public void setSourcevalue(final String _sourcevalue) {
        this._sourcevalue = _sourcevalue;
    }
    @DynamoDBAttribute(attributeName = "xCount")
    public String getXCount() {
        return _xCount;
    }

    public void setXCount(final String _xCount) {
        this._xCount = _xCount;
    }
    @DynamoDBAttribute(attributeName = "xMean")
    public String getXMean() {
        return _xMean;
    }

    public void setXMean(final String _xMean) {
        this._xMean = _xMean;
    }
    @DynamoDBAttribute(attributeName = "xVar")
    public String getXVar() {
        return _xVar;
    }

    public void setXVar(final String _xVar) { this._xVar = _xVar; }

}
