package com.wristcode.dynamoexample.model;

import android.os.Build;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName="riyaz_dev_submissions_for_review")
public class Datas {


    public Integer userId;
    public Long timeStamp;
    public String title;
    public Boolean isReviewed;


    @DynamoDBAttribute(attributeName="userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @DynamoDBAttribute(attributeName="timeStamp")
    public int getTimeStamp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Math.toIntExact(timeStamp);
        }
        return 0;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
    @DynamoDBAttribute(attributeName="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @DynamoDBAttribute(attributeName="isReviewed")
    public Boolean getReviewed() {
        return isReviewed;
    }

    public void setReviewed(Boolean reviewed) {
        isReviewed = reviewed;
    }



}
