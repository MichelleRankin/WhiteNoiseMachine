import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WhiteNoiseMachineTest {

    @Test
     void testInitialState() {
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        assertEquals(false, whiteNoise.isOn);
        assertEquals(50, whiteNoise.volume);
        assertEquals(false, whiteNoise.isLocked);
        assertEquals(false, whiteNoise.isNightLightOn);
        assertEquals("BRIGHT", whiteNoise.sound);
    }

    @Test
    void testTurnOnAndOff(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(true, whiteNoise.isOn);
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(false, whiteNoise.isOn);
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(true, whiteNoise.isOn);
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(false, whiteNoise.isOn);
    }

    @Test
    void testNightLight(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.isNightLightOn = true;
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(false, whiteNoise.isOn);
        assertEquals(false, whiteNoise.isNightLightOn);
    }

    @Test
    void testVolume(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.volume = 70;
        whiteNoise.pressButtons(new String[]{"POWER"});
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(70, whiteNoise.volume);

    }

    @Test
    void testSound(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.sound = "SURF";

        whiteNoise.pressButtons(new String[]{"POWER"});
        whiteNoise.pressButtons(new String[]{"POWER"});

        assertEquals("SURF", whiteNoise.sound);
    }

    @Test
    void testVolumeManagement(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.volume = 70;
        whiteNoise.pressButtons(new String[]{"VOL+"});
        assertEquals(80, whiteNoise.volume);

        whiteNoise.pressButtons(new String[]{"VOL+"});
        whiteNoise.pressButtons(new String[]{"VOL+"});
        whiteNoise.pressButtons(new String[]{"VOL+"});
        whiteNoise.pressButtons(new String[]{"VOL+"});
        whiteNoise.pressButtons(new String[]{"VOL+"});
        whiteNoise.pressButtons(new String[]{"VOL+"});

        assertEquals(100, whiteNoise.volume);
    }

    @Test
    void testDecreaseVolume(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.volume = 70;

        whiteNoise.pressButtons(new String[]{"VOL-"});
        assertEquals(60, whiteNoise.volume);

        whiteNoise.volume = 30;
        whiteNoise.pressButtons(new String[]{"VOL-"});
        whiteNoise.pressButtons(new String[]{"VOL-"});
        whiteNoise.pressButtons(new String[]{"VOL-"});
        whiteNoise.pressButtons(new String[]{"VOL-"});
        whiteNoise.pressButtons(new String[]{"VOL-"});
        whiteNoise.pressButtons(new String[]{"VOL-"});

        assertEquals(0, whiteNoise.volume);
    }

    @Test
    void testSoundSelection(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        assertEquals("BRIGHT", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("DEEP", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("SURF", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("BRIGHT", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("DEEP", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("SURF", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("BRIGHT", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("DEEP", whiteNoise.sound);
    }

    @Test
    @DisplayName("locks when the sound and power button are pressed simultaneously")
    void testLockAndUnlock(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isLocked = false;
        whiteNoise.isOn = true;
        whiteNoise.pressButtons(new String[]{"POWER", "S"});
        assertEquals(true, whiteNoise.isLocked);
        assertEquals(true, whiteNoise.isOn);
    }

    @Test
    void testDoNotProcess(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.isLocked = true;
        whiteNoise.sound = "BRIGHT";
        whiteNoise.volume = 50;

        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(true, whiteNoise.isLocked);
        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("BRIGHT", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"VOL+"});
        assertEquals(50, whiteNoise.volume);
        whiteNoise.pressButtons(new String[]{"VOL-"});
        assertEquals(50, whiteNoise.volume);
    }

    @Test
    void testNL(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = true;
        whiteNoise.isNightLightOn = false;
        whiteNoise.pressButtons(new String[]{"POWER", "POWER"});
        assertEquals(true, whiteNoise.isNightLightOn);
    }

    @Test
    void testDontProcessWhileOff(){
        WhiteNoiseMachine whiteNoise = new WhiteNoiseMachine();
        whiteNoise.isOn = false;
        whiteNoise.isLocked = false;
        whiteNoise.sound = "BRIGHT";
        whiteNoise.volume = 50;

        whiteNoise.pressButtons(new String[]{"S"});
        assertEquals("BRIGHT", whiteNoise.sound);
        whiteNoise.pressButtons(new String[]{"VOL+"});
        assertEquals(50, whiteNoise.volume);
        whiteNoise.pressButtons(new String[]{"VOL-"});
        assertEquals(50, whiteNoise.volume);
        whiteNoise.pressButtons(new String[]{"POWER", "S"});
        assertEquals(false, whiteNoise.isLocked);
        whiteNoise.pressButtons(new String[]{"POWER"});
        assertEquals(true, whiteNoise.isOn);
    }

}