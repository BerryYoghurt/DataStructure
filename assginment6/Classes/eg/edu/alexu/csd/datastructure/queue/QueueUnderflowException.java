package eg.edu.alexu.csd.datastructure.queue;

@SuppressWarnings("serial")
public class QueueUnderflowException extends RuntimeException{
	
	QueueUnderflowException(String message){
		super(message);
	}
}