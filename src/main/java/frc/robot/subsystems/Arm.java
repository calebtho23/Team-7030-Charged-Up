package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase{
    private CANSparkMax ArmMotor= new CANSparkMax(Constants.ArmOutID, MotorType.kBrushed);
    private CANSparkMax adjustMotor = new CANSparkMax(Constants.ArmUpID, MotorType.kBrushed);
    public Arm(){
        ArmMotor.setIdleMode(IdleMode.kBrake);
        adjustMotor.setIdleMode(IdleMode.kBrake);
        adjustMotor.setSmartCurrentLimit(35);
        ArmMotor.setSmartCurrentLimit(35);
    }

    public void ArmIn()
    {
        ArmMotor.set(0.8);
    }
    public void HoldArm(){
        ArmMotor.set(0);
        adjustMotor.set(0);
    }
    public void StayPut(){
        adjustMotor.set(0);
    }
    public void ArmOut(){
        ArmMotor.set(-0.8);
    }
    public void ArmUp()
    {
        adjustMotor.set(0.2);
    }
    public void ArmDown(){
        adjustMotor.set(-0.2);
    }
}

