package frc.robot.subsystems;

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase{
    public CANSparkMax rightMaster = new CANSparkMax(Constants.rightMasterID, MotorType.kBrushless);
    public CANSparkMax leftSlave = new CANSparkMax(Constants.leftSlaveID, MotorType.kBrushless);
    public CANSparkMax rightSlave = new CANSparkMax(Constants.rightSlaveID, MotorType.kBrushless);
    public CANSparkMax leftMaster = new CANSparkMax(Constants.leftMasterID, MotorType.kBrushless);
    public static DifferentialDriveKinematics kinematics = 
    new DifferentialDriveKinematics(Units.inchesToMeters(15));
    
    private static Drivetrain m_subsystem;

    public RelativeEncoder rightEncoder;
    public RelativeEncoder leftEncoder; 
    public ShuffleboardTab drivetrain = Shuffleboard.getTab("DriveTrain");
    //public Gyro gyro = new ADXRS450_Gyro();
    //public DifferentialDriveOdometry m_Odometry; =

    public DifferentialDrive drive; 


    public static Drivetrain get(){
        return m_subsystem;
    }
    public Drivetrain(){
        m_subsystem = this;
        leftMaster.setIdleMode(IdleMode.kBrake);
        leftSlave.setIdleMode(IdleMode.kBrake);
        rightMaster.setIdleMode(IdleMode.kBrake);
        rightSlave.setIdleMode(IdleMode.kBrake);
        
        
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftEncoder = leftMaster.getEncoder();
        rightEncoder = rightMaster.getEncoder();
        leftMaster.setInverted(false);
        rightMaster.setInverted(true);
    
        

        // m_Odometry = new DifferentialDriveOdometry(getGyro());
        // m_Odometry.resetPosition(new Pose2d(), getGyro());

        leftEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);
        rightEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);
        leftEncoder.setVelocityConversionFactor(Constants.kEncoderVelocityConversionFactor);
        rightEncoder.setVelocityConversionFactor(Constants.kEncoderVelocityConversionFactor);
        drive = new DifferentialDrive(leftMaster, rightMaster);
        drive.setSafetyEnabled(false);
        drive.setExpiration(0.1);
}
    public void arcadeDrive(double speed, double rotation) 
    {
        drive.arcadeDrive(speed, rotation);
    }
    public void arcadeClean(double speed, double rotation){
        
        setWheelSpeeds(kinematics.toWheelSpeeds(new ChassisSpeeds(speed, 0, rotation)));

    }
    public void setWheelSpeeds(DifferentialDriveWheelSpeeds wheelSpeeds)
    {   
        leftMaster.set(wheelSpeeds.leftMetersPerSecond);
        rightMaster.set(wheelSpeeds.rightMetersPerSecond);
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    public double getLeftEncoderDistance(){
        return leftEncoder.getPosition();
    }

    public double getRightEncoderDistance(){
        return rightEncoder.getPosition();
    }
    @Override
    public void periodic(){
        drivetrain.addNumber("Left Master Pecent Output", ()->leftMaster.getAppliedOutput());
        drivetrain.addNumber("Left Slave Pecent Output", ()->leftSlave.getAppliedOutput());
        drivetrain.addNumber("Right Master Pecent Output", ()->rightMaster.getAppliedOutput());
        drivetrain.addNumber("Right Slave Pecent Output", ()->rightSlave.getAppliedOutput());
    }   
    
    }   
    

  
    

