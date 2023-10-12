package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ThreadHandler;

public abstract class Telelib extends OpMode {

    // Difficulty: EASY
    // All: Create your motors and servos
    double hPower = 1;
    double half = 1;
    boolean halfToggle = false;
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;
    public DcMotor motorLift;
    public DcMotor horizontalLift;

    public Servo outtakeLeft;
    public Servo outtakeRight;

    public Servo planeLauncher;
    public Servo planeAngler;

    public ThreadHandler th_horiLift;
    public ThreadHandler th_arcadeDrive;

    @Override
    public void init(){
        // Difficulty: EASY
        // All: Hardware map your motors and servos

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        horizontalLift = hardwareMap.get(DcMotor.class, "horizontalLift");

        th_horiLift = new ThreadHandler();
        th_arcadeDrive = new ThreadHandler();

        // Difficulty: EASY
        // All: Set your motors' zero power behavior
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Difficulty: EASY
        // All: Set your motors' directions
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLift.setDirection(DcMotorSimple.Direction.FORWARD);
        horizontalLift.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    Thread horizontal_lift_move = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){
            }
            while(Math.abs(horizontalLift.getCurrentPosition()) < 10){
                horizontalLift.setPower(hPower);
            }
        }
    });

    Thread full_speed = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            half = 1;
            halfToggle = false;
        }
    });

    Thread half_speed = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            half = .5;
            halfToggle = true;
        }
    });

    public void horizontalLift() {

        if(gamepad2.left_stick_y > .1){
            th_horiLift.queue(horizontal_lift_move);
        }

    }

    public void arcadeDrive(){

        if (gamepad1.a && halfToggle){
            th_arcadeDrive.queue(half_speed);
        }
        else{
            th_arcadeDrive.queue(full_speed);
        }

        double left_stick_x = gamepad1.left_stick_x;
        double left_stick_y = gamepad1.left_stick_y;
        double right_stick_x = gamepad1.right_stick_x;

        if (Math.abs(left_stick_x) > 0.1 ||
                Math.abs(left_stick_y) >.1|| Math.abs(right_stick_x) > 0.1){
            fr.setPower((left_stick_y + left_stick_x) + right_stick_x);
            fl.setPower((left_stick_y - left_stick_x) - right_stick_x);
            br.setPower((left_stick_y - left_stick_x) + right_stick_x);
            bl.setPower((left_stick_y + left_stick_x) - right_stick_x);
        }
        else{
            fl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
        }
    }

    public void planeLauncher(){
        if (gamepad1.left_trigger > 0.3){
            if (planeLauncher.getPosition() != 1.0) {
                planeLauncher.setPosition(1.0);
            } else {
                planeLauncher.setPosition(0.0);
            }
        }
        if (gamepad1.right_trigger > 0.3){
            if (planeAngler.getPosition() != 1.0){
                planeAngler.setPosition(1.0);
            } else {
                planeAngler.setPosition(0.0);
            }
        }
    }

    public void outtake(){
        if (gamepad2.left_bumper){
            outtakeLeft.setPosition(1);
            while (outtakeLeft.getPosition() != 1){
                // do nothing
            }
            outtakeLeft.setPosition(0);
        }
        if (gamepad2.right_bumper){
            outtakeRight.setPosition(1);  
            while (outtakeRight.getPosition() != 1){
                // do nothing
            }
            outtakeRight.setPosition(0);
        }
    }

    public void kill(){
        // Difficulty: EASY
        // Elin
        // This method shuts off the power from all motors
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);

        // sophia: I pushed pheonix's code for the lift, so you should be able to see it now.
        // can you also shut off the power for the lift motor as well.
    }

}
