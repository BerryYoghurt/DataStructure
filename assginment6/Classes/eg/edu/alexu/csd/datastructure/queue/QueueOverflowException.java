package eg.edu.alexu.csd.datastructure.queue;


@SuppressWarnings("serial")
public class QueueOverflowException extends RuntimeException{
	QueueOverflowException(String message){
		super(message);
	}
}
