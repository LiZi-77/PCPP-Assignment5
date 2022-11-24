package mobilepayment;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;
import mobilepayment.Bank.BankCommand;
import mobilepayment.MobileApp.PaymentReq;
import mobilepayment.Account.PrintBalance;
import java.util.concurrent.TimeUnit;

public class Guardian extends AbstractBehavior<Guardian.GuardianCommand> {

    /* --- Messages ------------------------------------- */
    public interface GuardianCommand { }
    // Feel free to add message types at your convenience
    public static final class KickOff implements GuardianCommand { }
    /* --- State ---------------------------------------- */
    // empty
    ActorRef<BankCommand> mobileApp_1;
    ActorRef<BankCommand> mobileApp_2;

    ActorRef<BankCommand> bank_1;
    ActorRef<BankCommand> bank_2;

    ActorRef<BankCommand> account_1;
    ActorRef<BankCommand> account_2;


    /* --- Constructor ---------------------------------- */
    private Guardian(ActorContext<GuardianCommand> context) {
	    super(context);
        this.mobileApp_1 = getContext().spawn(MobileApp.create() , "mobileApp_actor_1");
        this.mobileApp_2 = getContext().spawn(MobileApp.create() , "mobileApp_actor_2");    

        this.bank_1 = getContext().spawn(Bank.create() , "bank_1");
        this.bank_2 = getContext().spawn(Bank.create() , "bank_2");   

        this.account_1 = getContext().spawn(Account.create() , "account_1");
        this.account_2 = getContext().spawn(Account.create() , "account_2"); 
    }


    /* --- Actor initial state -------------------------- */
    // To be implemented
    public static Behavior<GuardianCommand> create() {
         return Behaviors.setup(Guardian::new);
    }
    

    /* --- Message handling ----------------------------- */
    @Override
    public Receive<GuardianCommand> createReceive() {
	return newReceiveBuilder()
	    // To be implemented
        .onMessage(KickOff.class, this::onKickOff)
	    .build();
    }


    /* --- Handlers ------------------------------------- */
    // To be implemented
    private Behavior<GuardianCommand> onKickOff(KickOff msg) {

        this.account_1.tell(new PrintBalance());
        this.account_2.tell(new PrintBalance());
        
        this.mobileApp_1.tell(new PaymentReq(this.bank_1, this.account_1, this.account_2));

        this.account_1.tell(new PrintBalance());
        this.account_2.tell(new PrintBalance());

        return this;
    }
}

