// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.time.Instant;
import java.time.Duration;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveCommand extends CommandBase {

  private Instant m_startTime;
  private double m_driveSpeed, m_driveTime;
  /** Creates a new AutoDrive. 
  *Drives at speed(%output), for set time(millis)
  */
  public AutoDriveCommand(double speed, double time) {
    m_driveSpeed = speed;
    m_driveTime = time;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Drivetrain.get());
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_startTime == null){
      m_startTime = Instant.now();
    }
    Drivetrain.get().tankDrive(m_driveSpeed, m_driveSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Drivetrain.get().tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Duration.between(m_startTime, Instant.now()).toMillis() > m_driveTime;
  }
}
