package control;

/**
 * clase encargada de manejar las excepciones de tipo control.
 * @author Yenny, Camilo, Emanuel.
 * @version 1.0
 */
public class ControlException extends Exception
{
     public ControlException(String mensaje) {
		super(mensaje);
	}

    ControlException()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
