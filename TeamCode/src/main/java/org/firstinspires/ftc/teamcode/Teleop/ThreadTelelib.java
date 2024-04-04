package org.firstinspires.ftc.teamcode.Teleop;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ThreadHandler;

import java.util.HashMap;

public abstract class ThreadTelelib extends OpMode {
    // Difficulty: EASY
    // All: Create your motors and servos
    boolean sub = false;
    boolean dom = false;
    double hPower = 1;
    double position = 0;
    boolean halfToggle = false;
    public DcMotor fl;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    public DcMotor intake;
    //public CRServo rollers;
    public Servo rflip;
    public Servo lflip;
    public Servo linearAct;
    public Servo shoomShoomDom;
    public Servo shoomShoomSub;
    //public Servo box;
    //public CRServo linac;
    public Servo drone;
    public ThreadHandler th_horiLift;
    public ThreadHandler th_arcadeDrive;
    public ThreadHandler th_planeLauncher;
    public ThreadHandler th_outtake;
    public ThreadHandler th_shoomSub;
    public ThreadHandler th_shoomDom;
    public ThreadHandler th_leftFlip;
    public ThreadHandler th_rightFlip;
    public ThreadHandler th_verticalLiftLeft;
    public ThreadHandler th_verticalLiftRight;
    /*public ThreadHandler th_box;
    public ThreadHandler th_linac;
    public ThreadHandler th_linac_down;*/
    public ThreadHandler th_intake;


    /*public DcMotor horizontalLiftRight;
    public DcMotor horizontalLiftLeft;*/
    public DcMotor verticalLiftRight;
    public DcMotor verticalLiftLeft;
    public Servo axon;

    public Servo deposit;
    public ThreadHandler th_axon;

    public void init(){
        // Difficulty: EASY
        // All: Hardware map your motors and servos

        th_axon = new ThreadHandler();
        th_horiLift = new ThreadHandler();
        th_arcadeDrive = new ThreadHandler();
        th_planeLauncher = new ThreadHandler();
        th_outtake = new ThreadHandler();
        th_shoomDom = new ThreadHandler();
        th_shoomSub = new ThreadHandler();
        th_leftFlip = new ThreadHandler();
        th_rightFlip = new ThreadHandler();
        th_verticalLiftLeft = new ThreadHandler();;
        th_verticalLiftRight = new ThreadHandler();
        /*th_box = new ThreadHandler();
        th_linac = new ThreadHandler();
        th_linac_down = new ThreadHandler();*/
        th_intake = new ThreadHandler();

        //linac = hardwareMap.get(Servo.class, "linac");

        rflip = hardwareMap.get(Servo.class, "rflip");
        lflip = hardwareMap.get(Servo.class, "lflip");
        shoomShoomDom = hardwareMap.get(Servo.class, "shoomShoomDom");
        shoomShoomSub = hardwareMap.get(Servo.class, "shoomShoomSub");
        drone = hardwareMap.get(Servo.class, "drone");
        axon = hardwareMap.get(Servo.class, "axon");
        //box = hardwareMap.get(Servo.class, "box");
        //rollers = hardwareMap.get(CRServo.class, "rollers");
        //linac = hardwareMap.get(CRServo.class, "linac");

        intake = hardwareMap.get(DcMotor.class, "intake");
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        /*horizontalLiftRight = hardwareMap.get(DcMotor.class, "hlr");
        horizontalLiftLeft = hardwareMap.get(DcMotor.class, "hll");*/

        verticalLiftRight = hardwareMap.get(DcMotor.class, "vlr");
        verticalLiftLeft = hardwareMap.get(DcMotor.class, "vll");


        // Difficulty: EASY
        // All: Set your motors' zero power behavior
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        /*horizontalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        horizontalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);*/

        verticalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        verticalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Difficulty: EASY
        // All: Set your motors' directions
        fl.setDirection(DcMotorSimple.Direction.FORWARD); //f fl and br
        bl.setDirection(DcMotorSimple.Direction.FORWARD); //r
        fr.setDirection(DcMotorSimple.Direction.REVERSE); //f
        br.setDirection(DcMotorSimple.Direction.REVERSE); //r
        axon.setDirection(Servo.Direction.REVERSE);

        /*horizontalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        horizontalLiftLeft.setDirection(DcMotorSimple.Direction.FORWARD);*/

        verticalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalLiftLeft.setDirection(DcMotorSimple.Direction.FORWARD);

    }
    Thread axon_intake = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 150){

            }
            axon.setPosition(.7);
        }
    });

    Thread axon_outtake = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 150){

            }
            axon.setPosition(0);
        }
    });
    Thread right_flip_out = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 150){

            }
            rflip.setPosition(.7);
        }
    });
    Thread right_flip = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 200){

            }
            rflip.setPosition(1);
            sleep(1000);
            rflip.setPosition(0);
        }
    });
    Thread left_flip_out = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 150){

            }
            lflip.setPosition(1);
        }
    });
    Thread left_flip = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while (time.milliseconds() < 200){

            }
            lflip.setPosition(0);
            sleep(1000);
            lflip.setPosition(.8);
        }
    });
    Thread shoom_dom_dep = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 150){

            }
            shoomShoomDom.setPosition(.50);
            dom = false;
            sleep(300);

        }
    });
    Thread shoom_dom_return = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 200){

            }
            shoomShoomDom.setPosition(.285);
            dom = true;
            sleep(300);
            shoomShoomDom.setPosition(.72);

        }
    });
    Thread shoom_sub_return = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 150){

            }
            shoomShoomSub.setPosition(0);
            sub = true;
            sleep(300);

        }
    });

    Thread shoom_sub_dep = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 200){

            }
            shoomShoomSub.setPosition(.45);
            sleep(300);
            shoomShoomSub.setPosition(.76);
        }
    });

//    Thread horizontal_lift_out = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            ElapsedTime time = new ElapsedTime();
//            time.reset();
//            //resetEncoders();
//
//            while(time.milliseconds() < 50){
//            }
//
//            horizontalLiftLeft.setPower(.75);
//            horizontalLiftRight.setPower(.75);
//
//        }
//    });


    //    Thread horizontal_lift_in = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            ElapsedTime time = new ElapsedTime();
//            time.reset();
//            //resetEncoders();
//
//            while(time.milliseconds() < 50){
//            }
//
//            horizontalLiftLeft.setPower(-.75);
//            horizontalLiftRight.setPower(-.75);
//
//        }
//    });
    Thread vertical_right_up = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            //resetEncoders();

            while (time.milliseconds() < 50) {
            }
            verticalLiftRight.setPower(1);
        }
    });
    Thread vertical_right_down = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            //resetEncoders();

            while (time.milliseconds() < 50) {
            }
            verticalLiftRight.setPower(-1);
        }
    });
    Thread vertical_left_down = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            //resetEncoders();

            while (time.milliseconds() < 50) {
            }
            verticalLiftLeft.setPower(-1);
        }
    });
    Thread vertical_left_up = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            //resetEncoders();

            while (time.milliseconds() < 50) {
            }
            verticalLiftLeft.setPower(1);
        }
    });
    Thread full_speed = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 300){

            }
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
            halfToggle = true;
        }
    });
    Thread plane_launcher_hold = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 150){

            }
            drone.setPosition(1);
            telemetry.addData("DronePos", drone.getPosition());
            telemetry.update();

        }
    });

    Thread plane_launcher_launch = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 150){

            }
            drone.setPosition(0);
            telemetry.addData("DronePos", drone.getPosition());
            telemetry.update();

        }
    });
    Thread intake_in = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 50){

            }
            intake.setPower(-1);
            //rollers.setPower(-1);
        }
    });

    Thread intake_out = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 50){

            }
            intake.setPower(1);
            //rollers.setPower(1);
        }
    });
//    Thread box_dep = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            ElapsedTime time = new ElapsedTime();
//            time.reset();
//            while(time.milliseconds() < 200){
//
//            }
//            box.setPosition(1);
//            sleep(1000);
//            box.setPosition(.22);
//
//        }
//    });

    /*Thread linac_up = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 150){

            }
            /*if(position > .57){
                linac.setPosition(.57);
                position = .57;
            }
            else {
                linac.setPosition(position);
                position += .01;
            linac.setPower(1);
            }

    });*/

//    Thread linac_down = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            ElapsedTime time = new ElapsedTime();
//            time.reset();
//            while(time.milliseconds() < 150){
//
//            }
//            /*if(position <= .25){
//                linac.setPosition(.25);
//                position = .25;
//            }
//            else {
//                linac.setPosition(position);
//                position -= .01;
//
//            }*/
//        linac.setPower(-1);
//        }
//    });
    /*Thread transferMacro = new Thread(new Runnable() {
        @Override
        public void run() {
            ElapsedTime time = new ElapsedTime();
            time.reset();
            while(time.milliseconds() < 100){

            }
            box.setPosition(1);
            sleep(500);
            box.setPosition(.75);
            sleep(300);
            box.setPosition(1);
            sleep(300);
            box.setPosition(.22);
            sleep(500);

            shoomShoomDom.setPosition(.22);
            sleep(500);
            shoomShoomDom.setPosition(1);
            shoomShoomDom.setPosition(.5);
            sleep(500);
            shoomShoomSub.setPosition(0);
            sleep(500);
            shoomShoomSub.setPosition(1);
            sleep(100);
        }
    });*/



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
    public void deposit(){
        if(gamepad1.dpad_down){
            deposit.setPosition(1);
        }
        if(gamepad1.dpad_up){
            deposit.setPosition(0);
        }
    }
    public void shoomShoom(){
        if(gamepad1.left_bumper) /*&& shoomShoomSub.getPosition() != 0*/{
            th_shoomSub.queue(shoom_sub_dep);
            dom = false;
            telemetry.addData("lbumper", gamepad1.left_bumper);
            //sleep(250);
        }
        /*else if(isPressed("left_bumper1", gamepad1.left_bumper) && shoomShoomSub.getPosition() != 1){
            th_shoomSub.queue(shoom_sub_dep);
            dom = true;
            telemetry.addData("lbumper", gamepad1.left_bumper);
            //sleep(250);
        }*/
        if(gamepad1.right_bumper /*&& shoomShoomDom.getPosition() != 1*/) {
            th_shoomDom.queue(shoom_dom_return); //rest
            sub = false;
            telemetry.addLine("Pos:" + shoomShoomDom.getPosition());
            //sleep(250);
        }
        /*else if(isPressed("right_bumper1", gamepad1.right_bumper) && shoomShoomDom.getPosition() != 0.5){
            th_shoomDom.queue(shoom_dom_dep); //deliver
            sub = true;
            telemetry.addLine("Pos:" + shoomShoomDom.getPosition());
            //sleep(250);
        }*/
    }
    /*public void horizontal_lift(){
        if(gamepad2.left_stick_x < -.25){
            th_horiLift.queue(horizontal_lift_out);
            telemetry.addData("EncoderPosLeft: ", horizontalLiftLeft.getCurrentPosition());
            telemetry.addData("LeftPwr: ", horizontalLiftLeft.getPower());
            telemetry.update();
        }
        else if (gamepad2.left_stick_x > .25) {
            th_horiLift.queue(horizontal_lift_in);
            telemetry.addData("EncoderPosLeft: ", horizontalLiftLeft.getCurrentPosition());
            telemetry.addData("LeftPwr: ", horizontalLiftLeft.getPower());
            telemetry.update();
        }
        else{
            hPower = 0;
            horizontalLiftRight.setPower(0);
            horizontalLiftLeft.setPower(0);
        }

    }*/
    public void rflip(){
        if(gamepad2.right_bumper) {
            th_rightFlip.queue(right_flip);
        }
        /*else{
            rflip.setPosition(0);
        }*/
    }
    public void lflip(){
        if(gamepad2.left_bumper){
            th_leftFlip.queue(left_flip);
            telemetry.addData("lbumper2", gamepad2.left_bumper);
            telemetry.addData("lbumper2", lflip.getDirection());
            telemetry.addData("lflip", lflip.getPosition());
            telemetry.update();
        }
        telemetry.addData("lflip", lflip.getPosition());
        telemetry.update();
        /*else{
            lflip.setPosition(1);
        }*/
    }
    public void drone(){
        if(gamepad2.y){
            th_planeLauncher.queue(plane_launcher_hold);
            telemetry.addData("DronePos", drone.getPosition());
        }
        else {
            th_planeLauncher.queue(plane_launcher_launch);
            telemetry.addData("DronePos", drone.getPosition());
            telemetry.update();
        }
    }
    /*public void box(){
        if(gamepad2.x){
            th_box.queue(box_dep);
        }
        /*else {
            th_box.queue(box_dep);
        }
    }*/
    public void vertical_lift_left(){
        if (gamepad2.left_stick_y > .2) {
            th_verticalLiftLeft.queue(vertical_left_up);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.update();
        } else if (gamepad2.left_stick_y < -.2) {
            th_verticalLiftLeft.queue(vertical_left_down);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.update();
        }
        else {
            verticalLiftLeft.setPower(0);
        }
    }
    public void vertical_lift_right(){
        if (gamepad2.right_stick_y > .2) {
            th_verticalLiftRight.queue(vertical_right_up);
        } else if (gamepad2.right_stick_y < -.2) {
            th_verticalLiftRight.queue(vertical_right_down);
        }
        else {
            verticalLiftRight.setPower(0);
        }
    }

    /*public void linac(){
        if (gamepad2.a) {
            th_linac.queue(linac_up);
            telemetry.addData("Position Linac", position);
        } else if (gamepad2.b) {
            th_linac.queue(linac_down);
            telemetry.addData("Position Linac", position);
        }
        else {
            linac.setPower(0);

        }
    }*/
    public void axon_linac(){
        if (gamepad2.a) {
            th_axon.queue(axon_intake);
            telemetry.addData("Position Axon", axon.getPosition());
            telemetry.update();
        } else if (gamepad2.b) {
            th_axon.queue(axon_outtake);
            telemetry.addData("Position Axon", axon.getPosition());
            telemetry.update();

        }
        else {
            axon.setPosition(.32);
            telemetry.addData("Position Axon", axon.getPosition());
            telemetry.update();


        }
    }
    public void intake(){
        if (gamepad2.right_trigger > .2) {
            th_intake.queue(intake_in);
        } else if (gamepad2.left_trigger > .2) {
            th_intake.queue(intake_out);
        }
        else {
            intake.setPower(0);
            //rollers.setPower(0);
        }
    }
    /*public void tMacro(){
        if(gamepad2.dpad_down){
            th_box.queue(transferMacro);
        }
    }*/
    public void arcadeDrive(){
        if(gamepad1.right_trigger > .5){
            //th_arcadeDrive.queue(half_speed);
            halfToggle = true;
        }
        if(gamepad1.left_trigger > .5) {
            //th_arcadeDrive.queue(full_speed);
            halfToggle= false;
        }
        double left_stick_x = gamepad1.left_stick_x;
        double left_stick_y = gamepad1.left_stick_y;
        double right_stick_x = gamepad1.right_stick_x;

        if(halfToggle && Math.abs(left_stick_x) > 0.1 ||
                Math.abs(left_stick_y) >.1|| Math.abs(right_stick_x) > 0.1) {
            fr.setPower(((left_stick_y + left_stick_x) + right_stick_x));
            fl.setPower(((left_stick_y - left_stick_x) - right_stick_x));
            br.setPower(((left_stick_y - left_stick_x) + right_stick_x));
            bl.setPower(((left_stick_y + left_stick_x) - right_stick_x));
            telemetry.addData("fr:", fr.getPower());
            telemetry.addData("br:", br.getPower());
            telemetry.addData("fl:", fl.getPower());
            telemetry.addData("bl:", bl.getPower());
        } else if (!halfToggle && Math.abs(left_stick_x) > 0.1 ||
                Math.abs(left_stick_y) >.1|| Math.abs(right_stick_x) > 0.1){
            fr.setPower(.5 * ((left_stick_y + left_stick_x) + right_stick_x));
            fl.setPower(.5 * ((left_stick_y - left_stick_x) - right_stick_x));
            br.setPower(.5 * ((left_stick_y - left_stick_x) + right_stick_x));
            bl.setPower(.5 * ((left_stick_y + left_stick_x) - right_stick_x));
            telemetry.addData("fr:", fr.getPower());
            telemetry.addData("br:", br.getPower());
            telemetry.addData("fl:", fl.getPower());
            telemetry.addData("bl:", bl.getPower());

        }
        else{
            fl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
            bl.setPower(0);
            telemetry.addData("fr:", fr.getPower());
            telemetry.addData("br:", br.getPower());
            telemetry.addData("fl:", fl.getPower());
            telemetry.addData("bl:", bl.getPower());
        }
    }

    public void loop(){}

    public void kill(){
        /*horizontalLiftRight.setPower(0);
        horizontalLiftLeft.setPower(0);*/
    }
}