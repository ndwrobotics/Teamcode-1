package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="autonR", group="")
//@Disabled
public class auto_2 extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor back;
    private DcMotor front;
    private DcMotor intake;
    private DcMotor spin;

    Servo servo;

    private ElapsedTime runtime = new ElapsedTime();

    boolean down = false;

    @Override
    public void runOpMode() {

        servo = hardwareMap.servo.get("drop");

        left  = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        back = hardwareMap.get(DcMotor.class, "back");
        front = hardwareMap.get(DcMotor.class, "front");
        intake = hardwareMap.get(DcMotor.class, "intake");
        spin = hardwareMap.get(DcMotor.class, "spin");

        left.setDirection(DcMotorSimple.Direction.REVERSE);
        front.setDirection(DcMotorSimple.Direction.REVERSE);

        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


        driv(-0.5, 0, 0, 3);

        sleep(100);

        driv(0, -0.5, 0, 1);

        sleep(100);

        drop(true);

        sleep(100);

        driv(0, 0.5, 0, 1);




        telemetry.addData("pos", runtime);

        telemetry.update();



    }

    public void driv(double speedx, double speedy, double speedt, double time) {

        left.setPower((-speedt)+(-speedx)+(speedy));
        right.setPower((speedt)+(-speedx)+(speedy));
        back.setPower((speedt)+(speedx)+(speedy));
        front.setPower((-speedt)+(speedx)+(speedy));

        runtime.reset();

        while ( runtime.seconds() < time && opModeIsActive() ) {
                sleep(1);
        }

        left.setPower(0);
        right.setPower(0);
        back.setPower(0);
        front.setPower(0);

    }

    public void drop(boolean dropped) {
        if (!dropped) {
            servo.setPosition(0);
        } if (dropped) {
            servo.setPosition(1);
        }
        sleep(100);
    }

}
