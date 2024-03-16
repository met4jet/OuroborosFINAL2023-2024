package org.firstinspires.ftc.teamcode.Auto.State;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;

@Autonomous(group = "Auto", name = "IntakeTest")
public class IntakeTest extends LinearOpMode {
    public ColorRangeSensor colorSensor;

    public void runOpMode() throws InterruptedException {
        waitForStart();
        Intake intake = new Intake(this);
        intake.getWhite2new(30);
    }
}
