// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int leftMasterID = 1; 
    public static final int leftSlaveID = 2; 
    public static final int rightMasterID = 3; 
    public static final int rightSlaveID = 4; 
    public static final int ArmOutID = 5;
    public static final int ArmUpID = 6;
    public static final int Turret = 7;
    public static final int Claw = 8;
    public static final double TrackWidth = Units.inchesToMeters(27); 
    public static final double WheelBase = Units.inchesToMeters(32);
    public static final double kWheelDiameter = Units.inchesToMeters(6); 
    public static final double kGearRatio = 12.75; 

    public static final double kEncoderPositionConversionFactor = (1/kGearRatio) * Math.PI * kWheelDiameter;
    public static final double kEncoderVelocityConversionFactor = kEncoderPositionConversionFactor / 60.0;

}
