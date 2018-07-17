/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author QuyPC
 */
public class FilterSchool {

    private String schoolName;
    private String[] location;
    private String[] field;
    private String[] typeSchool;
    private String[] sjCombi;
    private String minPoint;
   
 
    public FilterSchool() {
    }

    public FilterSchool(String schoolName, String[] location, String[] field, String[] typeSchool, String[] sjCombi, String minPoint) {
        this.schoolName = schoolName;
        this.location = location;
        this.field = field;
        this.typeSchool = typeSchool;
        this.sjCombi = sjCombi;
        this.minPoint = minPoint;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public String[] getField() {
        return field;
    }

    public void setField(String[] field) {
        this.field = field;
    }

    public String[] getTypeSchool() {
        return typeSchool;
    }

    public void setTypeSchool(String[] typeSchool) {
        this.typeSchool = typeSchool;
    }

    public String[] getSjCombi() {
        return sjCombi;
    }

    public void setSjCombi(String[] sjCombi) {
        this.sjCombi = sjCombi;
    }

    public String getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(String minPoint) {
        this.minPoint = minPoint;
    } 
}
