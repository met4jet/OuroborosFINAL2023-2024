package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import static android.os.SystemClock.sleep;

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
    public DcMotor motorLift2;
    public DcMotor horizontalLift;
    public DcMotor horizontalLift2;


    public Servo outtakeLeft;
    public Servo outtakeRight;
    public Servo intakeBox;
    public Servo shoomShoomTop;
    public Servo shoomShoomBottom;
    public Servo planeLauncher;
    public Servo planeAngler;
    public Servo flipPad;
    public Servo flipPad2;

    public CRServo intakeWheels;

    public ThreadHandler th_horiLift;
    public ThreadHandler th_arcadeDrive;
    public  ThreadHandler th_planeLauncher;
    public ThreadHandler th_outtake;
    @Override
    public void init(){
        // Difficulty: EASY
        // All: Hardware map your motors and servos

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        motorLift = hardwareMap.get(DcMotor.class, "motorLift");
        motorLift2 = hardwareMap.get(DcMotor.class, "motorLift2");
        horizontalLift = hardwareMap.get(DcMotor.class, "horizontalLift");
        horizontalLift2 = hardwareMap.get(DcMotor.class, "horizontalLift2");

        outtakeLeft = hardwareMap.get(Servo.class, "outtakeLeft");
        outtakeRight = hardwareMap.get(Servo.class, "outtakeRight");
        intakeBox = hardwareMap.get(Servo.class, "intakeBox");
        shoomShoomTop = hardwareMap.get(Servo.class, "shoomShoomTop");
        shoomShoomBottom = hardwareMap.get(Servo.class, "shoomShoomBottom");

        planeLauncher = hardwareMap.get(Servo.class, "planeLauncher");
        planeAngler = hardwareMap.get(Servo.class, "planeAngler");
        flipPad = hardwareMap.get(Servo.class, "flipPad");
        flipPad2 = hardwareMap.get(Servo.class, "flipPad2");

        intakeWheels = hardwareMap.get(CRServo.class, "intakeWheels");

        th_horiLift = new ThreadHandler();
        th_arcadeDrive = new ThreadHandler();
        th_planeLauncher = new ThreadHandler();
        th_outtake = new ThreadHandler();

        // Difficulty: EASY
        // All: Set your motors' zero power behavior
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorLift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalLift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Difficulty: EASY
        // All: Set your motors' directions
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLift.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLift2.setDirection(DcMotorSimple.Direction.FORWARD);
        horizontalLift.setDirection(DcMotorSimple.Direction.FORWARD);
        horizontalLift2.setDirection(DcMotorSimple.Direction.FORWARD);
    }
    public void resetEncoders() {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        horizontalLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        horizontalLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalLift2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLift2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    Thread horizontal_lift_move = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            resetEncoders();

            while(time.milliseconds() < 300){
            }
            while(Math.abs(horizontalLift.getCurrentPosition()) < 10){
                horizontalLift.setPower(hPower);
                horizontalLift2.setPower(hPower);
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
    Thread intake_extension = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            resetEncoders();
            while (time.milliseconds() < 300) {
            }
            while(Math.abs(horizontalLift.getCurrentPosition()) > 10) {
                horizontalLift.setPower(-1);
                horizontalLift2.setPower(-1);
            }
            intakeBox.setPosition(1);
            intakeBox.setPosition(0);
            sleep(50);
            shoomShoomTop.setPosition(0);
            shoomShoomBottom.setPosition(1);
            sleep(100);
            shoomShoomBottom.setPosition(0);
            shoomShoomTop.setPosition(1);
        }
    });

    Thread outtake_extension = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            while(Math.abs(motorLift.getCurrentPosition()) < 100) {
                motorLift.setPower(1);
                motorLift2.setPower(1);
            }
            flipPad.setPosition(1);
            flipPad2.setPosition(1);
            sleep(100);
            flipPad.setPosition(0);
            flipPad.setPosition(0);
        }
    });


    Thread plane_launcher_launch = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            planeLauncher.setPosition(1.0);
        }
    });

    Thread plane_launcher_ready = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            planeLauncher.setPosition(0.0);
        }
    });

    Thread plane_angler_up = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            planeAngler.setPosition(1.0);
        }
    });

    Thread plane_angler_down = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
            planeAngler.setPosition(0.0);
        }
    });

    Thread left_Outtake = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 300){

            }
            outtakeLeft.setPosition(1);
            while (outtakeLeft.getPosition() != 1){
                // do nothing
            }
            outtakeLeft.setPosition(0);
        }
    });

    Thread right_Outtake = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 300){

            }
            outtakeRight.setPosition(1);
            while (outtakeRight.getPosition() != 1){
                // do nothing
            }
            outtakeRight.setPosition(0);
        }
    });
    public void intake() {
        //horizontal lift movement
        if(gamepad2.left_stick_y > .1){
            hPower = 1;
            th_horiLift.queue(horizontal_lift_move);
        }
        if (gamepad2.left_stick_y < -.1) {
            hPower = -1;
            th_horiLift.queue(horizontal_lift_move);
        }

        //Intake Spoked Wheels
        if(gamepad2.right_trigger > .1) {
            intakeWheels.setPower(1);
        }
        else if (gamepad2.left_trigger > .1) {
            intakeWheels.setPower(-1);
        }

        //Returning intake macro
        if(gamepad2.b) {
            th_horiLift.queue(intake_extension);
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
                th_planeLauncher.queue(plane_launcher_launch);
            } else {
                th_planeLauncher.queue(plane_launcher_ready);
            }
        }
        if (gamepad1.right_trigger > 0.3){
            if (planeAngler.getPosition() != 1.0){
                th_planeLauncher.queue(plane_angler_up);
            } else {
                th_planeLauncher.queue(plane_angler_down);
            }
        }
    }

    public void outtake(){
        if (gamepad2.left_bumper){
            th_outtake.queue(left_Outtake);
        }
        if (gamepad2.right_bumper){
            th_outtake.queue(right_Outtake);
        }
        if(gamepad2.x) {
            th_outtake.queue(outtake_extension);
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