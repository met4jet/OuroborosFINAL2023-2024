package org.firstinspires.ftc.teamcode.Auto;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vision {
    LinearOpMode opMode;
    Servo stop;
    Servo wrist;
    public static final String vuKey =
            "AdzMYbL/////AAABmflzIV+frU0RltL/ML+2uAZXgJiI" +
                    "Werfe92N/AeH7QsWCOQqyKa2G+tUDcgvg8uE8QjHeBZPcpf5hAwlC5qCfvg76eBoaa2b" +
                    "MMZ73hmTiHmr9fj3XmF4LWWZtDC6pWTFrzRAUguhlvgnck6Y4jjM16Px5TqgWYuWnpcxNM" +
                    "HMyOXdnHLlyysyE64PVzoN7hgMXgbi2K8+pmTXvpV2OeLCag8fAj1Tgdm/kKGr0TX86aQsC2" +
                    "RVjToZXr9QyAeyODi4l1KEFmGwxEoteNU8yqNbBGkPXGh/+IIm6/s/KxCJegg8qhxZDgO8110F" +
                    "RzwA5a6EltfxAMmtO0G8BB9SSkApxkcSzpyI0k2LxWof2YZG6x4H";
    public VuforiaLocalizer vuforia;

    public Vision(LinearOpMode opMode) {
        this.opMode = opMode;
        stop = opMode.hardwareMap.servo.get("stop");
        wrist = opMode.hardwareMap.servo.get("wrist");
        // variable allows image to show up on robot controller phone
        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id",
                        opMode.hardwareMap.appContext.getPackageName());

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // original vuforia license key
        parameters.vuforiaLicenseKey = vuKey;
        // hardware mapping of webcam device
        parameters.cameraName = opMode.hardwareMap.get(WebcamName.class, "Webcam 1");

        // start vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // set RGB format to 565
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        // allowing the frame to only be 4 images at a time
        vuforia.setFrameQueueCapacity(1);
        wrist.setPosition(1);
        stop.setPosition(0);
        stop.setPosition(1);
        //opMode.telemetry.addData("stop position: ",stop.getPosition());
        opMode.telemetry.addLine("Vision init");
        opMode.telemetry.update();
    }

    public Bitmap getBitmap() throws InterruptedException {
        // method to actually capture frame
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        Image rgb = frame.getImage(1);

        long numImages = frame.getNumImages();

        // go through all images taken in frame and find ones that match correct format
        for (int i = 0; i < numImages; i++) {
            int fmt = frame.getImage(i).getFormat();

            if (fmt == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i);
                break;

            } else {
                opMode.telemetry.addLine("Didn't find correct rgb format");
                opMode.telemetry.update();

            }

        }

        // create bitmap
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        frame.close();

        //opMode.telemetry.addLine("Got Bitmap");
        //opMode.telemetry.addData("width", rgb.getWidth());
        //opMode.telemetry.addData("height", rgb.getHeight());
        //opMode.telemetry.update();

        //opMode.sleep(500);

        return bm;
    }

    public int sense() throws InterruptedException {

        double total = 0;
        double average = 0;

        ArrayList<Integer> list = new ArrayList<>();
        Bitmap bitmap = getBitmap();
            bitmap.getHeight();
            bitmap.getWidth();
            bitmap.getPixel(50,50);
        for (int y = 50; y < bitmap.getHeight(); y += 10){
            for (int x = 0; x < bitmap.getWidth(); x++ ){
                if (red(bitmap.getPixel(x,y)) < 20 && blue(bitmap.getPixel(x,y)) < 20 && green(bitmap.getPixel(x,y)) < 20){
                    list.add(y);
                }
            }
        }

        for(int i = 0; i < list.size(); i++){
            total += list.get(i);
        }

        average = total/list.size();

        if (average < 100){
            return 1;
        } else if (average > 200) {
            return 3;
        } else {
            return 2;
        }

    }

}
