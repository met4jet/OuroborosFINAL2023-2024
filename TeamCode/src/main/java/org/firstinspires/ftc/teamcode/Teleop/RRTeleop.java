package org.firstinspires.ftc.teamcode.Teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ThreadHandler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RRTeleop extends LinearOpMode {
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
    public void runOpMode() {
        // Insert whatever initialization your own code does
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
        // Assuming you're using StandardTrackingWheelLocalizer.java
        // Switch this class to something else (Like TwoWheeTrackingLocalizer.java) if your configuration is different
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        // Set your initial pose to x: 10, y: 10, facing 90 degrees
        drive.setPoseEstimate(new Pose2d(10, 10, Math.toRadians(90)));

        waitForStart();

        while(opModeIsActive()) {
            // Make sure to call drive.update() on *every* loop
            // Increasing loop time by utilizing bulk reads and minimizing writes will increase your odometry accuracy
            drive.update();

            // Retrieve your pose
            Pose2d myPose = drive.getPoseEstimate();

            telemetry.addData("x", myPose.getX());
            telemetry.addData("y", myPose.getY());
            telemetry.addData("heading", myPose.getHeading());

            // Insert whatever teleop code you're using
            ThreadTeleOp tele = new ThreadTeleOp();
            tele.horizontal_lift();
            tele.shoomShoom();
            tele.rflip();
            tele.lflip();
            tele.vertical_lift_left();
            tele.vertical_lift_right();
            tele.drone();
            tele.box();
            tele.linac();
            tele.intake();
            tele.arcadeDrive();
            tele.tMacro();
        }
    }
}