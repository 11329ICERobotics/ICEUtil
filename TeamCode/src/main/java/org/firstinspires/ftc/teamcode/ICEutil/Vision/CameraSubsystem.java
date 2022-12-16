package org.firstinspires.ftc.teamcode.ICEutil.Vision;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class CameraSubsystem {
    public OpenCvWebcam webcam;
    public int streamWidth = 640;
    public int streamHeight = 360;

    public CameraSubsystem(HardwareMap hardwareMap) {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext
                .getResources()
                .getIdentifier(
                        "cameraMonitorViewId",
                        "id",
                        hardwareMap.appContext.getPackageName()
                );
        webcam =
                OpenCvCameraFactory
                        .getInstance()
                        .createWebcam(webcamName, cameraMonitorViewId);
        webcam.openCameraDeviceAsync(
                new OpenCvCamera.AsyncCameraOpenListener() {
                    public void onOpened() {
                        webcam.startStreaming(
                                streamWidth,
                                streamHeight,
                                OpenCvCameraRotation.SIDEWAYS_RIGHT
                        );
                    }

                    public void onError(int errorCode) {
                        //pew pew pew go brrrrrr
                    }
                }
        );
    }

    //public void openDashboardStream() {FtcDashboard.getInstance().startCameraStream(webcam, 0);}

    //public void closeDashboardStream() {FtcDashboard.getInstance().stopCameraStream();}

    public void setPipeline(OpenCvPipeline pipeline) {
        webcam.setPipeline(pipeline);
    }

    public void close() {
        webcam.closeCameraDevice();
    }
}
