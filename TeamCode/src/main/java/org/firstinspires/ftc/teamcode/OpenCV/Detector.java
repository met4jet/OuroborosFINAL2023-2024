package org.firstinspires.ftc.teamcode.OpenCV;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class Detector extends OpenCvPipeline {

    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location{
        FRONT,
        MIDDLE,
        BACK,
        NOT_FOUND
    }

    private Location location;
    static final Rect FRONT_ROI = new Rect(
            new Point(60,35),
            new Point(120, 75));

    static final Rect MIDDLE_ROI = new Rect(
            new Point(60,35),
            new Point(120, 75));
    static final Rect BACK_ROI = new Rect(
            new Point(60,35),
            new Point(120, 75));
    static double PERCENT_COLOR_THRESHOLD = 0.4;
    public Detector(Telemetry t){ telemetry = t;}

    @Override
    public Mat processFrame(Mat input) {

        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(23,50,70);
        Scalar highHSV = new Scalar(32,255,255);

        Core.inRange(mat, lowHSV, highHSV, mat);
        Mat front = mat.submat(FRONT_ROI);
        Mat middle = mat.submat(FRONT_ROI);
        Mat right = mat.submat(FRONT_ROI);

        double frontValue = Core.sumElems(front).val[0]/FRONT_ROI.area()/255;
        double middleValue = Core.sumElems(front).val[0]/MIDDLE_ROI.area()/255;
        double backValue = Core.sumElems(front).val[0]/BACK_ROI.area()/255;
        front.release();

        telemetry.addData("Front raw value", (int) Core.sumElems(front).val[0]);
        telemetry.addData("Middle raw value", (int) Core.sumElems(middle).val[0]);
        telemetry.addData("Right raw value", (int) Core.sumElems(right).val[0]);
        telemetry.addData("Front percentage", Math.round(frontValue * 100) + "%");
        telemetry.addData("Middle percentage", Math.round(middleValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(backValue * 100) + "%");

        boolean pixelFront = frontValue > PERCENT_COLOR_THRESHOLD;
        boolean pixelMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        boolean pixelBack = backValue > PERCENT_COLOR_THRESHOLD;

        if(pixelFront && pixelBack && pixelMiddle){
            location = Location.NOT_FOUND;
            telemetry.addData("Pixel Location", "not found");
        }
        else if (pixelBack && pixelMiddle){
            location = Location.FRONT;
            telemetry.addData("Pixel Location", "front");
        }
        else if (pixelFront && pixelBack){
            location = Location.MIDDLE;
            telemetry.addData("Pixel Location", "middle");
        }
        else {
            location = Location.BACK;
            telemetry.addData("Pixel Location", "back");
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR);

        Scalar noPixel = new Scalar(255,0,0);
        Scalar yesPixel = new Scalar(0,255,0);

        Imgproc.rectangle(mat, FRONT_ROI, location == Location.FRONT? yesPixel:noPixel);
        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.MIDDLE? yesPixel:noPixel);
        Imgproc.rectangle(mat, BACK_ROI, location == Location.BACK? yesPixel:noPixel);

        return mat;
    }

    public Location getLocation(){
        return location;
    }

}