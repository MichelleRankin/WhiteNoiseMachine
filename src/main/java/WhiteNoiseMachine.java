
import java.util.*;
import java.lang.*;


public class WhiteNoiseMachine {
    boolean isOn = false;
    String sound = "BRIGHT";
    int volume = 50;
    boolean isLocked = false;
    boolean isNightLightOn = false;


     void pressButtons(String[] action) {
         List<String> actionList = new ArrayList<>(Arrays.asList(action));
         int actionSize = actionList.size();
             if (actionSize == 1 && actionList.contains("POWER")) {
                 if (isOn == true) {
                     isOn = false;
                     isNightLightOn = false;

                 } else if (isOn == false) {
                     isOn = true;
                 }
             }
         if (isOn == true) {

             if (actionSize == 1 && actionList.contains("VOL+")) {
                 if (volume != 100) {
                     volume += 10;
                 }

             } else if (actionSize == 1 && actionList.contains("VOL-")) {
                 if (volume != 0) {
                     volume -= 10;
                 }

             } else if (actionSize == 1 && actionList.contains("S")) {
                 if (sound.equals("BRIGHT")) {
                     sound = "DEEP";
                 } else if (sound.equals("DEEP")) {
                     sound = "SURF";
                 } else if (sound.equals("SURF")) {
                     sound = "BRIGHT";
                 }
             } else if (actionSize == 2 && actionList.contains("POWER") && actionList.contains("S")) {
                 if (isLocked == false) {
                     isLocked = true;
                 } else {
                     isLocked = false;
                 }
             } else if(actionSize == 2 && actionList.contains("POWER") && actionList.contains("POWER")){
                 if(isNightLightOn == false){
                     isNightLightOn = true;
                 } else{
                     isNightLightOn = false;
                 }
             }


         }
     }}