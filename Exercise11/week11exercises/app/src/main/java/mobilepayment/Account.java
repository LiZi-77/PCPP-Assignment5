package mobilepayment;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Bank.BankCommand;
import mobilepayment.Bank.Deposite;

public class Account extends AbstractBehavior<BankCommand> {

    /* --- Messages ------------------------------------- */
    // Feel free to add message types at your convenience
    public static final class PrintBalance implements BankCommand{ }

    /* --- State ---------------------------------------- */
    // To be Implemented
    private int balance = 0;    //the balance of this account

    /* --- Constructor ---------------------------------- */
    // Feel free to extend the contructor at your convenience
    private Account(ActorContext<BankCommand> context) {
	    super(context);
        context.getLog().info("Account {} started!",
			      context.getSelf().path().name());
    }
    

    /* --- Actor initial state -------------------------- */
    public static Behavior<BankCommand> create() {
	    return Behaviors.setup(Account::new);
    }
    

    /* --- Message handling ----------------------------- */
    // To be Implemented
    @Override
    public Receive<BankCommand> createReceive() {
	return newReceiveBuilder()
	    // To be implemented
        .onMessage(Deposite.class, this::onDeposite)
        .onMessage(PrintBalance.class, this::onPrintBalance)
	    .build();
    }

    /* --- Handlers ------------------------------------- */
    // To be Implemented
    public Behavior<BankCommand> onDeposite(Deposite deposite) {
        //print current balance
        //getContext().getLog().info("Account {} has a balance of {}", getContext().getSelf().path().name(), this.balance);

        // change the balance based on the deposite
        this.balance += deposite.amount;

        //print the balance of this account after receiving the message
        //getContext().getLog().info("Account {} has a balance of {}", getContext().getSelf().path().name(), this.balance);

        return this;
    }

    public Behavior<BankCommand> onPrintBalance(PrintBalance pb) {
        //print current balance
        getContext().getLog().info("Account {} has a balance of {}", getContext().getSelf().path().name(), this.balance);

        return this;
    }
}

/* explaination:
 * the state of every account is a int which store the balance of this account.
 * And the balance of every account is initialized to 0 in the beginning
 * The message it must handle is the Deposite AccountCommand
 * After receiving this kind of command, the balance of the account should be changed.
 */
