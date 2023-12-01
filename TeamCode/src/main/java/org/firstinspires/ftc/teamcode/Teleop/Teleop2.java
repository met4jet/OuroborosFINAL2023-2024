package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
    public CRServo intake;
    public Servo rflip;
    public Servo lflip;
    public Servo linac;
    public Servo shoomShoomSub;
    public CRServo box;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    @Override
    public void runOpMode() throws InterruptedException {

        shoomShoomDom = hardwareMap.get(Servo.class, "shoomShoomDom");
        linac = hardwareMap.get(Servo.class, "linac");
        box = hardwareMap.get(CRServo.class, "box");
        shoomShoomSub = hardwareMap.get(Servo.class, "shoomShoomSub");
        linac = hardwareMap.get(Servo.class, "linac");
        lflip = hardwareMap.get(Servo.class, "lflip");
        rflip = hardwareMap.get(Servo.class, "rflip");
        intake = hardwareMap.get(CRServo.class, "intake");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        ArrayList Box = new ArrayList<String>();


        waitForStart();

        //DOM
        String switchDom = "CLOSE";
        String heightchanger = "CLOSE";
        String switchSub = "CLOSE";
        String switchLFlip = "CLOSE";
        String switchRFlip = "CLOSE";

        ElapsedTime time = new ElapsedTime();
        while(opModeIsActive()){
            switch(switchDom) {
                case "CLOSE":
                    if(gamepad2.a){
                        shoomShoomDom.setPosition(.2);
                        switchDom = "OPEN";
                    }
                    break;
                case "OPEN":
                    if(gamepad2.a){
                        shoomShoomDom.setPosition(.52);
                        switchDom = "CLOSE";
                    }
                    break;
                default:
                    switchDom = "CLOSE";
            }
            switch(heightchanger) {
                case "CLOSE":
                    if(gamepad2.b){
                        linac.setPosition(1);
                        heightchanger = "OPEN";
                    }
                    break;
                case "OPEN":
                    if(gamepad2.b){
                        linac.setPosition(0);
                        heightchanger = "CLOSE";
                    }
                    break;
                default:
                    switchDom = "CLOSE";
            }
            //SUB
            switch(switchSub) {
                case "CLOSE":
                    if (gamepad2.x) {
                        shoomShoomSub.setPosition(1);
                        switchSub = "OPEN";
                    }
                    break;
                case "OPEN":
                    if (gamepad2.x) {
                        shoomShoomSub.setPosition(0);
                        switchSub = "CLOSE";
                    }
                    break;
                default:
                    switchSub = "CLOSE";
            }
            //SUB
            switch(switchLFlip) {
                case "CLOSE":
                    if (gamepad2.left_bumper) {
                        lflip.setPosition(1);
                        switchLFlip = "OPEN";
                    }
                    break;
                case "OPEN":
                    if (gamepad2.left_bumper) {
                        lflip.setPosition(0);
                        switchLFlip = "CLOSE";
                    }
                    break;
                default:
                    switchLFlip = "CLOSE";
            }
            switch(switchRFlip) {
                case "CLOSE":
                    if (gamepad2.right_bumper) {
                        rflip.setPosition(1);
                        switchRFlip = "OPEN";
                    }
                    break;
                case "OPEN":
                    if (gamepad2.right_bumper) {
                        rflip.setPosition(0);
                        switchRFlip = "CLOSE";
                    }
                    break;
                default:
                    switchRFlip = "CLOSE";
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
    /*public boolean isPressed(String str, boolean button) {
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

        return output;*/
}

