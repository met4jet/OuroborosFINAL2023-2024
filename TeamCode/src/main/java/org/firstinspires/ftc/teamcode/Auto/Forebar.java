package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Forebar {

    public LinearOpMode opMode;
    public DcMotor forebar;

    public Forebar(LinearOpMode opMode){
        // Sophia
        this.opMode = opMode;
        forebar = opMode.hardwareMap.dcMotor.get("forebar");
        opMode.telemetry.addLine("forebar init");
        forebar.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void resetEncoders(){
        // Sophia

        forebar.setMode(DcMotor.RunMode.RESET_ENCODERS);
        forebar.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void moveForebar(double targetPosition, double kCos, double kp, double ki, double kd){

        resetEncoders();

        double angle = 1; // get angle

        double position = forebar.getCurrentPosition();

        ElapsedTime timer = new ElapsedTime();

        double p;
        double i = 0;
        double d;
        double a = .8; // need to tune, can be anything from 0 < a < 1
        double prevP = 0;
        double power;
        double iLimit = .25/ki;

        double currFilterEst;
        double prevFilterEst = 0;

        timer.reset();

        while(Math.abs(forebar.getCurrentPosition() - position) > 10 && opMode.opModeIsActive()){

            p = forebar.getCurrentPosition() - targetPosition;
            angle = forebar.getCurrentPosition() * 0/* conversion*/; // make sure to convert to radians

            i += (p - prevP) * timer.seconds();

            if (Math.abs(i) > iLimit) {
                i = Math.signum(i) * iLimit;
            }

            currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
            prevFilterEst = currFilterEst;

            d = currFilterEst/timer.seconds();

            power = p * kp + i * ki + d * kd;
            power += Math.cos(angle) * kCos;
            forebar.setPower(power);

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
