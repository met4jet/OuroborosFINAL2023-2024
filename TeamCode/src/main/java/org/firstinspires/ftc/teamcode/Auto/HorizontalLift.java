package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HorizontalLift {
    public LinearOpMode opMode;
    public DcMotor hLift;

    public double kp, ki, kd;
    public double a;
    public double prevP;
    public double target;
    public ElapsedTime timer = new ElapsedTime();

    public double motion_profile(double maxAcc, double maxVelocity, double  distance, double elapsedTime){
        ElapsedTime time = new ElapsedTime();;
        time.reset();

        double posAcc = maxVelocity/maxAcc;

        double accDist = .5*maxAcc*(posAcc * posAcc);

        if(accDist > .5 * distance)
        {
            posAcc = Math.sqrt((0.5*distance) / (.5*maxAcc));
        }

        accDist = .5 *maxAcc * (posAcc * posAcc);
        maxVelocity = maxAcc * posAcc;

        double cruise_distance = distance - 2 * accDist;
        double cruise = cruise_distance/maxVelocity;
        double negAcc_time = posAcc + cruise;

        double entire = 2*posAcc + cruise;

        if(elapsedTime > entire){
            return distance;
        }
        else if(elapsedTime < posAcc){
            return .5 * maxAcc * (elapsedTime * elapsedTime);
        }
        else if(elapsedTime < negAcc_time){
            accDist = .5 * maxAcc * (posAcc * posAcc);
            double cruise_current = elapsedTime - accDist;

            return accDist + maxVelocity * cruise_current;
        }

        else{
         accDist = .5*maxAcc*(posAcc * posAcc);
         cruise_distance = maxVelocity* cruise;
         negAcc_time = elapsedTime - negAcc_time;

         return accDist + cruise_distance + maxVelocity * negAcc_time - 0.5 * maxAcc * (negAcc_time * negAcc_time);
        }
    }

    public void HorizontalLift(LinearOpMode opMode){
        this.opMode = opMode;
        hLift = opMode.hardwareMap.dcMotor.get("hLift");
        opMode.telemetry.addLine("hlift :))))))))))))))");
        hLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void resetEncoders(){
        hLift.setMode(DcMotor.RunMode.RESET_ENCODERS);
        hLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void hLiftPID(int position, double kp, double ki, double kd){
        this.opMode = opMode;

        hLift = opMode.hardwareMap.dcMotor.get("lift");

        opMode.telemetry.addLine("lift init");

        //Set your motor's zero power behavior
        hLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.target = target;
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.a = a;
    }
}
