package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.FlywheelConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Flywheel 
{
  private CANSparkMax leadFlywheel = new CANSparkMax(FlywheelConstants.kLeftFlywheelID, MotorType.kBrushless);
  private CANSparkMax followFlywheel = new CANSparkMax(FlywheelConstants.kRightFlywheelID, MotorType.kBrushless);
  private boolean toggle = false;

  public static enum FlywheelStates 
  {
    OFF, IN, OUT
  }

  Timer timer = new Timer();

  private FlywheelStates state = FlywheelStates.OFF;

  public Flywheel() 
  {
    //Restore factory defaults -- should presist between power cycles
    leadFlywheel.restoreFactoryDefaults();
    followFlywheel.restoreFactoryDefaults();
    followFlywheel.follow(leadFlywheel,true);
  }

  public void flywheelRun(XboxController controller, double lowPower, double highPower)
  {
    if (controller.getAButtonPressed())
    {
      shoot(lowPower);
    }
    if (controller.getAButtonReleased())
    {
      stop();
    }
    if (controller.getBButtonPressed())
    {
      shoot(highPower);
    }
    if (controller.getBButtonReleased())
    {
      stop();
    }
  }

  public void flyWheelToggle(XboxController controller1, XboxController controller2, double lowPower, double highPower)
  {
    
    if (controller1.getAButtonPressed()) 
    {
      if (toggle) 
      {
          // Current state is true so turn off
          stop();
          toggle = false;
          System.out.println("Flywheel OFF\n");
      } 
      else 
      {
          // Current state is false so turn on
          stop();
          shoot(lowPower);
          toggle = true;
          System.out.println("Flywheel LOW\n");
      }
    }

    if (controller1.getYButtonPressed() || controller2.getLeftBumperPressed()) 
    {
      if (toggle) 
      {
          // Current state is true so turn off
          stop();
          toggle = false;
          System.out.println("Flywheel OFF\n");
      } 
      else 
      {
          // Current state is false so turn on
          stop();
          shoot(highPower);
          toggle = true;
          System.out.println("Flywheel HIGH\n");
      }
    }
  }
  
  // @param power the power to shoot the flywheel at [-1, 1]. Negative values spin outwards.
  
  public void shoot(double power) 
  {
    leadFlywheel.set(power);
  }

  //Stop the flywheel
  
  public void stop() 
  {
    leadFlywheel.set((double)0.0);
  }

  public void setState(FlywheelStates istate) 
  {
    state = istate;
  }

  public FlywheelStates getState() 
  {
    return state;
  }

  public boolean getFlywheelState()
  {
    boolean status = false;
    if (leadFlywheel.get() > FlywheelConstants.kFlywheelHighSpeed - 0.05)
    {
      status = true;
    }
    return status;
  }
  
  public double getFlywheelVelocity()
  {
    return leadFlywheel.get();
  }
}
