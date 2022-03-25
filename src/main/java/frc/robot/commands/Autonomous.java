package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.RunDrive;

import frc.robot.subsystems.DriveTrain;

public class Autonomous extends SequentialCommandGroup {
    DriveTrain m_robotTrain = new DriveTrain();
    public Autonomous()
    {
        addCommands(new RunDrive(m_robotTrain, 0.0));
    }
}
