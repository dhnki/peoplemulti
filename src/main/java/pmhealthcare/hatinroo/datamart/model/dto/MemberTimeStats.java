package pmhealthcare.hatinroo.datamart.model.dto;

import lombok.Getter;
import lombok.Setter;

import pmhealthcare.hatinroo.datamart.model.device.date.Activity;
import pmhealthcare.hatinroo.datamart.model.device.date.Sleep;
import pmhealthcare.hatinroo.datamart.model.device.date.Stay;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MemberTimeStats {

    private long memberNo;

    private long deviceNo;

    private String deviceId;

    private List<Stay> stays;

    private List<Activity> activities;

//    private List<Meal> meals;

    private List<Sleep> sleeps;


    public MemberTimeStats(long memberNo, long deviceNo, String deviceId) {
        this.memberNo = memberNo;
        this.deviceNo = deviceNo;
        this.deviceId = deviceId;
        this.stays = new ArrayList<>();
        this.activities = new ArrayList<>();
//        this.meals = new ArrayList<>();
        this.sleeps = new ArrayList<>();
    }

    public void addStay(Stay stay){
        stays.add(stay);
    }

    public void addActivity(Activity activity){
        activities.add(activity);
    }

//    public void addMeal(Meal meal){
//        meals.add(meal);
//    }

    public void addSleep(Sleep sleep){
        sleeps.add(sleep);
    }

}
