package frc.robot.subsystems;


import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.FlywheelConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel {
    private CANSparkMax leadFlywheel = new CANSparkMax(FlywheelConstants.kLeftFlywheelID, MotorType.kBrushless);
    private CANSparkMax followFlywheel = new CANSparkMax(FlywheelConstants.kRightFlywheelID, MotorType.kBrushless);
  public static enum FlywheelStates {
    OFF, IN, OUT
  }

  private FlywheelStates state = FlywheelStates.OFF;

  /**
   * Creates a new IntakeRollers.
   */
  public Flywheel() {
    //HACKLDR leadFlywheel.setNeutralMode(NeutralMode.Brake);
    //Restore factory defaults -- should presist between power cycles
    leadFlywheel.restoreFactoryDefaults();
    followFlywheel.restoreFactoryDefaults();
    leadFlywheel.setInverted(true);
  }

  public void flywheelRun(XboxController controller, double power)
  {
    if (controller.getAButtonPressed())
    {
      shoot(power);
    }
    if (controller.getAButtonReleased())
    {
      stop();
    }
  }
  /**
   * shoots the flywheel at a given power
   * 
   * @param power the power to shoot the flywheel at [-1, 1]. Negative values spin outwards.
   */
  public void shoot(double power) {
    leadFlywheel.set( power);
  }

  /**
   * Stop the flywheel
   */
  public void stop() {
    leadFlywheel.set((double)0.0);
  }


  public void setState(FlywheelStates istate) {
    state = istate;
  }

  public FlywheelStates getState() {
    return state;
  }

}
