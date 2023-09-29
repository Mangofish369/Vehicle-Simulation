import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Bus subclass
 */
public class Bus extends Vehicle
{
    public static final int STOP_DURATION = 1000;
    SimpleTimer stop = new SimpleTimer();
    private boolean moving = true;
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(moving){
            super.act();
        }
        else{
            if(stop.millisElapsed() >= 1000){
                moving = true;
            }
        }
        
    }

    public boolean checkHitPedestrian () {
        Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        if( p!= null && p.isAwake()){
            getWorld().removeObject(p);
            moving = false;
            stop.mark();
            return true; 
        }
        return false;
    }
}
