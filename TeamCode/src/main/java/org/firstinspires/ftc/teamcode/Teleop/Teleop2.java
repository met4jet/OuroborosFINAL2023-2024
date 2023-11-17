package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.HashMap;

@TeleOp(name = "Testing Switch")
public class Teleop2 extends LinearOpMode {
    public Servo shoomShoomDom;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    @Override
    public void runOpMode() throws InterruptedException {

        shoomShoomDom = hardwareMap.get(Servo.class, "shoomShoomDom");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        ArrayList Box = new ArrayList<String>();


        waitForStart();

        //DOM
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

            // DRIVE TRAIN
            double left_stick_x = gamepad1.left_stick_x;
            double left_stick_y = gamepad1.left_stick_y;
            double right_stick_x = gamepad1.right_stick_x;

            if (Math.abs(left_stick_x) > 0.1 ||
                    Math.abs(left_stick_y) >.1|| Math.abs(right_stick_x) > 0.1){
                fr.setPower(((left_stick_y + left_stick_x) + right_stick_x));
                fl.setPower(((left_stick_y - left_stick_x) - right_stick_x));
                br.setPower(((left_stick_y - left_stick_x) + right_stick_x));
                bl.setPower(((left_stick_y + left_stick_x) - right_stick_x));
            }
            else{
                fl.setPower(0);
                fr.setPower(0);
                br.setPower(0);
                bl.setPower(0);
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
