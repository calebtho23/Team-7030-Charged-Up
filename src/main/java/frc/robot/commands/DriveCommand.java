// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  public Drivetrain drivetrain;
  public DoubleSupplier m_rotation; 
  public SlewRateLimiter xRateLimiter;
  public SlewRateLimiter rotationLimiter;
  public DoubleSupplier m_speed;

  public DriveCommand(Drivetrain subsystem, DoubleSupplier speed, DoubleSupplier rotation){
      drivetrain = subsystem;
      m_speed = speed;
      m_rotation = rotation;
      

      addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.arcadeClean(m_speed.getAsDouble()*0.8, m_rotation.getAsDouble()*0.8);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.x`
  @Override
  public boolean isFinished() {
    return false;
  }
}
