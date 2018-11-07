package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="driv2", group="")
//@Disabled
public class opMode2 extends LinearOpMode {

    private DcMotor left;
    private DcMotor right;
    private DcMotor back;
    private DcMotor front;
    private DcMotor intake;
    private DcMotor spin;

    private ElapsedTime runtime = new ElapsedTime();

    boolean down = false;

    @Override
    public void runOpMode() {

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

        while (opModeIsActive()) {


            double yr = -gamepad1.right_stick_y;
            double xr = gamepad1.right_stick_x;
            double xl = -gamepad1.left_stick_x;

                if (Math.abs(yr)>0) {

                    left.setPower(yr);
                    right.setPower(yr);
                    back.setPower(yr);
                    front.setPower(yr);

                } else if (Math.abs(xr)>0) {

                    left.setPower(-xr);
                    right.setPower(-xr);
                    back.setPower(xr);
                    front.setPower(xr);

                } else if ( Math.abs(xl)>0 ) {
                    left.setPower(-xl*0.5);
                    right.setPower(xl*0.5);
                    back.setPower(xl*0.5);
                    front.setPower(-xl*0.5);
                } else {
                    left.setPower(0);
                    right.setPower(0);
                    back.setPower(0);
                    front.setPower(0);
                }



            if(gamepad2.right_stick_y>0.5){
                spin.setPower(1000);
            } else if (gamepad2.right_stick_y<-0.5) {
                spin.setPower(-1000);
            } else {
                spin.setPower(0);
            }

            runtime.reset();

            if(gamepad2.dpad_up&&down == true){
                while (opModeIsActive()&&intake.getCurrentPosition()<0) {
                    intake.setPower(0.5);
                    intake.setTargetPosition(0);
                    down = false;
                }
            } else if (gamepad2.dpad_down&&down == false) {
                while (opModeIsActive()&&intake.getCurrentPosition()>-1100) {
                    intake.setPower(-0.5);
                    intake.setTargetPosition(-1100);
                    down = true;
                }
            } else {
                intake.setPower(0);
            }

            telemetry.addData("pos", intake.getCurrentPosition());

            telemetry.update();

        }
    }
}
