package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "tanknoduckautofar")
public class tanknoduckautofar extends LinearOpMode {

   /**
    * Amount of time elapsed
    */
   private ElapsedTime runtime = new ElapsedTime();

   MecanumRobot rb = new MecanumRobot();

   public static void resetEncoder(DcMotor motor) {
       motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
   }

   @Override
   public void runOpMode() throws InterruptedException {
       telemetry.addData("Status", "Initializing");

       rb.init(hardwareMap, this);

       rb.flMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rb.frMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rb.blMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
       rb.brMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

       resetEncoder(rb.flMotor);

       telemetry.addData("Status", "Initialized");

       telemetry.update();

       waitForStart();

       runtime.reset();
//       encoder auto
//       rb.driveForwardByEncoder(50, rb.flMotor, 1);

       // below is the hard-coded auto
       driveForwardByTime(300, -1);
       duckByTime(1000, 0.5);
       turnClockWiseByTime(800,0.5);
       driveForwardByTime(1000, 1);
       turnClockWiseByTime(-800, 0.5);
       driveForwardByTime(3000, 1);
   }

    void driveForwardByTime(double milliseconds, double power) {
        double currTime = getRuntime();
        double waitUntil = currTime + (double)(milliseconds/1000);
        while (getRuntime() < waitUntil){
            rb.driveForward(power);
        }
        rb.driveStop();
    }

    void turnClockWiseByTime(double milliseconds, double power) {
        double currTime = getRuntime();
        double waitUntil = currTime + (double)(milliseconds/1000);
        while (getRuntime() < waitUntil){
            rb.turnClockwise(power);
        }
        rb.driveStop();
    }

    void duckByTime(double milliseconds, double power) {
        double currTime = getRuntime();
        double waitUntil = currTime + (double)(milliseconds/1000);
        while (getRuntime() < waitUntil){
            rb.duckmotor.setPower(power);
        }
        rb.duckmotor.setPower(0);
    }
}

