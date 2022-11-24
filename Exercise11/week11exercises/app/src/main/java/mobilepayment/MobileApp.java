package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

// Hint: You may generate random numbers using Random::ints
import java.util.Random;
import java.util.stream.IntStream;

import mobilepayment.Account.PrintBalance;
import mobilepayment.Bank.BankCommand;
import mobilepayment.Bank.Transaction;

public class MobileApp extends AbstractBehavior<BankCommand> {

    /* --- Messages ------------------------------------- */
    //public interface MobileAppCommand { }
    // Feel free to add message types at your convenience
    
    // Message
    public static final class PaymentReq implements BankCommand {
        public final ActorRef<BankCommand> bank;
        public final ActorRef<BankCommand> from;
        public final ActorRef<BankCommand> to;

        public PaymentReq(ActorRef<BankCommand> bank, ActorRef<BankCommand> from,
                ActorRef<BankCommand> to) {
            this.bank = bank;
            this.from = from;
            this.to = to;
        }
    }

    /* --- State ---------------------------------------- */
    // To be Implemented


    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private MobileApp(ActorContext context) {	
	super(context);
	context.getLog().info("Mobile app {} started!",
			      context.getSelf().path().name());
    }
    

    /* --- Actor initial state -------------------------- */
    public static Behavior<BankCommand> create() {
	    return Behaviors.setup(MobileApp::new);
	// You may extend the constructor if necessary
    }
    

    /* --- Message handling ----------------------------- */    
    @Override
    public Receive<BankCommand> createReceive() {
	return newReceiveBuilder()
	    // To be implemented
        .onMessage(PaymentReq.class, this::onPaymentReq)
	    .build();
    }

    

    /* --- Handlers ------------------------------------- */
    // To be Implemented
    public Behavior<BankCommand> onPaymentReq(PaymentReq paymentreq) {
        //move 100 from A1 to A2.
        //paymentreq.bank.tell(new Transaction(paymentreq.from, paymentreq.to, 100));

        Random rand = new Random();
        for(int i = 0; i < 100; i++) {
            int amount = rand.nextInt(100);
            paymentreq.bank.tell(new Transaction(paymentreq.from, paymentreq.to, amount));
        } 

        return this;
    }
}
