package core;
/*
 *   Created by Corentin on 04/05/2020 at 15:23
 */

/**
 * Console util for print text on the table.
 */
public final class Console {

    /**
     * The delay between 2 line.
     */
    private static long delay = 0;

    /**
     * Set the delay
     * @param delay The delay
     */
    public static void setDelay(long delay){
        Console.delay = Math.max(0, delay);
    }

    /**
     * Print a line.
     */
    public static void line(){
        System.out.println();
    }

    /**
     * Print an object on a single line.
     * @param o The object to print
     */
    public static void println(Object o){
        System.out.println(o);
        delay();
    }

    /**
     * Print an object.
     * @param o The object to print
     */
    public static void print(Object o){
        System.out.print(o);
        delay();
    }

    /**
     * Wait the delay.
     */
    private static void delay(){
        if(delay > 0){
            try{
                Thread.sleep(delay);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
