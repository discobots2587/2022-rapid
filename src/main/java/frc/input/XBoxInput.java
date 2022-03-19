package frc.input;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The input from the XBox remote
 */
public class XBoxInput extends InputMethod {
  private XboxController controller;
  private XboxController controller2;
  private final double JOYSTICK_DEAD_ZONE = 0.075;

  public XBoxInput() {
    // the joystick is registered as port #0
    controller = new XboxController(0);
    controller2 = new XboxController(1);

  }


  @Override
  public double leftSidePower() {
    double forward = controller.getLeftY();
    if(Math.abs(forward) < JOYSTICK_DEAD_ZONE)
      return 0;
    return forward;
  }

  @Override
  public double rightSidePower() {
    double forward = controller.getRightY();
    if(Math.abs(forward) < JOYSTICK_DEAD_ZONE)
      return 0;
    return forward;
  }
}