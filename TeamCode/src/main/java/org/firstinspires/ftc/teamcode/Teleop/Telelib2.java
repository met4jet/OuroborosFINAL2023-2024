package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;

public abstract class Telelib2 extends OpMode {

    // Difficulty: EASY
    // All: Create your motors and servos

    boolean halfToggle = false;
    public DcMotor fl;
    HashMap<String, Boolean> buttons = new HashMap<String, Boolean>();
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    public CRServo intake;
    public Servo rflip;
    public Servo lflip;
    public Servo linearAct;
    public Servo shoomShoomDom;
    public Servo shoomShoomSub;
    public Servo box;
    public Servo linac;

    public DcMotor horizontalLiftRight;
    public DcMotor horizontalLiftLeft;
    public DcMotor verticalLiftRight;
    public DcMotor verticalLiftLeft;

    @Override
    public void init(){
        // Difficulty: EASY
        // All: Hardware map your motors and servos

        linac = hardwareMap.get(Servo.class, "linac");

        rflip = hardwareMap.get(Servo.class, "rflip");
        lflip = hardwareMap.get(Servo.class, "lflip");
        intake = hardwareMap.get(CRServo.class, "intake");
        shoomShoomDom = hardwareMap.get(Servo.class, "shoomShoomDom");
        shoomShoomSub = hardwareMap.get(Servo.class, "shoomShoomSub");
        box = hardwareMap.get(Servo.class, "box");

        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        horizontalLiftRight = hardwareMap.get(DcMotor.class, "hlr");
        horizontalLiftLeft = hardwareMap.get(DcMotor.class, "hll");

        verticalLiftRight = hardwareMap.get(DcMotor.class, "vlr");
        verticalLiftLeft = hardwareMap.get(DcMotor.class, "vll");


        // Difficulty: EASY
        // All: Set your motors' zero power behavior
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        horizontalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        verticalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Difficulty: EASY
        // All: Set your motors' directions
        fl.setDirection(DcMotorSimple.Direction.FORWARD); //f fl and br
        bl.setDirection(DcMotorSimple.Direction.FORWARD); //r
        fr.setDirection(DcMotorSimple.Direction.REVERSE); //f
        br.setDirection(DcMotorSimple.Direction.REVERSE); //r

        horizontalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        horizontalLiftLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        verticalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalLiftLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        resetEncoders();
    }

    public void resetEncoders(){
        verticalLiftLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        horizontalLiftRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        horizontalLiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
    public void vertical_lift_right() {
        if (gamepad2.right_stick_y > .2) {
            verticalLiftRight.setPower(-1);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.addData("vRight encoders", verticalLiftRight.getCurrentPosition());
            telemetry.update();
        } else if (gamepad2.right_stick_y < -.2) {
            verticalLiftRight.setPower(1);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.addData("vRight encoders", verticalLiftRight.getCurrentPosition());
            telemetry.update();
        }
        else {
            verticalLiftRight.setPower(0);
            verticalLiftLeft.setPower(0);
        }
    }
    public void vertical_lift_left(){
        if (gamepad2.left_stick_y > .2) {
            verticalLiftLeft.setPower(1);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.addData("vRight encoders", verticalLiftRight.getCurrentPosition());
            telemetry.update();
        }
        else if (gamepad2.left_stick_y < -.2) {
            verticalLiftLeft.setPower(-1);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.addData("vRight encoders", verticalLiftRight.getCurrentPosition());
            telemetry.update();
        }
    }

    public void horizontal_lift(){
        if(gamepad2.left_stick_x > .2) {
            horizontalLiftRight.setPower(.75);
            horizontalLiftLeft.setPower(.75);
            telemetry.addData("EncoderPosLeft: ", horizontalLiftLeft.getCurrentPosition());
            telemetry.addData("LeftPwr: ", horizontalLiftLeft.getPower());
            telemetry.update();
        }
        else if(gamepad2.left_stick_x < -.2// && horizontalLiftLeft.getCurrentPosition() <= -100
        ){
            horizontalLiftRight.setPower(-.75);
            horizontalLiftLeft.setPower(-.75);
            telemetry.addData("EncoderPosLeft: ", horizontalLiftLeft.getCurrentPosition());
            telemetry.addData("LeftPwr: ", horizontalLiftLeft.getPower());
            telemetry.update();
        }
        else{
            horizontalLiftRight.setPower(0);
            horizontalLiftLeft.setPower(0);
        }
    }
    public void bringback(){
        if(gamepad2.dpad_down) {
            while (horizontalLiftLeft.getCurrentPosition() < -20) {
                horizontalLiftRight.setPower(-.75);
                horizontalLiftLeft.setPower(-.75);
            }
            horizontalLiftRight.setPower(0);
            horizontalLiftLeft.setPower(0);
        }
    }

    public void arcadeDrive(){
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

    public void intake(){
        if(gamepad2.left_trigger > .2) {
            intake.setPower(1);
        }
        else if(gamepad2.right_trigger > .2) {
            intake.setPower(-1);
        }
        else{
            intake.setPower(0);
        }
    }
    public void linac(){
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

    public void box(){
        if(isPressed("a", gamepad2.a)) {
            box.setPosition(.62); //down linac
            telemetry.addLine("box:" + box.getPosition());
            //sleep(100);
        }
        else{
            box.setPosition(0);
            telemetry.addLine("box:" + box.getPosition());
            //sleep(100);
        }
    }

    public void rflip(){
        if(gamepad2.right_bumper && rflip.getPosition() != 1){
            rflip.setPosition(1);
        }
        else if(gamepad2.right_bumper) {
            rflip.setPosition(0);
        }
    }
    public void lflip(){
        if(gamepad2.left_bumper && lflip.getPosition() != 1){
            lflip.setPosition(1);
        }
        else if(gamepad2.left_bumper) {
            lflip.setPosition(0);
        }
    }


    public void linearAct(){
        if(gamepad2.right_stick_y > .3){
            linearAct.setPosition(0);
        }
        else if (gamepad2.right_stick_x > .3){
            linearAct.setPosition(1);
        }
    }
    public void shoomShoom(){

        if(isPressed("left_bumper", gamepad1.left_bumper)){
            shoomShoomSub.setPosition(0);
            //sleep(250);
        }
        else{
            shoomShoomSub.setPosition(1);
            //sleep(250);
        }
        if(isPressed("right_bumper", gamepad1.right_bumper)) {
            shoomShoomDom.setPosition(.53); //deliver
            telemetry.addLine("Pos:" + shoomShoomDom.getPosition());
            //sleep(250);
        }
        else {
            shoomShoomDom.setPosition(.2); //resting
            telemetry.addLine("Pos:" + shoomShoomDom.getPosition());
            //sleep(250);
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
        horizontalLiftRight.setPower(0);
        horizontalLiftLeft.setPower(0);
        verticalLiftRight.setPower(0);
        verticalLiftLeft.setPower(0);
    }

}
