package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ClimberConstants;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber {
    private TalonFX Climber = new TalonFX(ClimberConstants.kRightClimberID);
    private TalonFX leftClimber = new TalonFX(ClimberConstants.kLeftClimberID);
  public static enum ClimberStates {
    OFF, IN, OUT
  }

  private ClimberStates state = ClimberStates.OFF;

  /**
   * Creates a new Climber.
   */
  public Climber () {
    leftClimber.follow(Climber);
    Climber.setNeutralMode(NeutralMode.Brake);
    Climber.setInverted(true);

  }

  public void ClimberRun(XboxController controller, double power)
  {
    if (controller.getYButtonPressed())
    {
      index(-power);
    }
    if (controller.getYButtonReleased())
    {
      stop();
    }
  }
  /**
   * spins the Climber at a given power
   * 
   * @param power the power to spin the Climber at [-1, 1]. Negative values spin outwards.
   */
  public void index(double power) {
    Climber.set(ControlMode.PercentOutput, power);
  }

  /**
   * Stop the Climber
   */
  public void stop() {
    Climber.set(ControlMode.PercentOutput, 0);
  }


  public void setState(ClimberStates istate) {
    state = istate;
  }

  public ClimberStates getState() {
    return state;
  }

}