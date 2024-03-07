package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public abstract class Distance extends OpMode {
    //public LinearOpMode opMode;
    public ColorRangeSensor colorSensor;
    public void init(){
        ///this.opMode = opMode;
        colorSensor = hardwareMap.get(ColorRangeSensor.class, "color");
    }
    public void test(){
        telemetry.addData("DISTANCE :: ", colorSensor.getDistance(DistanceUnit.INCH));
        telemetry.update();
    }
}
