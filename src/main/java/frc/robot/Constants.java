// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveTrainConstants {
        public static final int kleftMasterID = 01;
        public static final int krightMasterID = 31;
        public static final int kleftSlaveID = 21;
        public static final int krightSlaveID = 30;

        // public static final int kshifterChannel = 0;
        
        public static final int kmotorCurrentLimit = 35;
    }

    public static final class IntakeRollersConstants {
        public static final int kRollersID = 0;
        public static final int kIntakeSpeed = 50;
        public static final int kdeployChannel = 1;

    }

    public static final class ConveyerConstants
    {
        public static final int kConveyerID = 10000;
        public static final int kconveyerSpeed = 50;
    }

    public static final class FlywheelConstants
    {
        public static final int kFlywheelID = 10000;
        public static final int kFlywheelSpeed = 50;
    }

      
}
