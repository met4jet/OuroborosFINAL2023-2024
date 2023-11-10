package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;

@TeleOp(name = "Testing Switch")
public class Teleop2 extends LinearOpMode {
    public Servo linac;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    @Override
    public void runOpMode() throws InterruptedException {

        linac = hardwareMap.get(Servo.class, "linac");

        /*enum Box{
            DELIVER,
            REST
        }*/

        waitForStart();

        while(opModeIsActive()){


            if(isPressed("x", gamepad2.x)) //&& linac.getPosition() != 1
            {
                linac.setPosition(1);
                telemetry.addLine("linac:" + linac.getPosition());
            }
            else //if(!isPressed("x", gamepad2.x)) //&& linac.getPosition() != 0
            {
                linac.setPosition(0);
                telemetry.addLine("linac:" + linac.getPosition());
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
