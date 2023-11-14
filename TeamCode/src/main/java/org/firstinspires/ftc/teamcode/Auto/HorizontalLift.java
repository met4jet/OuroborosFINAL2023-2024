package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HorizontalLift {
    public LinearOpMode opMode;
    public DcMotor hLift;
    public DcMotor hLift2;

    public double kp, ki, kd;
    public double a;
    public double prevP;
    public double target;
    public ElapsedTime timer = new ElapsedTime();

    public HorizontalLift(LinearOpMode opMode){
        this.opMode = opMode;
        hLift = opMode.hardwareMap.dcMotor.get("hLift");
        hLift2 = opMode.hardwareMap.dcMotor.get("hLift2");

        opMode.telemetry.addLine("hlift :))))))))))))))");
        hLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    double motion_profile(double max_acceleration, double max_velocity, int distance, double elapsed_time) {

        // calculate the time it takes to accelerate to max velocity
        double acceleration_dt = max_velocity / max_acceleration;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfway_distance = distance / 2;
        double acceleration_distance = 0.5 * max_acceleration * acceleration_dt * acceleration_dt;

        if (acceleration_distance > halfway_distance)
            acceleration_dt = Math.sqrt(halfway_distance / (0.5 * max_acceleration));

        acceleration_distance = 0.5 * max_acceleration * acceleration_dt * acceleration_dt;

        // recalculate max velocity based on the time we have to accelerate and decelerate
        max_velocity = max_acceleration * acceleration_dt;

        // we decelerate at the same rate as we accelerate
        double deacceleration_dt = acceleration_dt;

        // calculate the time that we're at max velocity
        double cruise_distance = distance - 2 * acceleration_distance;
        double cruise_dt = cruise_distance / max_velocity;
        double deacceleration_time = acceleration_dt + cruise_dt;

        // check if we're still in the motion profile
        double entire_dt = acceleration_dt + cruise_dt + deacceleration_dt;
        if (elapsed_time > entire_dt)
            return distance;

        // if we're accelerating
        if (elapsed_time < acceleration_dt) {
            // use the kinematic equation for acceleration
            return 0.5 * max_acceleration * elapsed_time * elapsed_time;
        }
        // if we're cruising
        else if (elapsed_time < deacceleration_time) {
            acceleration_distance = 0.5 * max_acceleration * acceleration_dt * acceleration_dt;
            double cruise_current_dt = elapsed_time - acceleration_dt;

            // use the kinematic equation for constant velocity
            return acceleration_distance + max_velocity * cruise_current_dt;
        }

        // if we're decelerating
        else {
            acceleration_distance = 0.5 * max_acceleration * acceleration_dt *acceleration_dt;
            cruise_distance = max_velocity * cruise_dt;
            deacceleration_time = elapsed_time - deacceleration_time;

            // use the kinematic equations to calculate the instantaneous desired position
            return acceleration_distance + cruise_distance + max_velocity * deacceleration_time - 0.5 * max_acceleration * deacceleration_time * deacceleration_dt;
        }
    }


    public void resetEncoders(){
        hLift.setMode(DcMotor.RunMode.RESET_ENCODERS);
        hLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

        int currPos = hLift.getCurrentPosition();
        double targetPosition = motion_profile(10,10,hLift.getCurrentPosition(), timer.time());

        timer.reset();

        while(Math.abs(hLift.getCurrentPosition() - position) > 10 && opMode.opModeIsActive()){

            p = hLift.getCurrentPosition() - targetPosition;

            i += (p - prevP) * timer.seconds();

            if (Math.abs(i) > iLimit) {
                i = Math.signum(i) * iLimit;
            }

            currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
            prevFilterEst = currFilterEst;
            d = currFilterEst/timer.seconds();
            power = p * kp + i * ki + d * kd;
            hLift.setPower(power);
            hLift2.setPower(power);

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
