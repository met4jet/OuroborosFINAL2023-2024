package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class TrapMotionProfileRight {
    public LinearOpMode opMode;
    public DcMotor verticalLiftRight;

    // ---------- TRAP METHOD VARIABLES -----------
    public double acceleration_dt;
    public double halfway_distance;
    public double acceleration_distance;
    public double deceleration_dt;
    public double cruise_distance;
    public double cruise_dt;
    public double deceleration_time;
    public double entire_dt;
    public double cruise_current_dt;

    // --------- PID METHOD VARIABLES ----------
    public ElapsedTime timerPID = new ElapsedTime();
    public double p;
    public double i = 0;
    public double d;
    public double a = .5;
    public double prevP = 0;
    public double power;
    public double iLimit = .25/.005; // ki = .005

    public double currFilterEst;
    public double prevFilterEst = 0;

    public TrapMotionProfileRight(LinearOpMode opMode){
        this.opMode = opMode;
        verticalLiftRight = opMode.hardwareMap.get(DcMotor.class, "vlr");

        verticalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftRight.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void resetEncoders(){
        verticalLiftRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private double motion_profile_position(double max_acceleration, double max_velocity, double distance, double elapsed_time) {
        //Return the current reference position based on the given motion profile times, maximum acceleration, velocity, and current time.

        // calculate the time it takes to accelerate to max velocity
        acceleration_dt = max_velocity / max_acceleration;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        halfway_distance = distance / 2;
        acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_dt,2);

        if (acceleration_distance > halfway_distance)
            acceleration_dt = Math.sqrt(halfway_distance / (0.5 * max_acceleration));

        acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_dt,2);

        // recalculate max velocity based on the time we have to accelerate and decelerate
        max_velocity = max_acceleration * acceleration_dt;

        // we decelerate at the same rate as we accelerate
        deceleration_dt = acceleration_dt;

        // calculate the time that we're at max velocity
        cruise_distance = distance - 2 * acceleration_distance;
        cruise_dt = cruise_distance / max_velocity;
        deceleration_time = acceleration_dt + cruise_dt;

        // check if we're still in the motion profile
        entire_dt = acceleration_dt + cruise_dt + deceleration_dt;
        if (elapsed_time > entire_dt)
            return distance;

        // if we're accelerating
        if (elapsed_time < acceleration_dt) {
            // use the kinematic equation for acceleration
            opMode.telemetry.addData("accel", 0.5 * max_acceleration * Math.pow(elapsed_time, 2));
            opMode.telemetry.update();
            return 0.5 * max_acceleration * Math.pow(elapsed_time, 2);
        }

            // if we're cruising
        else if (elapsed_time < deceleration_time) {
            acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_dt, 2);
            cruise_current_dt = elapsed_time - acceleration_dt;
            opMode.telemetry.addData("cruise", acceleration_distance + max_velocity * cruise_current_dt);
            opMode.telemetry.update();

            // use the kinematic equation for constant velocity
            return acceleration_distance + max_velocity * cruise_current_dt;
        }

        // if we're decelerating
        else {
            acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_dt, 2);
            cruise_distance = max_velocity * cruise_dt;
            deceleration_time = elapsed_time - deceleration_time;
            opMode.telemetry.addData("decel", acceleration_distance + cruise_distance + max_velocity * deceleration_time - 0.5 * max_acceleration * Math.pow(deceleration_time,2));
            opMode.telemetry.update();

            // use the kinematic equations to calculate the instantaneous desired position
            return acceleration_distance + cruise_distance + max_velocity * deceleration_time - 0.5 * max_acceleration * Math.pow(deceleration_time,2);
        }
    }

    private double movePIDRight(double position, double kp, double ki, double kd){

        timerPID.reset();

        p = verticalLiftRight.getCurrentPosition() - position;

        i += (p - prevP) * timerPID.seconds();

        if (Math.abs(i) > iLimit) {
            i = Math.signum(i) * iLimit;
        }

        currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
        prevFilterEst = currFilterEst;
        d = currFilterEst/timerPID.seconds();
        power = p * kp + i * ki + d * kd;

        prevP = p;

        timerPID.reset();

        opMode.telemetry.addLine("right lift pos:" + verticalLiftRight.getCurrentPosition());
        opMode.telemetry.update();

        return power;

    }

    public void motion_profile_PID(double max_acceleration, double max_velocity, double distance) {

        resetEncoders();

        ElapsedTime elapsed_time = new ElapsedTime();

        while(Math.abs(Math.abs(verticalLiftRight.getCurrentPosition()) - distance) > 5 && opMode.opModeIsActive()){
            double instantTargetPosition = motion_profile_position(max_acceleration, max_velocity, distance, elapsed_time.seconds());

            double motorPower = movePIDRight(instantTargetPosition, 0.0045, 0.0053, 0.0005);

            verticalLiftRight.setPower(motorPower);
        }
    }
}