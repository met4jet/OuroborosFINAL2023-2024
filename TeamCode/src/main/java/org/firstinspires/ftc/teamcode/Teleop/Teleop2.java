package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.HashMap;

@TeleOp(name = "Testing Switch")
public class Teleop2 extends LinearOpMode {
    public Servo shoomShoomDom;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    @Override
    public void runOpMode() throws InterruptedException {

        shoomShoomDom = hardwareMap.get(Servo.class, "shoomShoomDom");

        ArrayList Box = new ArrayList<String>();


        waitForStart();
        String switchDom = "CLOSE";
        ElapsedTime time = new ElapsedTime();
        while(opModeIsActive()){
            switch(switchDom) {
                case "CLOSE":
                    if(gamepad1.a){
                        shoomShoomDom.setPosition(1);
                        switchDom = "OPEN";
                    }
                    break;
                case "OPEN":
                    if(gamepad1.a){
                        shoomShoomDom.setPosition(0);
                        switchDom = "CLOSE";
                    }
                    break;
                default:
                    switchDom = "CLOSE";
            }

        }
    }
    public boolean isPressed(String str, boolean button) {
        ElapsedTime time = new ElapsedTime();
        boolean output = false;
        if(!buttons.containsKey(str)) {
            buttons.put(str, false);
            output = false;
        }
        boolean buttonPrev = buttons.get(str);
        if(button != buttonPrev) {
            output = true;
        }
        buttons.put(str,output);

        return output;
    }
}
