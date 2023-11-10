package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VerticalLift {
    public LinearOpMode opMode;
    public DcMotor verticalLiftRight;
    public DcMotor verticalLiftLeft;

    public double kp, ki, kd;
    public double a;
    public double prevP;
    public double target;
    public ElapsedTime timer = new ElapsedTime();

    public VerticalLift(LinearOpMode opMode){
        this.opMode = opMode;
        verticalLiftRight = opMode.hardwareMap.get(DcMotor.class, "vlr");
        verticalLiftLeft = opMode.hardwareMap.get(DcMotor.class, "vll");

        verticalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
        verticalLiftLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetEncoders(){
        verticalLiftRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        verticalLiftLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void moveEncoderLiftDown(double distance){
        resetEncoders(); // negative

        while (verticalLiftRight.getCurrentPosition() > distance && opMode.opModeIsActive()){
            verticalLiftRight.setPower(-1);
            verticalLiftLeft.setPower(-1);
            opMode.telemetry.addLine("verticalLiftRight pos:" + verticalLiftRight.getCurrentPosition());
        }
        verticalLiftRight.setPower(0);
        verticalLiftLeft.setPower(0);
    }

    public void moveEncoderLiftUp(double distance){
        resetEncoders(); // positive

        while (verticalLiftRight.getCurrentPosition() < distance && opMode.opModeIsActive()){
            verticalLiftRight.setPower(1);
            verticalLiftLeft.setPower(1);
            opMode.telemetry.addLine("verticalLiftRight pos:" + verticalLiftRight.getCurrentPosition());
        }
        verticalLiftRight.setPower(0);
        verticalLiftLeft.setPower(0);
    }

    public void movePID(int position, double kp, double ki, double kd){
        if(position != 0) {
            resetEncoders();
        }

        ElapsedTime timer = new ElapsedTime();

        double p;
        double i = 0;
        double d;
        double a = .5;
        double prevP = 0;
        double power;
        double iLimit = .25/ki;

        double currFilterEst;
        double prevFilterEst = 0;

        int currPos = verticalLiftLeft.getCurrentPosition();

        timer.reset();

        while(Math.abs(verticalLiftLeft.getCurrentPosition() - position) > 10 && opMode.opModeIsActive()){

            p = verticalLiftLeft.getCurrentPosition() - position;

            i += (p - prevP) * timer.seconds();

            if (Math.abs(i) > iLimit) {
                i = Math.signum(i) * iLimit;
            }

            currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
            prevFilterEst = currFilterEst;
            d = currFilterEst/timer.seconds();
            power = p * kp + i * ki + d * kd;
            verticalLiftLeft.setPower(power);
            verticalLiftRight.setPower(power);

            prevP = p;

            timer.reset();

            opMode.telemetry.addData("p :: ", p * kp);
            opMode.telemetry.addData("i :: ", i * ki);
            opMode.telemetry.addData("d :: ", d * kd);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.update();
        }
    }
}
