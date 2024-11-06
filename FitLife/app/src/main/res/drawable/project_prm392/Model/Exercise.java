//package com.example.project_prm392.Model;
//
//public class Exercise {
//    private int id;
//    private String name;
//    private int repeatTimes;
//    private String duration;
//    private int imageResId;
//
//    public Exercise() {
//    }
//
//    public Exercise(String name, int repeatTimes, String duration, int imageResId) {
//        this.name = name;
//        this.repeatTimes = repeatTimes;
//        this.duration = duration;
//        this.imageResId = imageResId;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getRepeatTimes() {
//        return repeatTimes;
//    }
//
//    public void setRepeatTimes(int repeatTimes) {
//        this.repeatTimes = repeatTimes;
//    }
//
//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }
//
//    public int getImageResId() {
//        return imageResId;
//    }
//
//    public void setImageResId(int imageResId) {
//        this.imageResId = imageResId;
//    }
//}
package com.example.project_prm392.Model;

public class Exercise {
    private int id;
    private String name;
    private int repeatTimes;
    private String duration;
    private int imageResId;
    private int typeId; // New field

    public Exercise() {
    }

    public Exercise(String name, int repeatTimes, String duration, int imageResId, int typeId) {
        this.name = name;
        this.repeatTimes = repeatTimes;
        this.duration = duration;
        this.imageResId = imageResId;
        this.typeId = typeId;
    }

    // Getters and setters for all fields including typeId

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int repeatTimes) {
        this.repeatTimes = repeatTimes;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
