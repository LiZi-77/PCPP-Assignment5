package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class Bank extends AbstractBehavior<Bank.BankCommand> {

    /* --- Messages ------------------------------------- */
    public interface BankCommand { }
    // Feel free to add message types at your convenience
    // Transaction command:
    public static final class Transaction implements BankCommand {
        public final ActorRef<BankCommand> from;
        public final ActorRef<BankCommand> to;
        public final int amount;

        public Transaction(ActorRef<BankCommand> from, ActorRef<BankCommand> to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }
    }

    // Deposit Message 
    public static final class Deposite implements BankCommand{ 
        public final int amount;

        public Deposite(int amount) {
            this.amount = amount;
        }
    }

    

    /* --- State ---------------------------------------- */
    // To be Implemented
    

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Bank(ActorContext<BankCommand> context) {
	    super(context);
        context.getLog().info("Bank {} started!",
			      context.getSelf().path().name());
    }
    

    /* --- Actor initial state -------------------------- */
    // To be Implemented
    public static Behavior<BankCommand> create() {
        return Behaviors.setup(Bank::new);
    }


    /* --- Message handling ----------------------------- */
    @Override
    public Receive<BankCommand> createReceive() {
	return newReceiveBuilder()
	    // To be implemented
        .onMessage(Transaction.class, this::onTransaction)
	    .build();
    }
    

    /* --- Handlers ------------------------------------- */
    // To be Implemented
    public Behavior<BankCommand> onTransaction(Transaction transaction){
        //getContext().getLog().info("Bank {} sends {} from Account {} to Account {}", getContext().getSelf().path().name(), 
        //transaction.amount, transaction.from, transaction.to);

        final int amount = transaction.amount;
        transaction.from.tell(new Deposite(-amount));
        transaction.to.tell(new Deposite(amount));

        return this;
    }
}
