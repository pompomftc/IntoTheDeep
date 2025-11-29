package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class RobotHardware {
    /* Declare OpMode members. */
    private final LinearOpMode myOpMode;   // gain access to methods in the calling OpMode.
    public DcMotor leftF,leftB,rightF,rightB;

    public Servo claw, armL, armR;
//    public Servo bucketServo = null;
//    public CRServo intakeServo = null;

    public DcMotorEx liftL,liftR = null;
    //5203 series, 384.5 ppr - encoder resolution
    //5204-08139 series, 3895.9 resolution, for other arm thing motor
//    public DcMotorEx armMotor = null;
    public IMU imu = null;

    // Define a constructor that allows the OpMode to pass a reference to itself.
    public RobotHardware(LinearOpMode opMode) {
        myOpMode = opMode;
    }

    public void init() {
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftF = myOpMode.hardwareMap.get(DcMotor.class, "leftF");
        leftB= myOpMode.hardwareMap.get(DcMotor.class, "leftB");
        rightF = myOpMode.hardwareMap.get(DcMotor.class, "rightF");
        rightB = myOpMode.hardwareMap.get(DcMotor.class, "rightB");

        liftR = myOpMode.hardwareMap.get(DcMotorEx.class, "liftR");
        liftL = myOpMode.hardwareMap.get(DcMotorEx.class, "liftL");

        liftR.setDirection(DcMotor.Direction.FORWARD);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//        armMotor = myOpMode.hardwareMap.get(DcMotorEx.class, "armMotor");
//        armMotor.setDirection(DcMotor.Direction.FORWARD);
//        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftF.setDirection(DcMotor.Direction.FORWARD);
        leftB.setDirection(DcMotor.Direction.FORWARD);
        rightF.setDirection(DcMotor.Direction.REVERSE);
        rightB.setDirection(DcMotor.Direction.REVERSE);

//        imu = myOpMode.hardwareMap.get(IMU.class, "imu");
        claw = myOpMode.hardwareMap.get(Servo.class,"claw");
        armL = myOpMode.hardwareMap.get(Servo.class,"armL");
        armR = myOpMode.hardwareMap.get(Servo.class,"armR");
//        bucketServo = myOpMode.hardwareMap.get(Servo.class,"bucketServo");
//        intakeServo = myOpMode.hardwareMap.get(CRServo.class,"intakeServo");

    }

    /**
     * Move robot according to desired axes motions
     * <p>
     * Positive X is forward
     * <p>
     * Positive Y is strafe left
     * <p>
     * Positive Yaw is counter-clockwise
     */
    public void moveRobot(double lx, double ly, double rx, double power, boolean toggleDirection) {
        double leftFrontPower = 0, leftBackPower = 0, rightFrontPower = 0, rightBackPower = 0;
        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(ly)+Math.abs(lx)+Math.abs(rx), 1);

        // Calculate wheel powers and toggle it!
        if (!toggleDirection)
        {
            leftFrontPower = ((ly-lx+rx)/max)*power;
            leftBackPower = ((ly+lx+rx)/max)*power;
            rightFrontPower = ((ly+lx-rx)/max)*power;
            rightBackPower = ((ly-lx-rx)/max)*power;
        }
        if (toggleDirection)
        {
            leftFrontPower = ((-ly+lx+rx)/max)*power;
            leftBackPower = ((-ly-lx+rx)/max)*power;
            rightFrontPower = ((-ly-lx-rx)/max)*power;
            rightBackPower = ((-ly+lx-rx)/max)*power;
        }

        // Send powers to the wheels.
        leftF.setPower(leftFrontPower);
        rightF.setPower(rightFrontPower);
        leftB.setPower(leftBackPower);
        rightB.setPower(rightBackPower);
    }
}
